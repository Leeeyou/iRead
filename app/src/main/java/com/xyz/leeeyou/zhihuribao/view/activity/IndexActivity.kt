package com.xyz.leeeyou.zhihuribao.view.activity

import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.adapter.ViewPagerAdapter
import com.xyz.leeeyou.zhihuribao.view.fragment.BaseFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.OneFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.StoryFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import java.util.*

class IndexActivity : BaseOriginalActivity() {
    val FIRST_PAGE_INDEX: Int = 0

    lateinit var mPtrFrame: PtrClassicFrameLayout
    lateinit var mViewPagerAdapter: ViewPagerAdapter

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
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = mViewPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewPagerAdapter.switchTo(position)
            }
        })

        val viewPagerTab = findViewById(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)
    }

    private fun initAdapter() {
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(StoryFragment())
        fragmentList.add(OneFragment())

        val titleList = ArrayList<String>()
        titleList.add("知乎日报")
        titleList.add("一个")

        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        mViewPagerAdapter.switchTo(FIRST_PAGE_INDEX)
    }

    private fun initPtr() {
        mPtrFrame = findViewById(R.id.store_house_ptr_frame) as PtrClassicFrameLayout
        mPtrFrame.disableWhenHorizontalMove(true)
        mPtrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mViewPagerAdapter.updateData()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return mViewPagerAdapter.checkCanDoRefresh()
            }
        })
    }

    fun refreshComplete() {
        mPtrFrame.refreshComplete()
    }

}
