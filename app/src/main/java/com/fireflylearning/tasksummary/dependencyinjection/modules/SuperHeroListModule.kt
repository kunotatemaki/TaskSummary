package com.fireflylearning.tasksummary.dependencyinjection.modules

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenterAndroidImpl
import com.fireflylearning.tasksummary.tasklist.views.TaksListView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
class SuperHeroListModule(private var mView: TaksListView) {

    @Provides
    fun providesSuperHeroListPresenter(presenterAndroidImpl: TaskListPresenterAndroidImpl) : TaskListPresenter {
        return presenterAndroidImpl
    }

    @Provides
    fun providesSuperHeroListView(): WeakReference<TaksListView>{
        return WeakReference(mView)
    }


    @Provides
    fun providesSuperHeroListLifecycleObserver(observer: TaskListLifecycleObserverAndroidImpl): TaskListLifecycleObserver {
        return observer
    }

}