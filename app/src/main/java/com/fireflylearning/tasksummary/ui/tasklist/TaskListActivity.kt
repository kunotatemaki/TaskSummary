package com.fireflylearning.tasksummary.ui.tasklist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.Menu
import android.view.MenuItem
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.ui.login.LoginActivity
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.common.ActivityViewModel
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import javax.inject.Inject


@CustomScopes.ActivityScope
class TaskListActivity : BaseActivity(){

    @Inject
    lateinit var navigationController: TaskListNavigationController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var log: LoggerHelper



    @Inject
    lateinit var preferences: PreferencesManager


    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setContentView(R.layout.activity_task_list)

        activityViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActivityViewModel::class.java)

        if(savedInstanceState == null){
            navigationController.navigateToListOfTasks(intent.extras[FireflyConstants.HOST] as String,
                    intent.extras[FireflyConstants.SECRET_TOKEN] as String)
        }

        activityViewModel.getSessionStatus().observe(this, Observer<Boolean>{
            status->
            run {
                if (status == false) {
                    goToLogin()
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.task_list_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.close_sesion -> {
                log.d(this, "cerrar sesion")
                activityViewModel.closeSession()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    @VisibleForTesting
    fun setTaskList(tasks: MutableList<Task>){
        //getLiveTaks().setLivedataValue(tasks)
    }


    //endregion
}
