package com.fireflylearning.tasksummary.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 31/8/17.
 */
class TaskServerResponse {

    @SerializedName("superheroes")
    @Expose
    var superheroes: List<TaskElementResponse>? = null

}