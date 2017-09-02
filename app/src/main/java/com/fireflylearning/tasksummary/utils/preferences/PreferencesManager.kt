package com.rukiasoft.newrukiapics.preferences.interfaces

/**
 * Created by Roll on 28/7/17.
 */
interface PreferencesManager {
    fun getSecretToken(): String
    fun setSecretToken(token: String)
    fun deleteSecretToken()

}