package com.xyz.leeeyou.zhihuribao.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by leeeyou on 2017/4/24.
 *
 * 扩展inflate方法
 */
fun ViewGroup.inflate(layoutId: Int): View =
        LayoutInflater.from(context).inflate(layoutId, this, false)