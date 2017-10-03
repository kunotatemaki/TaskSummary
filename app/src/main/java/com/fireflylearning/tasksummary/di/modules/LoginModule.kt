package com.fireflylearning.tasksummary.di.modules


import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.rukiasoft.fintonictest.sherodetails.lifecycleobservers.LoginLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.presenters.LoginPresenterAndroidImpl
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.login.views.LoginView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
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