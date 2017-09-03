package com.fireflylearning.tasksummary.login.views

import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.utils.FireflyConstants

/**
 * Created by Roll on 31/8/17.
 */
interface LoginView {

    fun addLifecycleObserver(observer: LoginLifecycleObserver)

    fun showErrorInHost(state: Boolean)

    fun showErrorInToken(state: Boolean)

    fun goToTaskListView()

    fun getLiveStatus(): CustomLiveData<FireflyConstants.TokenError>

    fun showErrorFromResponse(error: FireflyConstants.TokenError)

    fun showProgressBar()

    fun hideProgressBar()

    fun storeTokenInChache(token: String)

    fun storeHostInChache(host: String)

}