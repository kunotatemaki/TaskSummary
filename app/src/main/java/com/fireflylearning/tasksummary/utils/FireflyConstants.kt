package com.fireflylearning.tasksummary.utils

/**
 * Created by Roll on 31/8/17.
 */
object FireflyConstants {

    val DEVICE_ID : String = "AndroidApp"
    val SECRET_TOKEN: String = "secret_token"
    val HOST: String = "host"

    enum class TokenError(val value: Int) {
        NO_OP (0),
        NETWORK_ERROR (1),
        HOST_ERROR (2),
        INVALID_TOKEN(3),
        RESPONSE_OK (4)
    }

    val DATABASE_NAME: String = "firefly-database"

}