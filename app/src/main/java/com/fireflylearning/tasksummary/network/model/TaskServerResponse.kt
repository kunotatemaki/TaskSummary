package com.fireflylearning.tasksummary.network.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 31/8/17.
 */
class TaskServerResponse {

    @SerializedName("data")
    @Expose
    var tasks: TaskListServerResponse? = null


    class TaskListServerResponse{
        @SerializedName("tasks")
        @Expose
        var tasksList: List<TaskElementResponse>? = null
    }

}