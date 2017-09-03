package com.fireflylearning.tasksummary.utils

import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import java.text.SimpleDateFormat
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 3/9/17.
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

        task.show_in_markbook?.let markbook@ {
            task.highlight_in_markbook?.let {
                if(task.highlight_in_markbook!!) {
                    flags.add(resources.getString(R.string.markbook_highlighted))
                    return@markbook
                }
            }
            flags.add(resources.getString(R.string.markbook))
        }

        task.show_in_parent_portal?.let {
            if (task.show_in_parent_portal) {
                flags.add(resources.getString(R.string.show_in_parent_portal))
            }
        }

        task.hide_addressees?.let {
            if (task.hide_addressees) {
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