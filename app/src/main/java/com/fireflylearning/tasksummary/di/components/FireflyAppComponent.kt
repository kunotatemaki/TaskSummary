package com.fireflylearning.tasksummary.di.components

import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.di.modules.ActivityBuilder
import com.fireflylearning.tasksummary.di.modules.FireflyAppModule
import com.rukiasoft.utilslibrary.di.UtilsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        FireflyAppModule::class,
        UtilsModule::class,
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