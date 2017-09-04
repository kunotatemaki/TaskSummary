package com.fireflylearning.tasksummary.tasklist.viewmodels

import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.TaskListLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewModel : ViewModel() {
    var showingEmpty: Boolean = false
    var host: String = ""
    var token: String = ""
    val tasks: CustomLiveData<MutableList<Task>> = TaskListLiveData()
}