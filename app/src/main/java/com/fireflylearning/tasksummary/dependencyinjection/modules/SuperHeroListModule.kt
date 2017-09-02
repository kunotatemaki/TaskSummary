package com.fireflylearning.tasksummary.dependencyinjection.modules

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.sheroeslist.lifecycleobservers.SuperHeroListLifecycleObserver
import com.fireflylearning.tasksummary.sheroeslist.lifecycleobservers.SuperHeroListLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.sheroeslist.presenters.SuperHeroListPresenter
import com.fireflylearning.tasksummary.sheroeslist.presenters.SuperHeroListPresenterAndroidImpl
import com.fireflylearning.tasksummary.sheroeslist.views.SuperHeroListView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
class SuperHeroListModule(private var mView: SuperHeroListView) {

    @Provides
    fun providesSuperHeroListPresenter(presenterAndroidImpl: SuperHeroListPresenterAndroidImpl) : SuperHeroListPresenter {
        return presenterAndroidImpl
    }

    @Provides
    fun providesSuperHeroListView(): WeakReference<SuperHeroListView>{
        return WeakReference(mView)
    }


    @Provides
    fun providesSuperHeroListLifecycleObserver(observer: SuperHeroListLifecycleObserverAndroidImpl): SuperHeroListLifecycleObserver {
        return observer
    }

}