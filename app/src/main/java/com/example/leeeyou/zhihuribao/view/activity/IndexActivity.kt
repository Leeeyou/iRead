package com.example.leeeyou.zhihuribao.view.activity

import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.adapter.ViewPagerAdapter
import com.example.leeeyou.zhihuribao.view.fragment.BaseFragment
import com.example.leeeyou.zhihuribao.view.fragment.OneFragment
import com.example.leeeyou.zhihuribao.view.fragment.StoryFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import java.util.*

class IndexActivity : BaseOriginalActivity() {
    lateinit var mPtrFrame: PtrClassicFrameLayout

    override fun setupActivityComponent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(StoryFragment())
        fragmentList.add(OneFragment())

        val titleList = ArrayList<String>()
        titleList.add("知乎日报")
        titleList.add("一个")

        val mAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = mAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mAdapter.switchTo(position)
            }
        })

        val viewPagerTab = findViewById(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)

        mPtrFrame = findViewById(R.id.store_house_ptr_frame) as PtrClassicFrameLayout
        mPtrFrame.disableWhenHorizontalMove(true)
        mPtrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mAdapter.updateData()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return mAdapter.checkCanDoRefresh()
            }
        })

        mAdapter.switchTo(0)
    }

    fun refreshComplete() {
        mPtrFrame.refreshComplete()
    }

}
