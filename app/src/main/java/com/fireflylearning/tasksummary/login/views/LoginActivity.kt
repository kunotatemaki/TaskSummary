package com.fireflylearning.tasksummary.login.views

import android.arch.lifecycle.LifecycleObserver
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivityLoginBinding
import com.fireflylearning.tasksummary.dependencyinjection.modules.LoginModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
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

    lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dependency injection
        (application as FireflyApp).mComponent
                .getLoginSubcomponent(LoginModule(this)).inject(this)

        //databinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login, GlideBindingComponent())

        mBinding.presenter = presenter


    }


    override fun addLifecycleObserver(observer: LoginLifecycleObserver) {
        if(observer is LifecycleObserver){
            lifecycle.addObserver(observer)
        }
    }
}
