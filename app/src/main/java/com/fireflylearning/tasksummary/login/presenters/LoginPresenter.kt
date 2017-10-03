package com.fireflylearning.tasksummary.login.presenters

import com.fireflylearning.tasksummary.login.views.LoginView

/**
 * Created by Roll on 1/9/17.
 */
interface LoginPresenter {

    fun attemptLogin(host: String, token: String)

}