package com.fireflylearning.tasksummary

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.fireflylearning.tasksummary.utils.logger.AndroidLoggerHelperImpl
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.resources.ResourcesManagerAndroidImpl
import com.fireflylearning.tasksummary.test.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Roll on 1/9/17.
 */
@RunWith(AndroidJUnit4::class)
class ResourcesTest {

    lateinit var context: Context
    val log: LoggerHelper = AndroidLoggerHelperImpl()
    lateinit var resources: ResourcesManagerAndroidImpl

    @Before
    fun setUp(){
        context = InstrumentationRegistry.getContext()
        resources = ResourcesManagerAndroidImpl(log, context)
    }

    @Test
    fun checkIfLoadStringFromStringsFile(){
        val string = resources.getString(R.string.test_string)
        Assert.assertTrue(string == "This string is used to test resources class")
    }
}


