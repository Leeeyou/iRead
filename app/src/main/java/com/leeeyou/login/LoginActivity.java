package com.leeeyou.login;

import android.os.Bundle;

import com.leeeyou.R;
import com.leeeyou.manager.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setLeftTitleAndDisplayHomeAsUp("登录");
    }
}