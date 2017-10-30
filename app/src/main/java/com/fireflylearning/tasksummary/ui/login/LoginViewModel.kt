package com.fireflylearning.tasksummary.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.repository.NetworkLogin
import com.fireflylearning.tasksummary.vo.CheckField
import com.fireflylearning.tasksummary.vo.Resource
import com.rukiasoft.utilslibrary.resources.ResourcesManager
import javax.inject.Inject

/**
 * Created by Roll on 3/9/17.
 * view model for loginOld screen
 */
class LoginViewModel @Inject constructor(private val resourcesManager: ResourcesManager,
                                         val network: NetworkLogin): ViewModel() {

    private val host: MutableLiveData<CheckField<String>> = MutableLiveData()
    private val token: MutableLiveData<CheckField<String>> = MutableLiveData()

    private var response: LiveData<Resource<Boolean>>

    init {
        host.value = CheckField.valid(resourcesManager.getString(R.string.base_host))
        token.value = CheckField.valid("")
        response = network.asLiveData()
    }

    fun getToken(): LiveData<CheckField<String>> = token
    fun getHost(): LiveData<CheckField<String>> = host
    fun getResponse(): LiveData<Resource<Boolean>> = response

    fun resetResponse(){
        network.resetLiveData()
    }

    fun attemptLogin(host: String, token: String, remainLogged: Boolean) {

        var loginOK = true

        if (TextUtils.isEmpty(token)) {
            this.token.value = CheckField.invalid(token, resourcesManager.getString(R.string.error_field_required))
            loginOK = false
        }

        if (TextUtils.isEmpty(host)) {
            this.host.value = CheckField.invalid(host, resourcesManager.getString(R.string.error_field_required))
            loginOK = false
        }

        if(loginOK) {

            network.login(host, token, remainLogged)
        }

    }

}