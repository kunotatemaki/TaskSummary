package com.fireflylearning.tasksummary.utils.preferences

/**
 * Created by Roll on 28/7/17.
 */
interface PreferencesManager {
    fun getSecretToken(): String
    fun setSecretToken(token: String)
    fun deleteSecretToken()

    fun getHost(): String
    fun setHost(host: String)
    fun deleteHost()

    fun getFetchDate(): Long
    fun setFetchDate(host: Long)
    fun deleteFetchDate()



}