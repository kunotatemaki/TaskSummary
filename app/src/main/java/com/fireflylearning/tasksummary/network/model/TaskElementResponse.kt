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
    @SerializedName("show_in_markbook")
    @Expose
    val showInMarkbook: Boolean? = null
    @SerializedName("highlight_in_markbook")
    @Expose
    val highlightInMarkbook: Boolean? = null
    @SerializedName("show_in_parent_portal")
    @Expose
    val showInParentPortal: Boolean? = null
    @SerializedName("hide_addressees")
    @Expose
    val hideAddressees: Boolean? = null
    @SerializedName("description_page_url")
    @Expose
    val descriptionPageUrl: String? = null
}