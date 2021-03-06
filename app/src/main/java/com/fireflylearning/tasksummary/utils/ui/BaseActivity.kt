package com.fireflylearning.tasksummary.utils.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.widget.TextView
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Roll on 31/8/17.
 */

open class BaseActivity : DaggerAppCompatActivity(){



    protected fun setToolbar(toolbar: Toolbar, backArrow: Boolean) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(backArrow)
            try {
                val f = toolbar::class.java.getDeclaredField("mTitleTextView")
                f.isAccessible = true
                val titleTextView = f.get(toolbar) as TextView
                titleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
                titleTextView.isFocusable = true
                titleTextView.isFocusableInTouchMode = true
                titleTextView.requestFocus()
                titleTextView.setSingleLine(true)
                titleTextView.isSelected = true
                titleTextView.marqueeRepeatLimit = -1
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }

    }

}
