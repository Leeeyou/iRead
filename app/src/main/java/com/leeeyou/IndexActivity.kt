package com.leeeyou

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.leeeyou.manager.BaseActivity
import com.leeeyou.util.ViewPagerAdapter
import com.leeeyou.wanandroid.WanAndroidFragment
import com.leeeyou.weather.WeatherFragment
import kotlinx.android.synthetic.main.activity_index.*

private const val FIRST_PAGE_INDEX: Int = 0

/**
 * ClassName:   IndexActivity
 *
 * Author:      leeeyou                             
 * Date:        2018/12/21 18:02                     
 */
class IndexActivity : BaseActivity() {

    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        initAdapter()
        initViewPager()
    }

    private fun initViewPager() {
        indexViewPager.adapter = mViewPagerAdapter
        indexViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewPagerAdapter.switchTo(position)
            }
        })

        indexTabLayout.setupWithViewPager(indexViewPager)
    }

    private fun initAdapter() {
        //create a collection object using arrayOf
        val fragmentList = arrayOf(WeatherFragment(), WanAndroidFragment(), com.leeeyou.zhihudaily.view.ZhiHuDailyFragment())
        val titleList = arrayOf("天气", "WanAndroid", "知乎日报")

        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        mViewPagerAdapter.switchTo(FIRST_PAGE_INDEX)
    }

}
