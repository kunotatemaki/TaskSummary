package com.fireflylearning.tasksummary.utils.ui


import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.FireflyConstants

/**
 * Created by Roll on 31/8/17.
 */
interface MyLivedataObserver {

    fun handleChangesInObservedStatus(status: FireflyConstants.TokenError)
    fun handleChangesInObservedTasks(tasks: MutableList<Task>, origin: FireflyConstants.TaskOrigin)

}