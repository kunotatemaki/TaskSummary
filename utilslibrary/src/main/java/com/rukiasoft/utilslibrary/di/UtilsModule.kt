package com.rukiasoft.utilslibrary.di

import com.rukiasoft.utilslibrary.logger.AndroidLoggerHelperImpl
import com.rukiasoft.utilslibrary.logger.LoggerHelper
import com.rukiasoft.utilslibrary.resources.ResourcesManager
import com.rukiasoft.utilslibrary.resources.ResourcesManagerAndroidImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 * Module for the app component
 *
 */
@Module
@Singleton
class UtilsModule {



    @Provides
    fun providesLogHelper(logger: AndroidLoggerHelperImpl): LoggerHelper {
        return logger
    }

    @Provides
    fun providesResourcesManager(resources: ResourcesManagerAndroidImpl): ResourcesManager {
        return resources
    }


}