package com.example.vahakassignment

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebActivity : AppCompatActivity() {
    var head :String=""
    private var mProgressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
         head = intent.getStringExtra("head").toString()
        addWebView("https://en.wikipedia.org/wiki/"+head)
    }

      fun addWebView(url:String) {

        showProgressDialog(this)
        val webView = findViewById<View>(R.id.webview) as WebView
        webView.setBackgroundColor(resources.getColor(R.color.white))
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true



        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {


            }

            override fun onPageFinished(view: WebView, url: String) {

                dismissProgressDialog()

            }
        }
    }

    fun showProgressDialog(context: Context?) {
        try {
            if (isDialogShowing()) {
                dismissProgressDialog()
            }

            if (context is Activity) {
                val activity = context as Activity?
                if (activity!!.isFinishing) {
                    return
                }
            }
            mProgressDialog = Dialog(context!!)
            mProgressDialog?.setContentView(R.layout.dialog_loading)
            mProgressDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (!isFinishing) {
                mProgressDialog?.show()
            }
            mProgressDialog?.setCancelable(false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }


    fun isDialogShowing(): Boolean {
        try {
            return if (mProgressDialog == null) {
                false
            } else {
                mProgressDialog!!.isShowing()
            }
        } catch (e: Exception) {
            return false
        }
    }
}