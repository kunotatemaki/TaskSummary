package com.fireflylearning.tasksummary.di.components

import com.fireflylearning.tasksummary.di.modules.FireflyAppModule
import com.fireflylearning.tasksummary.di.modules.LoginModule
import com.fireflylearning.tasksummary.di.modules.TaskListModule
import com.fireflylearning.tasksummary.di.subcomponents.LoginSubcomponent
import com.fireflylearning.tasksummary.di.subcomponents.TaskListSubcomponent
import com.fireflylearning.tasksummary.splashscreen.SplashActivity
import com.fireflylearning.tasksummary.taskdetails.ActivityDetails
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Singleton
@Component(modules = arrayOf(FireflyAppModule::class))
interface FireflyAppComponent {
    fun getTaskListSubcomponent(module: TaskListModule): TaskListSubcomponent
    fun getLoginSubcomponent(module: LoginModule): LoginSubcomponent
    fun inject(activity: SplashActivity)
    fun inject(activity: ActivityDetails)
}