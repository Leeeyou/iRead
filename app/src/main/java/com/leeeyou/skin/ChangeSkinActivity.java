package com.leeeyou.skin;

import android.os.Bundle;

import com.leeeyou.R;
import com.leeeyou.manager.BaseActivity;

public class ChangeSkinActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
        setLeftTitleAndDisplayHomeAsUp("个性换肤");
    }

}
