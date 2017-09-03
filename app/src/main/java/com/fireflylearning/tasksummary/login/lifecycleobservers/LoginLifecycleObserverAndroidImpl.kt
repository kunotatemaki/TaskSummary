package com.rukiasoft.fintonictest.sherodetails.lifecycleobservers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.views.LoginView
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class LoginLifecycleObserverAndroidImpl @Inject constructor(val mView: WeakReference<LoginView>)
    : LoginLifecycleObserver, LifecycleObserver {

    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var log: LoggerHelper

    init {
        mView.safe{
            mView.get()!!.addLifecycleObserver(this@LoginLifecycleObserverAndroidImpl)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun actionInOnCreate() {
        log.d(this, "observer's oncreate")

        //force presenter to observe data (repos and user)
        if (presenter is MyLivedataObserver) {
            mView.safe {
                mView.get()!!.getLiveStatus().addObserverToLivedata(mView.get()!! as LifecycleRegistryOwner, presenter as MyLivedataObserver)
            }
        }
    }





}