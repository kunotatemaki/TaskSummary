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

    private var host: String? = null
    private lateinit var fireflyService: FireflyService

    fun getFireflyService(host: String): FireflyService{
        val myHost: String = if(host.contains("https://")) {
            host
        }else{
            TextUtils.concat("https://", host).toString()
        }
        if(this.host == null || this.host != myHost){
            this.host = myHost
            fireflyService = Retrofit.Builder()
                    .baseUrl(this.host!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create<FireflyService>(FireflyService::class.java)
        }
        return fireflyService
    }

}