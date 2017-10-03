package com.fireflylearning.tasksummary.di.modules

import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.presenters.LoginPresenterAndroidImpl
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenterAndroidImpl
import com.fireflylearning.tasksummary.tasklist.views.TaskListActivity
import com.fireflylearning.tasksummary.tasklist.views.TaskListView
import com.rukiasoft.fintonictest.sherodetails.lifecycleobservers.LoginLifecycleObserverAndroidImpl
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
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