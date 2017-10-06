package com.fireflylearning.tasksummary.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.AppExecutors
import com.fireflylearning.tasksummary.persistence.PersistenceManager
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import java.util.*
import javax.inject.Inject

/**
 * Created by Roll on 5/10/17.
 * view model common for activities
 */
class ActivityViewModel @Inject constructor(private val preferencesManager: PreferencesManager): ViewModel() {

    private val sessionStatus: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var persistence:PersistenceManager

    @Inject
    lateinit var appExecutors: AppExecutors
    init {
        sessionStatus.value = true
    }

    fun getSessionStatus(): LiveData<Boolean> {
        return sessionStatus
    }

    fun closeSession() {
        preferencesManager.deleteSecretToken()
        preferencesManager.deleteHost()
        sessionStatus.value = false

    }
}