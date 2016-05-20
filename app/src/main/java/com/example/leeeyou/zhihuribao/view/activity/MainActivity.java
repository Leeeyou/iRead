package com.example.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.view.MyFragment;
import com.example.leeeyou.zhihuribao.view.StoryFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends Base_Original_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("每日推荐", StoryFragment.class)
                .add("知乎专栏", MyFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    void setupActivityComponent() {

    }
}
