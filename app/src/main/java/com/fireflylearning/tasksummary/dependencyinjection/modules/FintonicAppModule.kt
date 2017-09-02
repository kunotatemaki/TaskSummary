package com.fireflylearning.tasksummary.dependencyinjection.modules

import android.content.Context
import com.fireflylearning.tasksummary.utils.logger.AndroidLoggerHelperImpl
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.network.logic.NetworkManagerAndroidImpl
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.utils.resources.ResourcesManagerAndroidImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Module
@Singleton
class FintonicAppModule(private val application: FireflyApp) {

    @Provides
    fun providesFintonicApp() : FireflyApp {
        return application
    }

    @Provides
    fun providesContext(): Context{
        return application.applicationContext
    }

    @Provides
    fun providesNetworkManager(network: NetworkManagerAndroidImpl): NetworkManager {
        return network
    }

    @Provides
    fun providesLogHelper(logger: AndroidLoggerHelperImpl): LoggerHelper {
        return logger
    }

    @Provides
    fun providesResourcesManager(resources: ResourcesManagerAndroidImpl): ResourcesManager {
        return resources
    }

}