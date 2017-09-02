package com.fireflylearning.tasksummary.network.endpoints

import com.fireflylearning.tasksummary.network.model.SuperHeroesResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Roll on 31/8/17.
 */
interface FintonicEndpoints {
    @GET("bins/bvyob")
    fun getSuperHeroes() : Call<SuperHeroesResponse>
}