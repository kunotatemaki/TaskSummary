package com.fireflylearning.tasksummary.utils.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Roll on 31/8/17.
 */
class GlideBindingAdapters {

    @BindingAdapter("app:imageList")
    fun setImageUrlInList(view: ImageView, url: String?) {
        //circle images
        url?.let {
            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .circleCrop())
                    .into(view)
        }
    }

    @BindingAdapter("app:imageDetail")
    fun setImageUrlInDetails(view: ImageView, url: String?) {
        //circle images
        url?.let {
            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(view)
        }
    }
}