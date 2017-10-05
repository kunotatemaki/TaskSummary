package com.fireflylearning.tasksummary.utils

import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import java.text.SimpleDateFormat
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 3/9/17.
 * Utilities for task class
 */
@Singleton
class TaskUtils @Inject constructor(){

    @Inject
    lateinit var resources: ResourcesManager

    private val format: String = "EEE d MMM yyyy"

    fun getFlagsFromTask(task: Task): String {
        val flags = ArrayList<String>()

        task.draft?.let {
            if (task.draft) {
                flags.add(resources.getString(R.string.draft))
            }
        }

        task.showInMarkbook?.let markbook@ {
            task.highlightInMarkbook?.let {
                if(task.highlightInMarkbook!!) {
                    flags.add(resources.getString(R.string.markbook_highlighted))
                    return@markbook
                }
            }
            flags.add(resources.getString(R.string.markbook))
        }

        task.showInParentPortal?.let {
            if (task.showInParentPortal) {
                flags.add(resources.getString(R.string.show_in_parent_portal))
            }
        }

        task.hideAddressees?.let {
            if (task.hideAddressees) {
                flags.add(resources.getString(R.string.hide_addressees))
            }
        }

        return android.text.TextUtils.join(", ", flags)
    }

    fun getDateSet(task: Task): String{
        return SimpleDateFormat(format).format(task.set)
    }

    fun getDateDue(task: Task): String{
        return SimpleDateFormat(format).format(task.due)
    }

}