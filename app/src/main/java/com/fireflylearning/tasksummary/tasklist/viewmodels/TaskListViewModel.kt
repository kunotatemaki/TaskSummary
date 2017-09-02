package com.fireflylearning.tasksummary.tasklist.viewmodels

import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.TaskListLiveData
import com.fireflylearning.tasksummary.model.Task

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewModel : ViewModel() {
    var showingEmpty: Boolean = false
    val tasks: CustomLiveData<MutableList<Task>> = TaskListLiveData()
}