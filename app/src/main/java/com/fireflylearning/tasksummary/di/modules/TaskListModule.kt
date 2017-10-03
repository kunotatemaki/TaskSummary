package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenterAndroidImpl
import com.fireflylearning.tasksummary.tasklist.views.TaskListView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
class TaskListModule(private var mView: TaskListView) {

    @Provides
    fun providesTaskListPresenter(presenterAndroidImpl: TaskListPresenterAndroidImpl) : TaskListPresenter {
        return presenterAndroidImpl
    }

    @Provides
    fun providesTaskListView(): WeakReference<TaskListView>{
        return WeakReference(mView)
    }


    @Provides
    fun providesTaskListLifecycleObserver(observer: TaskListLifecycleObserverAndroidImpl): TaskListLifecycleObserver {
        return observer
    }

}