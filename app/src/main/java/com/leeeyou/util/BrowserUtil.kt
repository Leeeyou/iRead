package com.leeeyou.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.leeeyou.manager.sonic.BrowserActivity
import com.leeeyou.manager.sonic.BrowserActivity.*
import com.leeeyou.manager.sonic.MODE_SONIC
import com.leeeyou.manager.sonic.SonicJavaScriptInterface

fun Activity.startBrowserActivity(context: Context, url: String, title: String) {
    gotoBrowserActivity(context, url, title)
}

fun Fragment.startBrowserActivity(context: Context, url: String, title: String) {
    gotoBrowserActivity(context, url, title)
}

private fun gotoBrowserActivity(context: Context, url: String, title: String) {
    val intent = Intent(context, BrowserActivity::class.java)
    intent.putExtra(PARAM_URL, url)
    intent.putExtra(PARAM_MODE, MODE_SONIC)
    intent.putExtra(PARAM_TITLE, title)
    intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis())
    context.startActivity(intent)
}