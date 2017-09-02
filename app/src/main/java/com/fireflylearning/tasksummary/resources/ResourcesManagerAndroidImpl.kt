package com.fireflylearning.tasksummary.resources

import android.content.Context
import android.support.annotation.VisibleForTesting
import javax.inject.Singleton
import javax.inject.Inject
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper



/**
 * Created by Roll on 31/8/17.
 */
@Singleton
class ResourcesManagerAndroidImpl @Inject constructor(): ResourcesManager {

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    lateinit var context: Context

    @VisibleForTesting
    constructor(log: LoggerHelper, context: Context) : this() {
        this.log = log
        this.context = context
    }

    override fun getString(resId: Int): String {
        return context.getString(resId)

    }

}