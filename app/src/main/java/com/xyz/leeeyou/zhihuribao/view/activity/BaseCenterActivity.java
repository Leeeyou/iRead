package com.xyz.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.utils.ToolbarHelper;

/**
 * 基类,Toolbar样式的BaseActivity,标题能居中显示
 */
public abstract class BaseCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_center);

        setupActivityComponent();

        ToolbarHelper.checkSdkVersionToTranslucentFlag(getWindow());
    }

    abstract void setupActivityComponent();

    @Override
    public void setContentView(int layoutResID) {
        ToolbarHelper.setContentView(this, layoutResID);
    }

    @Override
    public void setContentView(View view) {
        ToolbarHelper.setContentView(this, view);
    }

    private void setCenterTitle(String title) {
        TextView toolbar_center_title = swithCenterTextVisible(View.VISIBLE);
        toolbar_center_title.setText(title);
    }

    private TextView swithCenterTextVisible(int visible) {
        TextView toolbar_center_title = (TextView) findViewById(R.id.toolbar_center_title);
        if (toolbar_center_title != null) {
            toolbar_center_title.setVisibility(visible);
        }
        return toolbar_center_title;
    }

    public void setLeftTitleAndDoNotDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());
    }

    public void setLeftTitleAndDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.showHomeAsUp(getSupportActionBar());
    }

    public void setLeftTitleAndHideCenterText(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());

        swithCenterTextVisible(View.GONE);
    }

    public void setCenterTitleOnly(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), null);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());

        setCenterTitle(title);
    }

}
