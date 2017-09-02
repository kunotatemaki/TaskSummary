package com.fireflylearning.tasksummary.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 31/8/17.
 */
class TaskElementResponse {

    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("photo")
    @Expose
    val photo: String? = null
    @SerializedName("realName")
    @Expose
    val realName: String? = null
    @SerializedName("height")
    @Expose
    val height: String? = null
    @SerializedName("power")
    @Expose
    val power: String? = null
    @SerializedName("abilities")
    @Expose
    val abilities: String? = null
    @SerializedName("groups")
    @Expose
    val groups: String? = null

}