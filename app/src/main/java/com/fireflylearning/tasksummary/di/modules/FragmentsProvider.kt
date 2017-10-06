package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.ui.tasklist.TaskListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Roll on 3/10/17.
 * builder for fragemnts
 */
@Module
abstract class FragmentsProvider {

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun provideTaskListFragmentFactory(): TaskListFragment



}