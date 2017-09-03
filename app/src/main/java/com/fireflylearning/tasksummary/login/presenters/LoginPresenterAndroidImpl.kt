package com.fireflylearning.tasksummary.login.presenters

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginView
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import java.lang.ref.WeakReference
import javax.inject.Inject


/**
 * Created by Roll on 1/9/17.
 */
@CustomScopes.ActivityScope
class LoginPresenterAndroidImpl @Inject constructor(val mView: WeakReference<LoginView>): LoginPresenter {


    @Inject
    lateinit var resources: ResourcesManager

    @Inject
    lateinit var log: LoggerHelper

    override fun attemptLogin(secret: String) {
        log.d(this, "intento de login: " + secret)
    }

}