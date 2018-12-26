package com.leeeyou.manager

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.leeeyou.R
import com.leeeyou.manager.sonic.*
import com.leeeyou.util.T
import com.tencent.sonic.sdk.*
import timber.log.Timber
import java.io.BufferedInputStream
import java.io.IOException
import java.lang.ref.WeakReference

const val PARAM_MODE = "param_mode"
const val PARAM_URL = "param_url"

class BrowserActivity : AppCompatActivity() {
    private var sonicSession: SonicSession? = null

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(PARAM_URL)
        val mode = intent.getIntExtra(PARAM_MODE, -1)

        Timber.e(url)
        Timber.e("mode is %s", mode)

        if (TextUtils.isEmpty(url) || -1 == mode) {
            finish()
            return
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostSonicRuntime(application), SonicConfig.Builder().build())
        }

        var sonicSessionClient: SonicSessionClientImpl? = null

        // if it's sonic mode , startup sonic session at first time
        if (MODE_SONIC == mode) {
            val builder = SonicSessionConfig.Builder()
            builder.setSupportLocalServer(true)

            // if it's offline pkg mode, we need to intercept the session connection
            if (MODE_SONIC_WITH_OFFLINE_CACHE == mode) {
                builder.setCacheInterceptor(object : SonicCacheInterceptor(null) {
                    override fun getCacheData(session: SonicSession?): String? {
                        return null// offline pkg does not need cache
                    }

                })

                builder.setConnectionInterceptor(object : SonicSessionConnectionInterceptor() {
                    override fun getConnection(session: SonicSession, intent: Intent): SonicSessionConnection {
                        return OfflinePkgSessionConnection(this@BrowserActivity, session, intent)
                    }

                })
            }

            // create sonic session and run sonic flow
            sonicSession = SonicEngine.getInstance().createSession(url, builder.build())
            if (null != sonicSession) {
                sonicSessionClient = SonicSessionClientImpl()
                sonicSession!!.bindClient(sonicSessionClient)
            } else {
                // this only happen when a same sonic session is already running,
                // u can comment following codes to feedback as a default mode.
                // throw new UnknownError("create session fail!");
                T.showShort(this, "create sonic session fail!")
            }

            // start init flow ...
            // in the real world, the init flow may cost a long time as startup
            // runtime、init configs....
            setContentView(R.layout.activity_browser)

            val btnFab = findViewById<FloatingActionButton>(R.id.btn_refresh)
            btnFab.setOnClickListener {
                sonicSession?.refresh()
            }

            // init webView
            val webView = findViewById<View>(R.id.webview) as WebView
            webView.webViewClient = (object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    sonicSession?.sessionClient?.pageFinish(url)
                }

                @TargetApi(21)
                override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                    return shouldInterceptRequest(view, request)
                }

                override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                    return if (sonicSession != null) {
                        sonicSession?.sessionClient?.requestResource(url) as WebResourceResponse
                    } else null
                }
            })

            val webSettings = webView.settings

            // add java script interface
            // note:if api level lower than 17(android 4.2), addJavascriptInterface has security
            // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
            // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
            webSettings.javaScriptEnabled = true
            webView.removeJavascriptInterface("searchBoxJavaBridge_")
            intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis())
            webView.addJavascriptInterface(SonicJavaScriptInterface(sonicSessionClient, intent), "sonic")

            // init webView settings
            webSettings.allowContentAccess = true
            webSettings.databaseEnabled = true
            webSettings.domStorageEnabled = true
            webSettings.setAppCacheEnabled(true)
            webSettings.savePassword = false
            webSettings.saveFormData = false
            webSettings.useWideViewPort = true
            webSettings.loadWithOverviewMode = true

            // webView is ready now, just tell session client to bind
            if (sonicSessionClient != null) {
                sonicSessionClient.bindWebView(webView)
                sonicSessionClient.clientReady()
            } else { // default mode
                webView.loadUrl(url)
            }
        }
    }

    override fun onDestroy() {
        sonicSession?.destroy()
        sonicSession = null
        super.onDestroy()
    }

    private class OfflinePkgSessionConnection(context: Context, session: SonicSession, intent: Intent)
        : SonicSessionConnection(session, intent) {

        private val context: WeakReference<Context> = WeakReference(context)

        override fun internalConnect(): Int {
            val ctx = context.get()
            ctx?.let {
                try {
                    val offlineHtmlInputStream = ctx.assets.open("sonic-demo-index.html")
                    responseStream = BufferedInputStream(offlineHtmlInputStream)
                    return SonicConstants.ERROR_CODE_SUCCESS
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            return SonicConstants.ERROR_CODE_UNKNOWN
        }

        override fun internalGetResponseStream(): BufferedInputStream {
            return responseStream
        }

        override fun internalGetCustomHeadFieldEtag(): String {
            return SonicSessionConnection.CUSTOM_HEAD_FILED_ETAG
        }

        override fun disconnect() {
            if (null != responseStream) {
                try {
                    responseStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        override fun getResponseCode(): Int {
            return 200
        }

        override fun getResponseHeaderFields(): Map<String, List<String>> {
            return HashMap(0)
        }

        override fun getResponseHeaderField(key: String): String {
            return ""
        }
    }
}