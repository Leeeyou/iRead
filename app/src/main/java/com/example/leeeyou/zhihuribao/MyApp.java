package com.example.leeeyou.zhihuribao;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by leeeyou on 16/9/28.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
