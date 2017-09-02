package com.rukiasoft.newrukiapics.preferences.implementations

import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.rukiasoft.newrukiapics.preferences.interfaces.PreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 28/7/17.
 */
@Singleton
class PreferencesManagerImpl @Inject constructor() : PreferencesManager {

    @Inject
    lateinit var prefs: AndroidPreferences

    override fun setSecretToken(token: String) {
        prefs.setStringFromPreferences(FireflyConstants.SECRET_TOKEN, token)
    }

    override fun deleteSecretToken() {
        prefs.deleteVarFromSharedPreferences(FireflyConstants.SECRET_TOKEN)
    }

    override fun getSecretToken(): String {
        return prefs.getStringFromPreferences(FireflyConstants.SECRET_TOKEN)
    }
}