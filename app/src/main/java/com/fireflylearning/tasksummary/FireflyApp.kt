package com.fireflylearning.tasksummary

import android.app.Application
import com.fireflylearning.tasksummary.di.FireflyAppComponent
import com.fireflylearning.tasksummary.di.modules.FireflyAppModule
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import android.arch.persistence.room.Room
import com.fireflylearning.tasksummary.di.DaggerFireflyAppComponent
import com.fireflylearning.tasksummary.utils.FireflyConstants
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by Roll on 31/8/17.
 */
class FireflyApp : DaggerApplication() {

    lateinit var mComponent: FireflyAppComponent

    lateinit var mDatabase: FireflyDatabase

    override fun onCreate() {
        super.onCreate()

        //Room
        mDatabase = Room.databaseBuilder(applicationContext,
                FireflyDatabase::class.java, FireflyConstants.DATABASE_NAME)
                .build()
    }

    override fun applicationInjector(): AndroidInjector<out FireflyApp> {
        val mComponent: FireflyAppComponent = DaggerFireflyAppComponent
                .builder()
                .application(this)
                .build()
        mComponent.inject(this)
        return mComponent
    }


}