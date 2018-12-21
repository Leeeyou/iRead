package com.leeeyou.wanandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.ViewPagerAdapter
import com.leeeyou.util.inflate
import kotlinx.android.synthetic.main.fragment_wan_android.*

/**
 * ClassName:   WanAndroidFragment
 * Description: Play Android
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidFragment : BaseFragment() {
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //create a collection object using arrayOf
        val fragmentList = arrayOf(WanAndroidRecommendFragment(), WanAndroidSystemFragment(), WanAndroidProjectFragment())
        val titleList = arrayOf("推荐", "体系", "项目")

        mViewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, fragmentList, titleList)
        wanAndroidViewPager.adapter = mViewPagerAdapter

        wanAndroidTabLayout.setupWithViewPager(wanAndroidViewPager)
    }

}