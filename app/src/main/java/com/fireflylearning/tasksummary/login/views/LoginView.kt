package com.fireflylearning.tasksummary.login.views

import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface LoginView {

    fun addLifecycleObserver(observer: LoginLifecycleObserver)

    fun showErrorInHost()

    fun showErrorInToken()

    fun goToTaskListView()

    fun showErrorFromResponse()

}