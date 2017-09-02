package com.fireflylearning.tasksummary.tasklist.livedataobservers

import com.fireflylearning.tasksummary.model.Task

/**
 * Created by Roll on 31/8/17.
 */
interface MyLivedataObserver {

    fun handleChangesInObservedTasks(tasks: MutableList<Task>)

}