package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenterAndroidImpl
import com.fireflylearning.tasksummary.tasklist.views.TaskListActivity
import com.fireflylearning.tasksummary.tasklist.views.TaskListView
import dagger.Module
import dagger.Binds



/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
abstract class TaskListModule {

    @Binds
    internal abstract fun bindMainView(act: TaskListActivity): TaskListView

    @Binds
    internal abstract fun bindTaskListPresenter(presenterAndroidImpl: TaskListPresenterAndroidImpl) : TaskListPresenter


    @Binds
    internal abstract fun bindTaskListLifecycleObserver(observer: TaskListLifecycleObserverAndroidImpl): TaskListLifecycleObserver


}