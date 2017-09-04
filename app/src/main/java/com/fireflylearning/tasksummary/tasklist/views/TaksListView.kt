package com.fireflylearning.tasksummary.tasklist.views

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface TaksListView {

    fun addLifecycleObserver(observer: TaskListLifecycleObserver)

    fun getLiveTaks(): CustomLiveData<MutableList<Task>>

    fun setTasksInView(tasks: List<Task>)

    fun showEmptyList(message: String)

    fun hideEmptyList()

    fun showLoader()

    fun hideLoader()

    fun showTaskDetails(task: Task)

    fun getTokenFromChache() : String

    fun getHostFromChache() : String

    fun goToLogin()
}