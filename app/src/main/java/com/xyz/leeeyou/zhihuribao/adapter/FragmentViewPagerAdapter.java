package com.xyz.leeeyou.zhihuribao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> mViewPagerFragments;
    private Fragment mCurrentFragment;

    public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        mViewPagerFragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewPagerFragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    protected void updateData() {
        final Fragment fragment = mCurrentFragment;
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

    @Override
    public int getCount() {
        return mViewPagerFragments.size();
    }

    public void switchTO(final int position) {
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

    public boolean checkCanDoRefresh() {
//        if (mCurrentFragment == null) {
//            return true;
//        }
//        return mCurrentFragment.checkCanDoRefresh();
        return true;
    }
}