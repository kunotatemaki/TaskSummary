package com.fireflylearning.tasksummary.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 31/8/17.
 */
class TaskElementResponse {

    @SerializedName("id")
    @Expose
    private val id: Int? = null
    @SerializedName("title")
    @Expose
    private val title: String? = null
    @SerializedName("set")
    @Expose
    private val set: String? = null
    @SerializedName("due")
    @Expose
    private val due: String? = null
    @SerializedName("archived")
    @Expose
    private val archived: Boolean? = null
    @SerializedName("draft")
    @Expose
    private val draft: Boolean? = null
    @SerializedName("show_in_markbook")
    @Expose
    private val showInMarkbook: Boolean? = null
    @SerializedName("highlight_in_markbook")
    @Expose
    private val highlightInMarkbook: Boolean? = null
    @SerializedName("show_in_parent_portal")
    @Expose
    private val showInParentPortal: Boolean? = null
    @SerializedName("hide_addressees")
    @Expose
    private val hideAddressees: Boolean? = null
    @SerializedName("description_page_url")
    @Expose
    private val descriptionPageUrl: Any? = null
}