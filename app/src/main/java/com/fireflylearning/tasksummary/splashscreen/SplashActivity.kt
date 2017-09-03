package com.fireflylearning.tasksummary.splashscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.tasklist.views.TaskListActivity
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import javax.inject.Inject

/**
 * Created by Roll on 1/9/17.
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences : PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as FireflyApp).mComponent.inject(this)
        val host = preferences.getHost()
        val token = preferences.getSecretToken()
        if(TextUtils.isEmpty(host) && TextUtils.isEmpty(token)){
            launchLoginScreen()
        }else {
            launchMainScreen()
        }
    }

    private fun launchLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchMainScreen() {
        val intent = Intent(this, TaskListActivity::class.java)
        intent.putExtra(FireflyConstants.HOST, preferences.getHost())
        intent.putExtra(FireflyConstants.SECRET_TOKEN, preferences.getSecretToken())
        startActivity(intent)
        finish()
    }
}