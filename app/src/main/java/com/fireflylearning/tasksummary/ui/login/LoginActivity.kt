package com.fireflylearning.tasksummary.ui.login

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivityLoginBinding
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.ui.tasklist.TaskListActivity
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import com.fireflylearning.tasksummary.vo.Status
import com.rukiasoft.utilslibrary.logger.LoggerHelper
import javax.inject.Inject


@CustomScopes.ActivityScope
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var log: LoggerHelper

    lateinit var mBinding: ActivityLoginBinding

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        //databinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mBinding.viewModel = viewModel

        mBinding.token.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == resources.getInteger(R.integer.ime_action)) {
                viewModel.attemptLogin(mBinding.host.text.toString(), mBinding.token.text.toString(),
                        mBinding.storeCredentials.isChecked)
                return@OnEditorActionListener true
            }
            false
        })

        viewModel.getHost().observe(this, Observer{ host ->
            if(!host?.valid!!){
                mBinding.host.error = host.message
            }
        })

        viewModel.getToken().observe(this,  Observer{token ->
            if(!token!!.valid) {
                mBinding.token.error = token.message
            }
        })

        viewModel.getResponse().observe(this, Observer{response ->
            when(response?.status){
                Status.SUCCESS -> goToTaskListView()
                Status.ERROR -> showErrorFromResponse(response.message!!)
                Status.LOADING -> showProgressBar()
                null -> return@Observer
            }

        })

    }

    /*override fun showErrorInHost(state: Boolean) {
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
    }*/

    private fun goToTaskListView() {
        val intent = Intent(this, TaskListActivity::class.java)
        intent.putExtra(FireflyConstants.HOST, ViewModelProviders.of(this).get(LoginViewModel::class.java).getHost().value?.data)
        intent.putExtra(FireflyConstants.SECRET_TOKEN, ViewModelProviders.of(this).get(LoginViewModel::class.java).getToken().value?.data)
        startActivity(intent)
        finish()
    }

    private fun showErrorFromResponse(error: String) {
        hideProgressBar()
        val dialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }
        dialog.setCancelable(true)
        dialog.setTitle(R.string.title_error)

        val message: String = error

        dialog.setMessage(message)

        dialog.setPositiveButton(resources.getString(R.string.accept)) { _, _ -> }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dialog.setOnDismissListener {
                log.d(this, "cerrado")
                viewModel.resetResponse()
            }
        }else{
            dialog.create().setOnDismissListener{
                log.d(this, "cerrado")
                viewModel.resetResponse()
            }
        }

        val alert = dialog.create()
        alert.show()

    }

    private fun showProgressBar() {
        mBinding.signInButton.visibility = View.INVISIBLE
        mBinding.loginProgress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mBinding.signInButton.visibility = View.VISIBLE
        mBinding.loginProgress.visibility = View.GONE
    }



    //endregion
}
