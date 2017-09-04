package com.fireflylearning.tasksummary.tasklist.presenters

import com.fireflylearning.tasksummary.persistence.entities.Task

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListPresenter {
    fun loadTasksFromNetwork()

    fun loadTasksFromDb()

    fun taskClicked(task: Task)

    fun closeSession()
}