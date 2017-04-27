package com.xyz.leeeyou.zhihuribao

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by leeeyou on 2017/4/21.
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}