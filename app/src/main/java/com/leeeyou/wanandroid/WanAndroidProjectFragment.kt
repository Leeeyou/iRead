package com.leeeyou.wanandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leeeyou.util.inflate
import com.leeeyou.BaseFragment
import com.leeeyou.R

/**
 * ClassName:   WeatherFragment
 * Description: 项目展示
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidProjectFragment : BaseFragment() {
    override fun updateData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkCanDoRefresh(): Boolean {
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_project)
    }

}