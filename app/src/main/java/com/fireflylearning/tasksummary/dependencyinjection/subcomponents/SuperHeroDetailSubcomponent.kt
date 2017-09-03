package com.fireflylearning.tasksummary.dependencyinjection.subcomponents

import com.fireflylearning.tasksummary.dependencyinjection.modules.LoginModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginActivity
import dagger.Subcomponent


/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginSubcomponent {
    fun inject(activity: LoginActivity)
}