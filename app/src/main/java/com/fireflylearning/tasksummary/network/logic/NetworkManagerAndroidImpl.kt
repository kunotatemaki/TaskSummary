package com.fireflylearning.tasksummary.network.logic

import android.text.TextUtils
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.network.endpoints.FireflyService
import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import okhttp3.ResponseBody
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

    private var retrofit: Retrofit? = null

    @Inject
    lateinit var log : LoggerHelper

    @Inject
    lateinit var resources: ResourcesManager

    @Inject
    lateinit var preferences: PreferencesManager

    override fun getListOfTasks(host: String, token: String, tasks: CustomLiveData<MutableList<Task>>) {

        configureEndpoint(host)

        retrofit?.let {
            val fireflyEndpoints = retrofit!!.create(FireflyService::class.java)

            //obtain query from reosurces file
            val query = resources.getString(R.string.tasks_query)

            val myCall: Call<TaskServerResponse> = fireflyEndpoints.getTasks(FireflyConstants.DEVICE_ID,
                    token, query)

            myCall.enqueue(object : Callback<TaskServerResponse> {
                override fun onResponse(call: Call<TaskServerResponse>?, response: Response<TaskServerResponse>?) {
                    if (response?.isSuccessful as Boolean) {
                        val list: MutableList<Task> = arrayListOf()
                        //map photos to observable value
                        response.body()
                                ?.tasks
                                ?.tasksList
                                ?.mapTo(list) {
                                    Task(it)
                                }

                        //order the list by task's date set
                        val sortedList: List<Task> = list.sortedWith(compareBy({ it.set }))

                        tasks.setTaskOrigin(FireflyConstants.TaskOrigin.FROM_NETWORK)

                        tasks.setLivedataValue(sortedList as MutableList<Task>)

                    } else {

                        log.d(this, "empty response")
                        tasks.setTaskOrigin(FireflyConstants.TaskOrigin.FROM_NETWORK)
                        tasks.setLivedataValue(arrayListOf())
                    }
                }

                override fun onFailure(call: Call<TaskServerResponse>?, t: Throwable?) {
                    log.d(this, t?.message.toString())
                    tasks.setTaskOrigin(FireflyConstants.TaskOrigin.FROM_NETWORK)
                    tasks.setLivedataValue(arrayListOf())

                }
            })
        }
    }

    override fun login(host: String, token: String, status: CustomLiveData<FireflyConstants.TokenError>) {

        configureEndpoint(host)

        retrofit?.let {
            val fireflyEndpoints = retrofit!!.create(FireflyService::class.java)

            val myCall: Call<ResponseBody> = fireflyEndpoints.login(FireflyConstants.DEVICE_ID,
                    token)

            myCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response?.isSuccessful as Boolean) {
                        val stringResponse: String? = response.body()?.string()
                        stringResponse?.let {
                            if(stringResponse == "OK"){
                                status.setLivedataValue(FireflyConstants.TokenError.RESPONSE_OK)
                                return
                            }
                        }
                        status.setLivedataValue(FireflyConstants.TokenError.HOST_ERROR)

                    } else {
                        log.d(this, "empty response")
                        when(response.code()) {
                            401 -> {
                                status.setLivedataValue(FireflyConstants.TokenError.INVALID_TOKEN)
                            }
                            else -> {
                                status.setLivedataValue(FireflyConstants.TokenError.HOST_ERROR)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    log.d(this, t?.message.toString())
                    status.setLivedataValue(FireflyConstants.TokenError.NETWORK_ERROR)

                }
            })
        }
    }

    private fun configureEndpoint(host: String){
        val myHost: String = if(host.contains("https://")) {
            host
        }else{
            TextUtils.concat("https://", host).toString()
        }
        retrofit = Retrofit.Builder()
        .baseUrl(myHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}

