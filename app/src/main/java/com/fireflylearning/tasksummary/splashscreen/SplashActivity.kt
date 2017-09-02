package com.fireflylearning.tasksummary.splashscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fireflylearning.tasksummary.sheroeslist.views.SuperHeroListActivity

/**
 * Created by Roll on 1/9/17.
 */
class SplashActivity : AppCompatActivity() {

    internal val REQUEST_CODE_ANIMATION: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchMainScreen()
    }

    private fun launchMainScreen() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
        finish()
    }
}