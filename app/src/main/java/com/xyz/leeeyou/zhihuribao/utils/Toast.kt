package com.xyz.leeeyou.zhihuribao.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by leeeyou on 2017/4/24.
 */
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


