package com.leeeyou.manager.sonic

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceResponse
import com.leeeyou.util.T
import com.tencent.sonic.sdk.SonicRuntime
import com.tencent.sonic.sdk.SonicSessionClient
import timber.log.Timber
import java.io.InputStream


class HostSonicRuntime(context: Context) : SonicRuntime(context) {
    override fun showToast(text: CharSequence?, duration: Int) {
        T.show(context, text, duration)
    }

    override fun log(tag: String?, level: Int, message: String?) {
        Timber.tag(tag)
        when (level) {
            Log.ERROR -> Timber.e(message)
            Log.INFO -> Timber.i(message)
            else -> Timber.d(message)
        }
    }

    override fun getUserAgent(): String {
        return "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36"
    }

    override fun isNetworkValid(): Boolean {
        return true
    }

    override fun postTaskToThread(task: Runnable?, delayMillis: Long) {
        Thread(task, "SonicThread").start()
    }

    override fun isSonicUrl(url: String?): Boolean {
        return true
    }

    override fun setCookie(url: String?, cookies: MutableList<String>?): Boolean {
        if (TextUtils.isEmpty(url) && cookies != null && cookies.size > 0) {
            val cookieManager = android.webkit.CookieManager.getInstance()
            for (item in cookies) {
                cookieManager.setCookie(url, item)
            }
            return true
        }
        return false
    }

    override fun getCookie(url: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val instance = CookieManager.getInstance()
            return instance.getCookie(url)
        }

        return ""
    }

    override fun createWebResourceResponse(mimeType: String?, encoding: String?, data: InputStream?, headers: MutableMap<String, String>?): Any {
        val webResourceResponse = WebResourceResponse(mimeType, encoding, data)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webResourceResponse.responseHeaders = headers
        }
        return webResourceResponse
    }

    override fun getCurrentUserAccount(): String {
        return "ReadSelectionSonic"
    }

    override fun notifyError(client: SonicSessionClient?, url: String?, errorCode: Int) {
    }


}