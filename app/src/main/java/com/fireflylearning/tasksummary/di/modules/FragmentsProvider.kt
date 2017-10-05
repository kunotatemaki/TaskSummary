package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.splashscreen.SplashActivity
import com.fireflylearning.tasksummary.taskdetails.ActivityDetails
import com.fireflylearning.tasksummary.ui.tasklist.TaskListFragment
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Roll on 3/10/17.
 * builder for fragemnts
 */
@Module
abstract class FragmentsProvider {

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(TaskListFragmentModule::class))
    abstract fun provideTaskListFragmentFactory(): TaskListFragment



}