package com.fireflylearning.tasksummary.tasklist.lifecycleobservers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.views.TaskListView
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListLifecycleObserverAndroidImpl @Inject constructor(private var taskListView: TaskListView?)
    : TaskListLifecycleObserver, LifecycleObserver {

    private val mView: WeakReference<TaskListView> = WeakReference(taskListView!!)

    @Inject
    lateinit var presenter: TaskListPresenter

    @Inject
    lateinit var log: LoggerHelper


    init {
        mView.safe{
            mView.get()!!.addLifecycleObserver(this@TaskListLifecycleObserverAndroidImpl)
        }
        taskListView = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun actionInOnCreate() {
        log.d(this, "observer's oncreate")

        //force presenter to observe data (repos and user)
        if (presenter is MyLivedataObserver) {
            mView.safe {
                mView.get()!!.getLiveTaks().addObserverToLivedata(mView.get()!! as LifecycleOwner, presenter as MyLivedataObserver)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun actionInOnResume() {
        log.d(this, "observer's onstart")
        presenter.loadTasksFromNetwork()
    }



}