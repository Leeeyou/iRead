package com.leeeyou

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.tencent.bugly.Bugly
import net.danlew.android.joda.JodaTimeAndroid

/**
 * ClassName:   App
 *
 * Author:      leeeyou
 * Date:        2018/2/24 14:27
 */
class KotlinApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        Bugly.init(this, "8f4e37e626", false)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}