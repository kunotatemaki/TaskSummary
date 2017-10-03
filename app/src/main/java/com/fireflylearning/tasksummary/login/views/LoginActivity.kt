package com.fireflylearning.tasksummary.login.views

import android.app.AlertDialog
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivityLoginBinding
import com.fireflylearning.tasksummary.di.modules.LoginModule
import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.viewmodels.LoginViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.tasklist.views.TaskListActivity
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import com.fireflylearning.tasksummary.utils.ui.GlideBindingComponent
import javax.inject.Inject


@CustomScopes.ActivityScope
class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var observer: LoginLifecycleObserver

    lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //databinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login, GlideBindingComponent())

        mBinding.presenter = presenter

        mBinding.token.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == resources.getInteger(R.integer.ime_action)) {
                presenter.attemptLogin(mBinding.host.text.toString(), mBinding.token.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

    }


    // region LOGIN VIEW
    override fun addLifecycleObserver(observer: LoginLifecycleObserver) {
        if(observer is LifecycleObserver){
            lifecycle.addObserver(observer)
        }
    }

    override fun showErrorInHost(state: Boolean) {
        when(state){
            true -> showErrorInTextField(mBinding.host)
            false -> removeErrorInTextField(mBinding.host)
        }

    }

    override fun showErrorInToken(state: Boolean) {
        when(state){
            true -> showErrorInTextField(mBinding.token)
            false -> removeErrorInTextField(mBinding.token)
        }
    }

    private fun showErrorInTextField(text: EditText){
        text.error = getString(R.string.error_field_required)
        text.requestFocus()
    }

    private fun removeErrorInTextField(text: EditText){
        text.error = null
    }

    override fun goToTaskListView() {
        val intent = Intent(this, TaskListActivity::class.java)
        intent.putExtra(FireflyConstants.HOST, ViewModelProviders.of(this).get(LoginViewModel::class.java).host)
        intent.putExtra(FireflyConstants.SECRET_TOKEN, ViewModelProviders.of(this).get(LoginViewModel::class.java).token)
        startActivity(intent)
        finish()
    }

    override fun showErrorFromResponse(error: FireflyConstants.TokenError) {
        val dialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }
        dialog.setCancelable(true)
        dialog.setTitle(R.string.title_error)

        val message: String = when (error) {
            FireflyConstants.TokenError.NETWORK_ERROR -> resources.getString(R.string.error_internet_connection)
            FireflyConstants.TokenError.HOST_ERROR -> resources.getString(R.string.error_invalid_host)
            FireflyConstants.TokenError.INVALID_TOKEN -> resources.getString(R.string.error_invalid_token)
            else -> ""
        }

        dialog.setMessage(message)

        dialog.setPositiveButton(resources.getString(R.string.accept)) { _, _ -> }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dialog.setOnDismissListener {
                log.d(this, "cerrado")
                getLiveStatus().setLivedataValue(FireflyConstants.TokenError.NO_OP)
            }
        }else{
            dialog.create().setOnDismissListener{
                log.d(this, "cerrado")
                getLiveStatus().setLivedataValue(FireflyConstants.TokenError.NO_OP)
            }
        }

        val alert = dialog.create()
        alert.show()

    }

    override fun getLiveStatus(): CustomLiveData<FireflyConstants.TokenError> {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java).status
    }

    override fun showProgressBar() {
        mBinding.signInButton.visibility = View.INVISIBLE
        mBinding.loginProgress.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding.signInButton.visibility = View.VISIBLE
        mBinding.loginProgress.visibility = View.GONE
    }

    override fun storeTokenInChache(token: String) {
        ViewModelProviders.of(this).get(LoginViewModel::class.java).token = token
    }

    override fun storeHostInChache(host: String) {
        ViewModelProviders.of(this).get(LoginViewModel::class.java).host = host
    }

    override fun storeCredentials(): Boolean {
        return mBinding.storeCredentials.isChecked
    }

    override fun getTokenFromChache(): String {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java).token
    }

    override fun getHostFromChache(): String {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java).host
    }

    //endregion
}

//todo hacer lo del texeditor