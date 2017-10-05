package com.fireflylearning.tasksummary.persistence

import android.arch.lifecycle.LiveData
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import com.fireflylearning.tasksummary.persistence.entities.Task
import javax.inject.Inject

/**
 * Created by Roll on 5/10/17.
 * Implementation of persistence manager for android
 */
class PersistenceManagerImplAndroid @Inject constructor(val db: FireflyDatabase): PersistenceManager {

    override fun insertTask(task: Task) {
        db.taskDao().insertAll(task)
    }

    override fun insertListOfTasks(tasks: List<Task>) {
        db.taskDao().insertAll(*tasks.toTypedArray())
    }

    override fun loadTasks() : LiveData<List<Task>> {
        return db.taskDao().getAllTasks()
    }
}