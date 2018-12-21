package com.leeeyou.wanandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.inflate

/**
 * ClassName:   WeatherFragment
 * Description: Play Android - Project
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidProjectFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_project)
    }

}