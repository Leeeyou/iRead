package com.leeeyou.setting;

import android.os.Bundle;

import com.leeeyou.R;
import com.leeeyou.manager.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setLeftTitleAndDisplayHomeAsUp("设置");
    }
}
