package com.leeeyou.util;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leeeyou.R;

/**
 * ClassName:   ToolbarHelper
 * Description: Toolbar helper class to extract public methods from Original and Center
 * <p>
 * Author:      leeeyou
 * Date:        2016/5/10 15:18
 */
public class ToolbarHelper {

    private static TextView toolbarTitle;

    public static void checkSdkVersionToTranslucentFlag(Window window) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            final WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | attributes.flags);
        }
    }

    public static void initToolbar(AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        }
    }

    public static void setContentView(AppCompatActivity activity, int layoutResID) {
        activity.setContentView(View.inflate(activity, layoutResID, null));
    }

    public static void setContentView(AppCompatActivity activity, View view) {
        LinearLayout rootLayout = activity.findViewById(R.id.rootLayout);
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
        actionBar.setTitle("");
        if (toolbarTitle != null) {
            toolbarTitle.setText(HtmlUtils.INSTANCE.translation(title));
            toolbarTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            toolbarTitle.setSingleLine(true);
            toolbarTitle.setSelected(true);
            toolbarTitle.setFocusable(true);
            toolbarTitle.setFocusableInTouchMode(true);
        }
    }

}
