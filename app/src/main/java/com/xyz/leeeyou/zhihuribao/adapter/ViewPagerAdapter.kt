package com.xyz.leeeyou.zhihuribao.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.xyz.leeeyou.zhihuribao.view.fragment.BaseFragment

/**
 * Created by leeeyou on 2017/4/26.
 */
class ViewPagerAdapter(fm: FragmentManager, private val mFragmentList: ArrayList<BaseFragment>, private val titleList: ArrayList<String>) : FragmentStatePagerAdapter(fm) {
    var mCurrentFragment: BaseFragment? = null

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun updateData() {
        if (mCurrentFragment == null) {
            return
        }
        return mCurrentFragment!!.updateData()
    }

    fun checkCanDoRefresh(): Boolean {
        if (mCurrentFragment == null) {
            return true
        }
        return mCurrentFragment!!.checkCanDoRefresh()
    }

    fun switchTo(position: Int) {
//        int len = mViewPagerFragments . size ();
//        for (int i = 0; i < len; i++) {
//            ViewPagerFragment fragment = mViewPagerFragments . get (i);
//            if (i == position) {
//                mCurrentFragment = fragment;
//                fragment.show();
//            } else {
//                fragment.hide();
//            }
//        }

        mCurrentFragment = mFragmentList[position]

//        for (i in mFragmentList.indices) {
//            if (i == position) {
//                mCurrentFragment = mFragmentList[position]
//            }
//        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

}