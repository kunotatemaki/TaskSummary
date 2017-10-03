package com.fireflylearning.tasksummary.di

import android.app.Application
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.di.modules.FireflyAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        FireflyAppModule::class,
        ActivityBuilder::class))
interface FireflyAppComponent : AndroidInjector<FireflyApp>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: FireflyApp): Builder

        fun build(): FireflyAppComponent
    }

    override fun inject(fireflyApp: FireflyApp)

}