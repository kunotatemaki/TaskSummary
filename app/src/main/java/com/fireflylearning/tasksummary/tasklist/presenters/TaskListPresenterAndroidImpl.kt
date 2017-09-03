package com.fireflylearning.tasksummary.tasklist.presenters

import android.support.annotation.VisibleForTesting
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.views.TaksListView
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListPresenterAndroidImpl @Inject constructor(val mView: WeakReference<TaksListView>)
    : TaskListPresenter, MyLivedataObserver {

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    lateinit var network: NetworkManager

    @Inject
    lateinit var resources: ResourcesManager

    @Inject
    lateinit var preferences: PreferencesManager

    @VisibleForTesting
    constructor(resources: ResourcesManager, log: LoggerHelper, mView: WeakReference<TaksListView>,
                network: NetworkManager) : this(mView) {
        this.resources = resources
        this.log = log
        this.network = network

    }


    override fun loadTasks() {
        mView.safe {
            val myView = mView.get()!!

            if(myView.getLiveTaks().getLivedataValue()?.isEmpty() == false){
                //no hace falta forzar a la vista a cargar los datos de la caché, porque
                //el livedata notificará a su observador (this) de su último valor cuando se suscriba
                //así que se llamará a handle... y se actualizará ahí
                return@safe
            }else{
                mView.get()!!.showLoader()
                //todo meter aquí la clave y el host que pase en el intent
                val host: String = resources.getString(R.string.base_host)
                network.getListOfTasks(mView.get()!!.getHostFromChache(),
                        mView.get()!!.getTokenFromChache(),
                        myView.getLiveTaks())
            }
        }
    }

    override fun handleChangesInObservedTasks(tasks: MutableList<Task>) {
        mView.safe {
            val myView = mView.get()!!
            myView.hideLoader()
            if(tasks.size == 0){
                myView.showEmptyList(resources.getString(R.string.no_superheroes))
            }else {
                mView.get()!!.setTasksInView(tasks)
            }
        }
    }

    override fun handleChangesInObservedStatus(status: FireflyConstants.TokenError) {
        //do nothing
    }

    override fun taskClicked(task: Task) {
        log.d(this, "clicked: " + task.title)
        /*mView.safe {
            mView.get()!!.showTaskDetails(superHeroView, superHero)
        }*/
    }

    override fun closeSession() {
        preferences.deleteSecretToken()
    }
}