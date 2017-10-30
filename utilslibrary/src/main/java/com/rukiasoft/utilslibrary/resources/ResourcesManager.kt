package com.rukiasoft.utilslibrary.resources

/**
 * Created by Roll on 31/8/17.
 * Class for load resources in the app
 */
interface ResourcesManager {
    fun getString(resId: Int): String

    fun getColor(resId: Int): Int

}