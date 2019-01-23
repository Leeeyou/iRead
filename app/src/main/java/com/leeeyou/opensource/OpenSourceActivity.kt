package com.leeeyou.opensource

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseActivity
import com.leeeyou.opensource.bean.OpenSourceData
import com.leeeyou.util.startBrowserActivity
import java.util.*

class OpenSourceActivity : BaseActivity() {

    private val mOpenSourceList = ArrayList<OpenSourceData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_source)
        setLeftTitleAndDisplayHomeAsUp("开源协议")
        initOpenSourceData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewOpenSource = findViewById<RecyclerView>(R.id.recyclerViewOpenSource)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewOpenSource.layoutManager = linearLayoutManager
        val adapter = object : BaseQuickAdapter<OpenSourceData, BaseViewHolder>(R.layout.item_open_source, mOpenSourceList) {
            override fun convert(helper: BaseViewHolder, item: OpenSourceData) {
                helper.setText(R.id.tv_title, item.name)
                        .setText(R.id.tv_desc, item.desc)
                        .addOnClickListener(R.id.rl_open_source)
            }
        }
        recyclerViewOpenSource.adapter = adapter
        adapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            if (view.id == R.id.rl_open_source) {
                val item = adapter.getItem(position)
                if (item != null && !TextUtils.isEmpty(item.url)) {
                    item.url?.let { item.name?.let { name -> startBrowserActivity(this@OpenSourceActivity, it, name) } }
                }
            }
        }
    }

    private fun initOpenSourceData() {
        mOpenSourceList.add(OpenSourceData("retrofit2", "https://github.com/square/retrofit", "Type-safe HTTP client for Android and Java by Square, Inc."))
        mOpenSourceList.add(OpenSourceData("RxAndroid", "https://github.com/ReactiveX/RxAndroid", "RxJava bindings for Android"))
        mOpenSourceList.add(OpenSourceData("RxKotlin", "https://github.com/ReactiveX/RxKotlin", "RxJava bindings for Kotlin"))
        mOpenSourceList.add(OpenSourceData("glide", "https://github.com/bumptech/glide", "An image loading and caching library for Android focused on smooth scrolling"))
        mOpenSourceList.add(OpenSourceData("okhttp3", "https://github.com/square/okhttp", "An HTTP+HTTP/2 client for Android and Java applications. "))
        mOpenSourceList.add(OpenSourceData("android-Ultra-Pull-To-Refresh", "https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh", "Ultra Pull to Refresh for Android. Support all the views."))
        mOpenSourceList.add(OpenSourceData("BaseRecyclerViewAdapterHelper", "https://github.com/CymChad/BaseRecyclerViewAdapterHelper", "BRVAH:Powerful and flexible RecyclerAdapter"))
        mOpenSourceList.add(OpenSourceData("banner", "https://github.com/youth5201314/banner", "Android广告图片轮播控件，支持无限循环和多种主题，可以灵活设置轮播样式、动画、轮播和切换时间、位置、图片加载框架等！"))
        mOpenSourceList.add(OpenSourceData("timber", "https://github.com/JakeWharton/timber", "A logger with a small, extensible API which provides utility on top of Android's normal Log class. "))
        mOpenSourceList.add(OpenSourceData("joda-time-android", "https://github.com/dlew/joda-time-android", "Joda-Time library with Android specialization"))
        mOpenSourceList.add(OpenSourceData("VasSonic", "https://github.com/Tencent/VasSonic", "VasSonic is a lightweight and high-performance Hybrid framework developed by tencent VAS team, which is intended to speed up the first screen of websites working on Android and iOS platform."))
        mOpenSourceList.add(OpenSourceData("FlowLayout", "https://github.com/hongyangAndroid/FlowLayout", "Android流式布局，支持单选、多选等，适合用于产品标签等。"))
        mOpenSourceList.add(OpenSourceData("SmartTabLayout", "https://github.com/ogaclejapan/SmartTabLayout", "A custom ViewPager title strip which gives continuous feedback to the user when scrolling"))
        mOpenSourceList.add(OpenSourceData("StatusBarUtil", "https://github.com/laobie/StatusBarUtil", "A util for setting status bar style on Android App. "))
        mOpenSourceList.add(OpenSourceData("PersistentCookieJar", "https://github.com/franmontiel/PersistentCookieJar", "A persistent CookieJar implementation for OkHttp 3 based on SharedPreferences."))
        mOpenSourceList.add(OpenSourceData("EventBus", "https://github.com/greenrobot/EventBus", "Event bus for Android and Java that simplifies communication between Activities, Fragments, Threads, Services, etc. Less code, better quality. "))
        mOpenSourceList.add(OpenSourceData("lottie-android", "https://github.com/airbnb/lottie-android", "Render After Effects animations natively on Android and iOS, Web, and React Native"))
    }
}
