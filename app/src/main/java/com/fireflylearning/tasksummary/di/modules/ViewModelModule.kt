package com.fireflylearning.tasksummary.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fireflylearning.tasksummary.di.interfaces.ViewModelKey
import com.fireflylearning.tasksummary.fireflyviewmodel.FireflyViewModelFactory
import com.fireflylearning.tasksummary.login.viewmodels.LoginViewModel
import com.fireflylearning.tasksummary.tasklist.TaskListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Roll on 4/10/17.
 * module for inject view models (if needed)
 */

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel::class)
    internal abstract fun bindTaskListViewModel(taskListViewModel: TaskListViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: FireflyViewModelFactory): ViewModelProvider.Factory
}

