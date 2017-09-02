package com.fireflylearning.tasksummary.model

import com.fireflylearning.tasksummary.network.model.TaskElementResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Roll on 31/8/17.
 */

//todo quitar los elementos antiguos
class Task constructor(val id: Int?,
                       val title: String?,
                       val description_page_url: String?,
                       val set: Date?,
                       val due: Date?,
                       val archived: Boolean?,
                       val draft: Boolean?,
                       val show_in_markbook: Boolean?,
                       val highlight_in_markbook: Boolean?,
                       val show_in_parent_portal: Boolean?,
                       val hide_addressees: Boolean? = null) {


            
    constructor(task: TaskElementResponse) : this(
            id = task.id,
            title = task.title,
            description_page_url = task.descriptionPageUrl,
            set = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(task.set),
            due= if (task.due!=null) SimpleDateFormat("yyyy-MM-dd").parse(task.due) else  null,
            archived = task.archived,
            draft = task.draft,
            show_in_markbook = task.showInMarkbook,
            highlight_in_markbook = task.highlightInMarkbook,
            show_in_parent_portal = task.showInParentPortal,
            hide_addressees = task.hideAddressees
    )

    
}