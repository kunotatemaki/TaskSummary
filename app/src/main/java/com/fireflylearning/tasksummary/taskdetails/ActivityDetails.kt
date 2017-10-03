package com.fireflylearning.tasksummary.taskdetails

import android.database.DatabaseUtils
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import android.webkit.WebViewClient
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.databinding.ActivityWebviewBinding
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import javax.inject.Inject


/**
 * Created by Roll on 4/9/17.
 */
class ActivityDetails : BaseActivity() {

    private lateinit var webView: WebView

    @Inject
    lateinit var log: LoggerHelper

    private lateinit var mBinding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dependency injection
        //(application as FireflyApp).mComponent.inject(this)

        //databinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val url = intent.getStringExtra(FireflyConstants.URL)

        webView = findViewById(R.id.webView1)
        //webView.settings.javaScriptEnabled = true

        val webViewClient: WebViewClient = object: WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                log.d(this, "empieza")
                mBinding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                log.d(this, "finaliza")
                mBinding.progressBar.visibility = View.INVISIBLE
            }
        }

        webView.webViewClient = webViewClient
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
