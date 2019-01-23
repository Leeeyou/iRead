package com.leeeyou.manager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.jaeger.library.StatusBarUtil
import com.leeeyou.R
import com.leeeyou.util.ToolbarHelper

/**
 * ClassName:   BaseActivity
 * Description: Toolbar native style
 *
 *
 * Author:      leeeyou
 * Date:        2017/8/17 09:19
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base_original)
    }

    override fun setContentView(layoutResID: Int) {
        ToolbarHelper.setContentView(this, layoutResID)
        setStatusBar()
    }

    override fun setContentView(view: View) {
        ToolbarHelper.setContentView(this, view)
        setStatusBar()
    }

    fun setLeftTitleAndDoNotDisplayHomeAsUp(title: String) {
        ToolbarHelper.setLeftTitle(supportActionBar!!, title)
        ToolbarHelper.hideHomeAsUp(supportActionBar!!)
    }

    fun setLeftTitleAndDisplayHomeAsUp(title: String) {
        ToolbarHelper.setLeftTitle(supportActionBar!!, title)
        ToolbarHelper.showHomeAsUp(supportActionBar!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setStatusBar() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA)
    }
}
