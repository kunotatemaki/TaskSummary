package com.fireflylearning.tasksummary.di.subcomponents

import com.fireflylearning.tasksummary.di.modules.LoginModule
import com.fireflylearning.tasksummary.di.scopes.CustomScopes
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