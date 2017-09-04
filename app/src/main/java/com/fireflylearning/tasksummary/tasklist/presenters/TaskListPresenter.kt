package com.fireflylearning.tasksummary.tasklist.presenters

import com.fireflylearning.tasksummary.persistence.entities.Task

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListPresenter {
    fun loadTasks()

    fun taskClicked(task: Task)

    fun closeSession()
}