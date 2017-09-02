package com.fireflylearning.tasksummary.network.logic

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.network.endpoints.FireflyEndpoints
import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import com.fireflylearning.tasksummary.utils.FintonicConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Singleton
class NetworkManagerAndroidImpl @Inject constructor(): NetworkManager {

    private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(FintonicConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Inject
    lateinit var log : LoggerHelper

    override fun getSuperHeroes(superHeroes: CustomLiveData<MutableList<Task>>) {

        val fintonicEndpoints = retrofit.create(FireflyEndpoints::class.java)

        val myCall : Call<TaskServerResponse> = fintonicEndpoints.getSuperHeroes()
        myCall.enqueue(object : Callback<TaskServerResponse> {
            override fun onResponse(call: Call<TaskServerResponse>?, response: Response<TaskServerResponse>?) {
                if (response?.isSuccessful as Boolean) {
                    val list : MutableList<Task> = arrayListOf()
                    //map photos to observable value
                    response.body()
                            ?.superheroes
                            ?.mapTo(list) {
                        Task(it)
                    }
                    superHeroes.setLivedataValue(list)

                } else {

                    log.d(this, "respuesta vacía")
                    superHeroes.setLivedataValue(arrayListOf())
                }
            }

            override fun onFailure(call: Call<TaskServerResponse>?, t: Throwable?) {
                log.d(this, t?.message.toString())
                superHeroes.setLivedataValue(arrayListOf())

            }
        })
    }
}