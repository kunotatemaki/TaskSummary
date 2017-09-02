package com.fireflylearning.tasksummary.tasklist.lifecycleobservers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.livedataobservers.MyLivedataObserver
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.views.TaksListView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListLifecycleObserverAndroidImpl @Inject constructor(val mView: WeakReference<TaksListView>)
    : TaskListLifecycleObserver, LifecycleObserver {

    @Inject
    lateinit var presenter: TaskListPresenter

    @Inject
    lateinit var log: LoggerHelper

    init {
        mView.safe{
            mView.get()!!.addLifecycleObserver(this@TaskListLifecycleObserverAndroidImpl)
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