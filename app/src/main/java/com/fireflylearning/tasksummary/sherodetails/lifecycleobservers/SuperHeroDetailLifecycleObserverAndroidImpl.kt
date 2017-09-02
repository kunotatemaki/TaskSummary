package com.fireflylearning.tasksummary.sherodetails.lifecycleobservers

import android.arch.lifecycle.LifecycleObserver
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.sherodetails.presenters.SuperHeroDetailPresenter
import com.fireflylearning.tasksummary.sherodetails.views.SuperHeroDetailView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class SuperHeroDetailLifecycleObserverAndroidImpl @Inject constructor(val mView: WeakReference<SuperHeroDetailView>)
    : SuperHeroDetailLifecycleObserver, LifecycleObserver {

    @Inject
    lateinit var presenter: SuperHeroDetailPresenter

    @Inject
    lateinit var log: LoggerHelper

    init {
        mView.safe{
            mView.get()!!.addLifecycleObserver(this@SuperHeroDetailLifecycleObserverAndroidImpl)
        }
    }





}