package com.leeeyou.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.leeeyou.BaseFragment

/**
 * ClassName:   ViewPagerAdapter
 * Description: main activity adapter
 *
 * Author:      leeeyou
 * Date:        2018/2/24 15:05
 */
class ViewPagerAdapter(fm: FragmentManager,
                       private val mFragmentList: Array<BaseFragment>,
                       private val titleList: Array<String>)
    : FragmentStatePagerAdapter(fm) {

    private var mCurrentFragment: BaseFragment? = null

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    fun switchTo(position: Int) {
        mCurrentFragment = mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence = titleList[position]

}