package com.fireflylearning.tasksummary.network

import android.text.TextUtils
import com.fireflylearning.tasksummary.utils.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 5/10/17.
 * factory method to return the {@link FireflyService} class
 */
@Singleton
class FireflyServiceFactory @Inject constructor() {

    private lateinit var fireflyService: FireflyService

    fun getFireflyService(host: String, withAdapter: Boolean): FireflyService{
        val myHost: String = if(host.contains("http://")) {
            host
        }else{
            TextUtils.concat("http://", host).toString()
        }
        fireflyService = if(withAdapter) {
            Retrofit.Builder()
                    .baseUrl(myHost)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create<FireflyService>(FireflyService::class.java)
        }else{
            Retrofit.Builder()
                    .baseUrl(myHost)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create<FireflyService>(FireflyService::class.java)
            }

        return fireflyService
    }

}