package com.fireflylearning.tasksummary.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 31/8/17.
 */
class TaskElementResponse {

    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("set")
    @Expose
    val set: String? = null
    @SerializedName("due")
    @Expose
    val due: String? = null
    @SerializedName("archived")
    @Expose
    val archived: Boolean? = null
    @SerializedName("draft")
    @Expose
    val draft: Boolean? = null
    @SerializedName("showInMarkbook")
    @Expose
    val showInMarkbook: Boolean? = null
    @SerializedName("highlightInMarkbook")
    @Expose
    val highlightInMarkbook: Boolean? = null
    @SerializedName("showInParentPortal")
    @Expose
    val showInParentPortal: Boolean? = null
    @SerializedName("hideAddressees")
    @Expose
    val hideAddressees: Boolean? = null
    @SerializedName("descriptionPageUrl")
    @Expose
    val descriptionPageUrl: String? = null
}