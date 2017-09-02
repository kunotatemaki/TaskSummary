package com.fireflylearning.tasksummary.dependencyinjection.subcomponents

import com.fireflylearning.tasksummary.dependencyinjection.modules.TaskListModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
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

