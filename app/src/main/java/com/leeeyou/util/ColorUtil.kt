package com.leeeyou.util

import android.graphics.Color
import android.support.v4.app.FragmentActivity

fun getThemeColor(activity: FragmentActivity?, attr: Int): Int {
    if (activity == null) return Color.WHITE
    val arrayOf = arrayOf(attr)
    val ta = activity.obtainStyledAttributes(arrayOf.toIntArray())
    val color = ta.getColor(0, 0XFFFFFF)
    ta.recycle()
    return color
}