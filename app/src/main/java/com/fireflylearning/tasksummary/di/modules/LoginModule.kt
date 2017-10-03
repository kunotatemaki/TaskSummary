package com.fireflylearning.tasksummary.di.modules


import com.fireflylearning.tasksummary.di.scopes.CustomScopes
import com.fireflylearning.tasksummary.login.lifecycleobservers.LoginLifecycleObserver
import com.rukiasoft.fintonictest.sherodetails.lifecycleobservers.LoginLifecycleObserverAndroidImpl
import com.fireflylearning.tasksummary.login.presenters.LoginPresenter
import com.fireflylearning.tasksummary.login.presenters.LoginPresenterAndroidImpl
import com.fireflylearning.tasksummary.login.views.LoginView
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */
@Module
@CustomScopes.ActivityScope
class LoginModule(private var mView: LoginView) {

    @Provides
    fun providesLoginPresenter(presenterAndroidImpl: LoginPresenterAndroidImpl) : LoginPresenter {
        return presenterAndroidImpl
    }

    @Provides
    fun providesLoginView(): WeakReference<LoginView> {
        return WeakReference(mView)
    }


    @Provides
    fun providesLoginLifecycleObserver(observer: LoginLifecycleObserverAndroidImpl): LoginLifecycleObserver {
        return observer
    }

}