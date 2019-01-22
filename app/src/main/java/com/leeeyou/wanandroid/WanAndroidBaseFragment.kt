package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.ScrollView
import com.leeeyou.R
import com.leeeyou.login.event.LoginSuccessEvent
import com.leeeyou.login.event.LogoutSuccessEvent
import com.leeeyou.manager.BaseFragment
import com.leeeyou.manager.MyAnimationListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class WanAndroidBaseFragment : BaseFragment() {
    lateinit var mLayoutManager: LinearLayoutManager
    var mPageIndex: Int = 0
    var mPageCount: Int = 0

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun initHeadView(ptrFrame: PtrFrameLayout, headStr: String) {
        val header = StoreHouseHeader(context)
        header.setTextColor(resources.getColor(R.color.colorTxtSelected))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString(headStr, 15)
        ptrFrame.headerView = header
        ptrFrame.addPtrUIHandler(header)
    }

    fun initLayoutManager(context: Context) {
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun initPtrFrame(ptrFrame: PtrFrameLayout, headStr: String) {
        initHeadView(ptrFrame, headStr)
        ptrFrame.disableWhenHorizontalMove(true)
        ptrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return checkRefresh()
            }
        })
    }

    open fun checkRefresh(): Boolean {
        return recyclerViewFirstItemCanVisible()
    }

    open fun pullDownToRefresh() {
        mPageIndex = 0
    }

    fun recyclerViewFirstItemCanVisible(): Boolean {
        return mLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0
    }

    fun showDetailTagAnimation(arrowImage: ImageView?, tagScrollView: ScrollView?) {
        arrowImage?.also {
            val rotateAnimation = RotateAnimation(0f, 90f, (arrowImage.width / 2).toFloat(), (arrowImage.height / 2).toFloat())
            rotateAnimation.duration = 100
            rotateAnimation.fillAfter = true
            rotateAnimation.interpolator = AccelerateInterpolator()
            arrowImage.startAnimation(rotateAnimation)

            rotateAnimation.setAnimationListener(object : MyAnimationListener() {
                override fun onAnimationEnd(animation: Animation?) {
                    tagScrollView?.visibility = View.VISIBLE
                }
            })
        }
    }

    fun hiddenDetailTagAnimation(arrowImage: ImageView?, tagScrollView: ScrollView?) {
        arrowImage?.also {
            val rotateAnimation = RotateAnimation(90f, 0f, (arrowImage.width / 2).toFloat(), (arrowImage.height / 2).toFloat())
            rotateAnimation.duration = 100
            rotateAnimation.fillAfter = true
            rotateAnimation.interpolator = AccelerateInterpolator()
            arrowImage.startAnimation(rotateAnimation)

            rotateAnimation.setAnimationListener(object : MyAnimationListener() {
                override fun onAnimationEnd(animation: Animation?) {
                    tagScrollView?.visibility = View.GONE
                }
            })
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LogoutSuccessEvent) {
        pullDownToRefresh()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LoginSuccessEvent) {
        pullDownToRefresh()
    }
}