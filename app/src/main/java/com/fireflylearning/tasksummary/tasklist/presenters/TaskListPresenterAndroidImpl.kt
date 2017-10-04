package com.fireflylearning.tasksummary.tasklist.presenters

import android.support.annotation.VisibleForTesting
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.persistence.PersistenceManager
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.views.TaskListView
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.ui.MyLivedataObserver
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
open class TaskListPresenterAndroidImpl @Inject constructor(private var taskListView: TaskListView?)
    : TaskListPresenter, MyLivedataObserver {

    private val mView: WeakReference<TaskListView> = WeakReference(taskListView!!)

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

    init {
        taskListView = null
    }

    @VisibleForTesting
    constructor(resources: ResourcesManager, log: LoggerHelper, mView: TaskListView,
                network: NetworkManager, persistence: PersistenceManager, preferences: PreferencesManager) : this(mView) {
        this.resources = resources
        this.log = log
        this.network = network
        this.persistence = persistence
        this.preferences = preferences

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
        mView.safe {
            if(task.descriptionPageUrl == null){
                mView.get()!!.showMessage(resources.getString(R.string.no_description))
            }else{
                var url = resources.getString(R.string.details_url)
                url = url.replace("<host>", mView.get()!!.getHostFromChache())
                url = url.replace("<deviceId>", FireflyConstants.DEVICE_ID)
                url = url.replace("<secret>", mView.get()!!.getTokenFromChache())
                url = url.replace("<url from api>", task.descriptionPageUrl)
                log.d(this, url)
                mView.get()!!.showTaskDetails(url)
            }
        }
    }

    override fun closeSession() {
        preferences.deleteSecretToken()
        preferences.deleteHost()
        mView.get()!!.goToLogin()
    }
}