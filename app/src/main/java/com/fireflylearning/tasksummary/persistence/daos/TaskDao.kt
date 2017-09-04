package com.fireflylearning.tasksummary.persistence.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.fireflylearning.tasksummary.persistence.entities.Task

import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Roll on 4/9/17.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}