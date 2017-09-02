package com.fireflylearning.tasksummary.utils.logger

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Created by Roll on 24/8/17.
 */
@Singleton
open class AndroidLoggerHelperImpl @Inject
internal constructor() : LoggerHelper {


    override fun d(theClass: Any, vararg messages: Any) {
        val tag = makeLogTag(theClass)
        log(tag, Log.DEBUG, null, *messages)
    }

    override fun e(theClass: Any, vararg messages: Any) {
        val tag = makeLogTag(theClass)
        log(tag, Log.ERROR, null, *messages)
    }

    override fun i(theClass: Any, vararg messages: Any) {
        val tag = makeLogTag(theClass)
        log(tag, Log.INFO, null, *messages)
    }

    override fun v(theClass: Any, vararg messages: Any) {
        val tag = makeLogTag(theClass)
        log(tag, Log.VERBOSE, null, *messages)
    }

    override fun w(theClass: Any, vararg messages: Any) {
        val tag = makeLogTag(theClass)
        log(tag, Log.WARN, null, *messages)
    }

    companion object {

        private val LOG_PREFIX = "rukia_"
        private val LOG_PREFIX_LENGTH = LOG_PREFIX.length
        private val MAX_LOG_TAG_LENGTH = 23

        private fun makeLogTag(`object`: Any): String {
            val str = `object`.javaClass.simpleName
            if (str.length > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
                return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1)
            }

            return LOG_PREFIX + str
        }

        private fun log(tag: String, level: Int, t: Throwable?, vararg messages: Any) {
            //if (Log.isLoggable(tag, level)) {
            val message: String
            if (t == null && messages.size == 1) {
                // handle this common case without the extra cost of creating a stringbuffer:
                message = messages[0].toString()
            } else {
                val sb = StringBuilder()

                for (m in messages) {
                    sb.append(m)
                }
                if (t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t))
                }
                message = sb.toString()
            }
            Log.println(level, tag, message)
            //}
        }
    }
}