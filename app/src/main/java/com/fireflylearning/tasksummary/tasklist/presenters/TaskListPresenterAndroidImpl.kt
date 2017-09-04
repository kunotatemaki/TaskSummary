package com.fireflylearning.tasksummary.tasklist.presenters

import android.support.annotation.VisibleForTesting
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.persistence.PersistenceManager
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

    @Inject
    lateinit var persistence: PersistenceManager

    @VisibleForTesting
    constructor(resources: ResourcesManager, log: LoggerHelper, mView: WeakReference<TaksListView>,
                network: NetworkManager) : this(mView) {
        this.resources = resources
        this.log = log
        this.network = network

    }


    override fun loadTasksFromNetwork() {
        mView.safe {
            val myView = mView.get()!!

            if(myView.getLiveTaks().getLivedataValue()?.isEmpty() == false){
                //no hace falta forzar a la vista a cargar los datos de la caché, porque
                //el livedata notificará a su observador (this) de su último valor cuando se suscriba
                //así que se llamará a handle... y se actualizará ahí
                return@safe
            }else{
                mView.get()!!.showLoader()
                network.getListOfTasks(mView.get()!!.getHostFromChache(),
                        mView.get()!!.getTokenFromChache(),
                        myView.getLiveTaks())
            }
        }
    }

    override fun loadTasksFromDb() {
        mView.safe {
            val myView = mView.get()!!
            myView.showLoader()
            persistence.loadTasks(myView.getLiveTaks())

        }
    }

    override fun handleChangesInObservedTasks(tasks: MutableList<Task>, origin: FireflyConstants.TaskOrigin) {
        mView.safe {
            val myView = mView.get()!!
            myView.hideLoader()
            if(tasks.size == 0){
                //no tasks. If there are task in database, show them. Else, show empty message
                when(origin){

                    FireflyConstants.TaskOrigin.FROM_NETWORK -> loadTasksFromDb();
                    FireflyConstants.TaskOrigin.FROM_DB -> myView.showEmptyList(resources.getString(R.string.no_tasks))
                }


            }else {
                mView.get()!!.setTasksInView(tasks)
                if(origin == FireflyConstants.TaskOrigin.FROM_NETWORK ){
                    //data from network -> store in db
                    persistence.insertListOfTasks(tasks)
                }
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
        preferences.deleteHost()
        mView.get()!!.goToLogin()
    }
}