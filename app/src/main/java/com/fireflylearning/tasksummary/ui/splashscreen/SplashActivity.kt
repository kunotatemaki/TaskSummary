package com.fireflylearning.tasksummary.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.ui.tasklist.TaskListActivity
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import javax.inject.Inject

/**
 * Created by Roll on 1/9/17.
 * Splash screen for the app
 */
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var preferences : PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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