package com.xyz.leeeyou.zhihuribao.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import java.util.*

internal class FragmentViewPagerAdapter2(fm: FragmentManager, private val mViewPagerFragments: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {
    private val mCurrentFragment: Fragment? = null

    override fun getItem(position: Int): Fragment {
        return mViewPagerFragments[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {}

    protected fun updateData() {
        val fragment = mCurrentFragment
        //        DemoRequestData.getImageList(new RequestFinishHandler<JsonData>() {
        //            @Override
        //            public void onRequestFinish(final JsonData data) {
        //                if (fragment == mCurrentFragment) {
        //                    fragment.update(data);
        //                    mPtrFrame.refreshComplete();
        //                }
        //            }
        //        });
    }

    override fun getCount(): Int {
        return mViewPagerFragments.size
    }

    fun switchTO(position: Int) {
        //        int len = mViewPagerFragments.size();
        //        for (int i = 0; i < len; i++) {
        //            ViewPagerFragment fragment = mViewPagerFragments.get(i);
        //            if (i == position) {
        //                mCurrentFragment = fragment;
        //                fragment.show();
        //            } else {
        //                fragment.hide();
        //            }
        //        }
    }

    fun checkCanDoRefresh(): Boolean {
        //        if (mCurrentFragment == null) {
        //            return true;
        //        }
        //        return mCurrentFragment.checkCanDoRefresh();
        return true
    }
}