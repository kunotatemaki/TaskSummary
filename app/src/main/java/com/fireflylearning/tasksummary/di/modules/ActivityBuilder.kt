package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.ui.login.LoginActivity
import com.fireflylearning.tasksummary.ui.splashscreen.SplashActivity
import com.fireflylearning.tasksummary.ui.taskdetails.ActivityDetails
import com.fireflylearning.tasksummary.ui.tasklist.TaskListActivity
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
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): ActivityDetails

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FragmentsProvider::class))
    abstract fun bindTaskListActivity(): TaskListActivity

}