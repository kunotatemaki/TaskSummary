package com.rukiasoft.utilslibrary.logger

/**
 * Created by Roll on 24/8/17.
 *
 */
interface LoggerHelper {
    fun d(theClass: Any, vararg messages: Any)

    fun e(theClass: Any, vararg messages: Any)

    fun w(theClass: Any, vararg messages: Any)

    fun i(theClass: Any, vararg messages: Any)

    fun v(theClass: Any, vararg messages: Any)
}