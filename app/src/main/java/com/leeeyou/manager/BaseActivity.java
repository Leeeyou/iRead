package com.leeeyou.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.leeeyou.R;
import com.leeeyou.util.ToolbarHelper;

/**
 * ClassName:   BaseActivity
 * Description: Toolbar native style
 * <p>
 * Author:      leeeyou
 * Date:        2017/8/17 09:19
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_original);
    }

    @Override
    public void setContentView(int layoutResID) {
        ToolbarHelper.setContentView(this, layoutResID);
        setStatusBar();
    }

    @Override
    public void setContentView(View view) {
        ToolbarHelper.setContentView(this, view);
        setStatusBar();
    }

    public void setLeftTitleAndDoNotDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());
    }

    public void setLeftTitleAndDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.showHomeAsUp(getSupportActionBar());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }
}
