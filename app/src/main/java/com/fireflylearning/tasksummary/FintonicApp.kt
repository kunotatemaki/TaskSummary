package com.fireflylearning.tasksummary

import android.app.Application
import com.fireflylearning.tasksummary.dependencyinjection.components.DaggerFintonicAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.components.FintonicAppComponent
import com.fireflylearning.tasksummary.dependencyinjection.modules.FintonicAppModule

/**
 * Created by Roll on 31/8/17.
 */
class FintonicApp: Application() {

    lateinit var mComponent: FintonicAppComponent
    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerFintonicAppComponent.builder()
                .fintonicAppModule(FintonicAppModule(this))
                .build()
    }
}