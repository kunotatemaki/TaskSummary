package com.fireflylearning.tasksummary.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.persistence.PersistenceManager
import com.fireflylearning.tasksummary.persistence.PersistenceManagerImplAndroid
import com.fireflylearning.tasksummary.persistence.daos.TaskDao
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 * Module for the app component
 *
 */
@Module(includes = arrayOf(ViewModelModule::class))
@Singleton
class FireflyAppModule {

    @Singleton
    @Provides
    fun provideDb(app: FireflyApp): FireflyDatabase {
        return Room.databaseBuilder(app,
                FireflyDatabase::class.java, FireflyConstants.DATABASE_NAME)
                .build()

    }

    @Singleton
    @Provides
    fun provideTaskDao(db: FireflyDatabase): TaskDao {
        return db.taskDao()
    }


    @Provides
    fun providesContext(application: FireflyApp): Context{
        return application.applicationContext
    }

    @Provides
    fun providesPreferencesManager(preferences: PreferencesManagerImpl): PreferencesManager {
        return preferences
    }

    @Provides
    fun providesPersistenceManager(persistence: PersistenceManagerImplAndroid): PersistenceManager {
        return persistence
    }
}