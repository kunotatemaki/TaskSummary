package com.fireflylearning.tasksummary.ui.tasklist.views

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.tasklist.lifecycleobservers.TaskListLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListView {

    fun addLifecycleObserver(observer: TaskListLifecycleObserver)

    fun getLiveTaks(): CustomLiveData<MutableList<Task>>

    fun setTasksInView(tasks: List<Task>)

    fun showEmptyList(message: String)

    fun hideEmptyList()

    fun showLoader()

    fun hideLoader()

    fun showTaskDetails(url: String)

    fun getTokenFromChache() : String

    fun getHostFromChache() : String

    fun showMessage(message: String)

    fun goToLogin()
}