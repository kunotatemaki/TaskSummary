package com.fireflylearning.tasksummary

import android.app.Application
import com.fireflylearning.tasksummary.di.components.DaggerFireflyAppComponent
import com.fireflylearning.tasksummary.di.components.FireflyAppComponent
import com.fireflylearning.tasksummary.di.modules.FireflyAppModule
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import android.arch.persistence.room.Room
import com.fireflylearning.tasksummary.utils.FireflyConstants


/**
 * Created by Roll on 31/8/17.
 */
class FireflyApp : Application() {

    lateinit var mComponent: FireflyAppComponent

    lateinit var mDatabase: FireflyDatabase

    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFireflyAppComponent.builder()
                .fireflyAppModule(FireflyAppModule(this))
                .build()

        //Room
        mDatabase = Room.databaseBuilder(applicationContext,
                FireflyDatabase::class.java, FireflyConstants.DATABASE_NAME)
                .build()
    }
}