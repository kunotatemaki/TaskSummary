package com.fireflylearning.tasksummary.model

import android.arch.lifecycle.LifecycleRegistryOwner
import com.fireflylearning.tasksummary.tasklist.livedataobservers.MyLivedataObserver


/**
 * Created by Roll on 24/8/17.
 */
interface CustomLiveData<T> {

    fun setLivedataValue(value: T)

    fun getLivedataValue(): T?

    fun addObserverToLivedata(lifecycleRegistryOwner: LifecycleRegistryOwner, observer: MyLivedataObserver)

}