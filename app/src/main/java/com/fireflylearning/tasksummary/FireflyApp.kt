package com.fireflylearning.tasksummary

import android.app.Application
import com.fireflylearning.tasksummary.dependencyinjection.components.DaggerFireflyAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.components.FireflyAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.modules.FireflyAppModule

/**
 * Created by Roll on 31/8/17.
 */
class FireflyApp : Application() {

    lateinit var mComponent: FireflyAppComponent
    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFireflyAppComponent.builder()
                .fireflyAppModule(FireflyAppModule(this))
                .build()
    }
}