package com.fireflylearning.tasksummary.di.subcomponents

import com.fireflylearning.tasksummary.di.modules.TaskListModule
import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.tasklist.views.TaskListActivity
import dagger.Subcomponent

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
@Subcomponent(modules = arrayOf(TaskListModule::class))
interface TaskListSubcomponent {
    fun inject(activity: TaskListActivity)
}

