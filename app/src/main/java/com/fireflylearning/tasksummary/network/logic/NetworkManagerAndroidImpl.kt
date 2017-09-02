package com.fireflylearning.tasksummary.network.logic

import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.network.endpoints.FireflyEndpoints
import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import com.fireflylearning.tasksummary.resources.ResourcesManager
import com.fireflylearning.tasksummary.utils.FireflyConstants
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
            .baseUrl(FireflyConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Inject
    lateinit var log : LoggerHelper

    @Inject
    lateinit var resources: ResourcesManager

    override fun getListOfTasks(tasks: CustomLiveData<MutableList<Task>>) {

        val fireflyEndpoints = retrofit.create(FireflyEndpoints::class.java)

        //obtain query from reosurces file
        val query = resources.getString(R.string.tasks_query)

        //todo sacar los valores de las propiedades
        val myCall : Call<TaskServerResponse> = fireflyEndpoints.getTasks(FireflyConstants.DEVICE_ID,
                "secret1", query)

        myCall.enqueue(object : Callback<TaskServerResponse> {
            override fun onResponse(call: Call<TaskServerResponse>?, response: Response<TaskServerResponse>?) {
                if (response?.isSuccessful as Boolean) {
                    val list : MutableList<Task> = arrayListOf()
                    //map photos to observable value
                    response.body()
                            ?.tasks
                            ?.tasksList
                            ?.mapTo(list) {
                        Task(it)
                    }
                    tasks.setLivedataValue(list)

                } else {

                    log.d(this, "empty response")
                    tasks.setLivedataValue(arrayListOf())
                }
            }

            override fun onFailure(call: Call<TaskServerResponse>?, t: Throwable?) {
                log.d(this, t?.message.toString())
                tasks.setLivedataValue(arrayListOf())

            }
        })
    }
}