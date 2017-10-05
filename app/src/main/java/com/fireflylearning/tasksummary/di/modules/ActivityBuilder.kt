package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.splashscreen.SplashActivity
import com.fireflylearning.tasksummary.taskdetails.ActivityDetails
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Roll on 3/10/17.
 */
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    abstract fun bindLoginActivity(): LoginActivity

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): ActivityDetails

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(TaskListModule::class, FragmentsProvider::class))
    abstract fun bindTaskListActivity(): TaskListActivity

}