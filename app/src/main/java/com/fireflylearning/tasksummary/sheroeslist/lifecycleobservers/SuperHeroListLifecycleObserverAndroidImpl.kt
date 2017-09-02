package com.fireflylearning.tasksummary.sheroeslist.lifecycleobservers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.sheroeslist.livedataobservers.MyLivedataObserver
import com.fireflylearning.tasksummary.sheroeslist.presenters.SuperHeroListPresenter
import com.fireflylearning.tasksummary.sheroeslist.views.SuperHeroListView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class SuperHeroListLifecycleObserverAndroidImpl @Inject constructor(val mView: WeakReference<SuperHeroListView>)
    : SuperHeroListLifecycleObserver, LifecycleObserver {

    @Inject
    lateinit var presenter: SuperHeroListPresenter

    @Inject
    lateinit var log: LoggerHelper

    init {
        mView.safe{
            mView.get()!!.addLifecycleObserver(this@SuperHeroListLifecycleObserverAndroidImpl)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun actionInOnCreate() {
        log.d(this, "observer's oncreate")

        //force presenter to observe data (repos and user)
        if (presenter is MyLivedataObserver) {
            mView.safe {
                mView.get()!!.getLiveSuperHeroes().addObserverToLivedata(mView.get()!! as LifecycleRegistryOwner, presenter as MyLivedataObserver)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun actionInOnResume() {
        log.d(this, "observer's onstart")
        presenter.loadSuperHeroes()
    }



}