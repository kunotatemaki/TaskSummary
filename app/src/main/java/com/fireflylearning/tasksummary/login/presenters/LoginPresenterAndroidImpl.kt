package com.fireflylearning.tasksummary.login.presenters

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginView
import java.lang.ref.WeakReference
import javax.inject.Inject


/**
 * Created by Roll on 1/9/17.
 */
@CustomScopes.ActivityScope
class LoginPresenterAndroidImpl @Inject constructor(val mView: WeakReference<LoginView>): LoginPresenter {

}