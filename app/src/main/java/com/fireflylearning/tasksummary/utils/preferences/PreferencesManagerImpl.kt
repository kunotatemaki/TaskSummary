package com.fireflylearning.tasksummary.utils.preferences

import com.fireflylearning.tasksummary.utils.FireflyConstants
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

    override fun getHost(): String {
        return prefs.getStringFromPreferences(FireflyConstants.HOST)
    }

    override fun setHost(host: String) {
        prefs.setStringFromPreferences(FireflyConstants.HOST, host)
    }

    override fun deleteHost() {
        prefs.deleteVarFromSharedPreferences(FireflyConstants.HOST)
    }
}