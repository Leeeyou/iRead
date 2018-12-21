package com.leeeyou.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Description: Expand the inflate method
 * 
 * Author:      leeeyou                             
 * Date:        2017/4/24 15:18
 */
fun ViewGroup.inflate(layoutId: Int): View =
        LayoutInflater.from(context).inflate(layoutId, this, false)