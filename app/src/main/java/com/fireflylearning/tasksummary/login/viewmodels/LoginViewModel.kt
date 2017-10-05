package com.fireflylearning.tasksummary.login.viewmodels

import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.LoginLiveData
import com.fireflylearning.tasksummary.utils.FireflyConstants
import javax.inject.Inject

/**
 * Created by Roll on 3/9/17.
 */
class LoginViewModel @Inject constructor(): ViewModel() {
    var host: String = ""
    var token: String = ""
    var status: CustomLiveData<FireflyConstants.TokenError> = LoginLiveData()
}