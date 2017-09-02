package com.fireflylearning.tasksummary.model

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.fireflylearning.tasksummary.sheroeslist.livedataobservers.MyLivedataObserver
import javax.inject.Inject


/**
 * Created by Roll on 24/8/17.
 */
class TaskListLiveData @Inject constructor(): MutableLiveData<MutableList<Task>>(), CustomLiveData<MutableList<Task>> {

    override fun setLivedataValue(value: MutableList<Task>) {
        this.value = value
    }

    override fun getLivedataValue(): MutableList<Task>? {
        return this.value
    }

    override fun addObserverToLivedata(lifecycleRegistryOwner: LifecycleRegistryOwner, observer: MyLivedataObserver) {

        this.observe(lifecycleRegistryOwner as LifecycleOwner,
                Observer<MutableList<Task>> { SuperHeroes -> observer.handleChangesInObservedSuperHeroes(SuperHeroes!!) })

    }
}