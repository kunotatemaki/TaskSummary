package com.rukiasoft.fintonictest.sherodetails.lifecycleobservers

import android.arch.lifecycle.LifecycleObserver
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.views.LoginView
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





}