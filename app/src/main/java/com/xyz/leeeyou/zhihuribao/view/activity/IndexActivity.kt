package com.xyz.leeeyou.zhihuribao.view.activity

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.adapter.ViewPagerAdapter
import com.xyz.leeeyou.zhihuribao.view.fragment.StoryFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.WanAndroidFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.WeatherFragment
import kotlinx.android.synthetic.main.activity_index.*

private const val FIRST_PAGE_INDEX: Int = 0

class IndexActivity : BaseOriginalActivity() {

    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun setupActivityComponent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        initAdapter()
        initViewPager()
        initPtr()
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
        val fragmentList = arrayOf(WeatherFragment(), WanAndroidFragment(), StoryFragment())
        val titleList = arrayOf("天气", "WanAndroid", "知乎日报")

        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        mViewPagerAdapter.switchTo(FIRST_PAGE_INDEX)
    }

    private fun initPtr() {
        store_house_ptr_frame.disableWhenHorizontalMove(true)
        store_house_ptr_frame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
//                mViewPagerAdapter.updateData()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?):
                    Boolean = mViewPagerAdapter.checkCanDoRefresh()
        })
    }

    fun refreshComplete() {
        store_house_ptr_frame.refreshComplete()
    }

}
