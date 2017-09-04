package com.fireflylearning.tasksummary.model

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import com.fireflylearning.tasksummary.utils.FireflyConstants
import javax.inject.Inject

/**
 * Created by Roll on 24/8/17.
 */
class LoginLiveData @Inject constructor(): MutableLiveData<FireflyConstants.TokenError>(), CustomLiveData<FireflyConstants.TokenError> {

    override fun setLivedataValue(value: FireflyConstants.TokenError) {
        this.value = value
    }

    override fun getLivedataValue(): FireflyConstants.TokenError? {
        this.value?.let {
            return this.value
        }
        return FireflyConstants.TokenError.NO_OP
    }

    override fun addObserverToLivedata(lifecycleRegistryOwner: LifecycleRegistryOwner, observer: MyLivedataObserver) {

        this.observe(lifecycleRegistryOwner as LifecycleOwner,
                Observer<FireflyConstants.TokenError> { status -> observer.handleChangesInObservedStatus(status!!) })

    }

    override fun forceStorageInLocalDatabaseOnNewData(force: Boolean) {

    }
}