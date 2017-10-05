package com.fireflylearning.tasksummary.ui.tasklist.presenters

import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListView

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListPresenter {
    fun loadTasksFromNetwork()

    fun loadTasksFromDb()

    fun taskClicked(task: Task)

    fun closeSession()
}