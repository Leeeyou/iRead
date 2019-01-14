package com.leeeyou.opensource;

import android.os.Bundle;

import com.leeeyou.R;
import com.leeeyou.manager.BaseActivity;

public class OpenSourceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source);
        setLeftTitleAndDisplayHomeAsUp("开源协议");
    }
}
