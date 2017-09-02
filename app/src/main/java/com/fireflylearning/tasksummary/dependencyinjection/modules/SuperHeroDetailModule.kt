package com.fireflylearning.tasksummary.dependencyinjection.modules

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.sherodetails.lifecycleobservers.SuperHeroDetailLifecycleObserver
import com.fireflylearning.tasksummary.sherodetails.lifecycleobservers.SuperHeroDetailLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.sherodetails.presenters.SuperHeroDetailPresenter
import com.fireflylearning.tasksummary.sherodetails.presenters.SuperHeroDetailPresenterAndroidImpl
import com.fireflylearning.tasksummary.sherodetails.views.SuperHeroDetailView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
class SuperHeroDetailModule(private var mView: SuperHeroDetailView) {

    @Provides
    fun providesSuperHeroDetailPresenter(presenterAndroidImpl: SuperHeroDetailPresenterAndroidImpl) : SuperHeroDetailPresenter {
        return presenterAndroidImpl
    }

    @Provides
    fun providesSuperHeroDetailView(): WeakReference<SuperHeroDetailView> {
        return WeakReference(mView)
    }


    @Provides
    fun providesSuperHeroDetailLifecycleObserver(observer: SuperHeroDetailLifecycleObserverAndroidImpl): SuperHeroDetailLifecycleObserver {
        return observer
    }

}