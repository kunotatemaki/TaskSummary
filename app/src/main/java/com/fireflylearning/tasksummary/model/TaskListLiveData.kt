package com.fireflylearning.tasksummary.model

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import javax.inject.Inject


/**
 * Created by Roll on 24/8/17.
 */
class TaskListLiveData @Inject constructor(): MutableLiveData<MutableList<Task>>(), CustomLiveData<MutableList<Task>> {

    private var taskOrigin: FireflyConstants.TaskOrigin = FireflyConstants.TaskOrigin.FROM_DB

    override fun setLivedataValue(value: MutableList<Task>) {
        this.value = value
    }

    override fun getLivedataValue(): MutableList<Task>? {
        return this.value
    }

    override fun addObserverToLivedata(lifecycleRegistryOwner: LifecycleOwner, observer: MyLivedataObserver) {

        this.observe(lifecycleRegistryOwner,
                Observer<MutableList<Task>> { Tasks -> observer.handleChangesInObservedTasks(Tasks!!, taskOrigin) })

    }

    override fun setTaskOrigin(origin: FireflyConstants.TaskOrigin) {
        taskOrigin = origin
    }
}