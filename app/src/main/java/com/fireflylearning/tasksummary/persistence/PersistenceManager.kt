package com.fireflylearning.tasksummary.persistence

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.fireflylearning.tasksummary.persistence.entities.Task

/**
 * Created by Roll on 5/10/17.
 * interface for the database helper
 */
interface PersistenceManager {

    fun insertTask(task: Task)

    fun insertListOfTasks(tasks: List<Task>)

    fun loadTasks() : LiveData<PagedList<Task>>

}