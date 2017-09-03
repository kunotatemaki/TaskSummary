package com.fireflylearning.tasksummary.network.endpoints

import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Roll on 31/8/17.
 */
interface FireflyEndpoints {
    @FormUrlEncoded
    @POST("_api/1.0/graphql")
    fun getTasks(@Query("ffauth_device_id") deviceId: String,
                 @Query("ffauth_secret") secret: String,
                 @Field("data") query: String) : Call<TaskServerResponse>

    @GET("login/api/checktoken")
    fun login(@Query("ffauth_device_id") deviceId: String,
              @Query("ffauth_secret") secret: String) : Call<ResponseBody>

}