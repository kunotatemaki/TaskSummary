package com.rukiasoft.utilslibrary.resources

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import javax.inject.Singleton
import javax.inject.Inject
import com.rukiasoft.utilslibrary.logger.LoggerHelper


/**
 * Created by Roll on 31/8/17.
 */
@Singleton
class ResourcesManagerAndroidImpl @Inject constructor(var context: Context, var loggerHelper: LoggerHelper): ResourcesManager {


    override fun getString(resId: Int): String {
        return context.getString(resId)

    }

    override fun getColor(resId: Int): Int {
        return ResourcesCompat.getColor(context.resources, resId, null)
    }

}