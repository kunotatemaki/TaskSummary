package com.fireflylearning.tasksummary.utils.preferences

import android.content.Context
import com.fireflylearning.tasksummary.security.Encryption
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

    @Inject
    lateinit var context: Context

    override fun setSecretToken(token: String) {
        prefs.setStringFromPreferences(FireflyConstants.SECRET_TOKEN,
                Encryption.encryptString(context, token, FireflyConstants.KEYSTORE_ALIAS))
    }

    override fun deleteSecretToken() {
        prefs.deleteVarFromSharedPreferences(FireflyConstants.SECRET_TOKEN)
    }

    override fun getSecretToken(): String {
        val token = prefs.getStringFromPreferences(FireflyConstants.SECRET_TOKEN)
        return Encryption.decryptString(token, FireflyConstants.KEYSTORE_ALIAS)

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

    override fun getFetchDate(): Long {
        return prefs.getLongFromPreferences(FireflyConstants.FETCHED_DATE)
    }

    override fun setFetchDate(fetchDate: Long) {
        prefs.setLongFromPreferences(FireflyConstants.FETCHED_DATE, fetchDate)
    }

    override fun deleteFetchDate() {
        prefs.deleteVarFromSharedPreferences(FireflyConstants.FETCHED_DATE)
    }
}