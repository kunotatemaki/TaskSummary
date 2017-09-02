package com.fireflylearning.tasksummary

import android.app.Application
import com.fireflylearning.tasksummary.dependencyinjection.components.DaggerFintonicAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.components.FireflyAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.modules.FireflyAppModule

/**
 * Created by Roll on 31/8/17.
 */
class FireflyApp : Application() {

    lateinit var mComponent: FireflyAppComponent
    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFintonicAppComponent.builder()
                .fintonicAppModule(FireflyAppModule(this))
                .build()
    }
}