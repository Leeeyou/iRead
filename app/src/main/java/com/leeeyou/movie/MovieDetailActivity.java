package com.leeeyou.movie;

import android.os.Bundle;

import com.leeeyou.R;
import com.leeeyou.manager.BaseActivity;

public class MovieDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setLeftTitleAndDisplayHomeAsUp("电影详情");
    }
}
