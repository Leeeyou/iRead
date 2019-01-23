package com.leeeyou.util

import android.content.Context
import android.widget.Toast

/**
 * ClassName:   T
 * Description: Toast unified management class
 *
 * Author:      leeeyou
 * Date:        2018/2/24 15:19
 */
class T private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        var isShow = true

        fun showShort(context: Context?, message: CharSequence?) {
            if (isShow) {
                if (context != null && message != null) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun showShort(context: Context?, message: Int) {
            if (isShow) {
                if (context != null) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun showLong(context: Context?, message: CharSequence?) {
            if (isShow) {
                if (context != null && message != null) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        fun showLong(context: Context?, message: Int) {
            if (isShow) {
                if (context != null) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        fun show(context: Context?, message: CharSequence?, duration: Int) {
            if (isShow) {
                if (context != null && message != null)
                    Toast.makeText(context, message, duration).show()
            }
        }

        fun show(context: Context?, message: Int, duration: Int) {
            if (isShow) {
                if (context != null) {
                    Toast.makeText(context, message, duration).show()
                }
            }
        }
    }

}