package com.fireflylearning.tasksummary.di.modules


import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.rukiasoft.fintonictest.sherodetails.lifecycleobservers.LoginLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.presenters.LoginPresenterAndroidImpl
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.login.views.LoginView
import dagger.Module
import dagger.Binds



/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
abstract class LoginModule {

    @Binds
    internal abstract fun bindMainView(act: LoginActivity): LoginView

    @Binds
    internal abstract fun bindLoginPresenter(presenterAndroidImpl: LoginPresenterAndroidImpl) : LoginPresenter


    @Binds
    internal abstract fun bindLoginLifecycleObserver(observer: LoginLifecycleObserverAndroidImpl): LoginLifecycleObserver


}