package com.fireflylearning.tasksummary.binding

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by Roll on 6/10/17.
 * Adapters for binding
 */
class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}