package com.example.leeeyou.zhihuribao.view.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.view.fragment.MyFragment
import com.example.leeeyou.zhihuribao.view.fragment.StoryFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class IndexActivity : Base_Original_Activity() {
    override fun setupActivityComponent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val myAdapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                .add("知乎推荐", StoryFragment::class.java)
                .add("知乎专栏", MyFragment::class.java)
                .create())

        val viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = myAdapter

        val viewPagerTab = findViewById(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)
    }
}
