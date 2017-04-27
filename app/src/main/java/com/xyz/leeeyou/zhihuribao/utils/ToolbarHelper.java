package com.xyz.leeeyou.zhihuribao.utils;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xyz.leeeyou.zhihuribao.R;

/**
 * Created by leeeyou on 16/5/10.
 * <p/>
 * Toolbar辅助类,抽取Original和Center中的公共方法
 */
public class ToolbarHelper {

    public static void checkSdkVersionToTranslucentFlag(Window window) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            final WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | attributes.flags);
        }
    }

    public static void initToolbar(AppCompatActivity activity) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

        if (toolbar != null) activity.setSupportActionBar(toolbar);
    }

    public static void setContentView(AppCompatActivity activity, int layoutResID) {
        activity.setContentView(View.inflate(activity, layoutResID, null));
    }

    public static void setContentView(AppCompatActivity activity, View view) {
        LinearLayout rootLayout = (LinearLayout) activity.findViewById(R.id.rootLayout);

        if (rootLayout == null) return;

        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        initToolbar(activity);
    }

    public static void hideHomeAsUp(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    public static void showHomeAsUp(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static void setLeftTitle(ActionBar actionBar, String title) {
        actionBar.setTitle(title);
    }

}
