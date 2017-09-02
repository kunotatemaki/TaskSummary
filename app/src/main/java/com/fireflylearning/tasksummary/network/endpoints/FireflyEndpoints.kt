package com.fireflylearning.tasksummary.network.endpoints

import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Roll on 31/8/17.
 */
interface FireflyEndpoints {
    @GET("bins/bvyob")
    fun getSuperHeroes() : Call<TaskServerResponse>
}