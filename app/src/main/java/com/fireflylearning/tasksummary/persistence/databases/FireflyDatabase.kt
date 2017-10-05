package com.fireflylearning.tasksummary.persistence.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.fireflylearning.tasksummary.persistence.daos.TaskDao
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.persistence.utils.Converters

/**
 * Created by Roll on 4/9/17.
 * database interdace form Room
 */

@Database(entities = arrayOf(Task::class), version = 1)
@TypeConverters(Converters::class)
abstract class FireflyDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}