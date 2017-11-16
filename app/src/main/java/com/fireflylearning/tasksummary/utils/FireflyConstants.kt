package com.fireflylearning.tasksummary.utils

/**
 * Created by Roll on 31/8/17.
 */
object FireflyConstants {

    val DEVICE_ID : String = "AndroidApp"
    val SECRET_TOKEN: String = "secret_token"
    val HOST: String = "host2"
    val URL: String = "task_url"
    val FETCHED_DATE: String = "fetched_date"

    enum class TokenError(val value: Int) {
        NO_OP (0),
        NETWORK_ERROR (1),
        HOST_ERROR (2),
        INVALID_TOKEN(3),
        RESPONSE_OK (4)
    }

    enum class TaskOrigin(val value: Int) {
        FROM_NETWORK (0),
        FROM_DB (1)
    }

    val DATABASE_NAME: String = "firefly-database"
    val KEYSTORE_ALIAS: String = "firefly-key"

}