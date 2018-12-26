package com.leeeyou.zhihudaily.view

import android.content.Intent
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.leeeyou.R
import com.leeeyou.manager.BaseActivity
import com.leeeyou.manager.BrowserActivity
import com.leeeyou.manager.PARAM_MODE
import com.leeeyou.manager.PARAM_URL
import com.leeeyou.manager.sonic.SonicJavaScriptInterface
import com.leeeyou.util.HtmlUtils
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyDetail
import com.leeeyou.zhihudaily.model.fetchZhiHuDailyDetailById
import kotlinx.android.synthetic.main.activity_story_detail.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ZhiHuDailyDetailActivity : BaseActivity() {
    internal lateinit var mMaterialDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)
        setLeftTitleAndDisplayHomeAsUp(intent.getStringExtra("storyTitle"))
        initWebView()
        fetchZhiHuDetail()
    }

    private fun fetchZhiHuDetail() {
        fetchZhiHuDailyDetailById(intent.getIntExtra("storyId", -1))
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe {
                    mMaterialDialog = MaterialDialog.Builder(this@ZhiHuDailyDetailActivity)
                            .content("Loading...")
                            .progress(true, 0)
                            .show()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ZhiHuDailyDetail>() {
                    override fun onCompleted() {
                        mMaterialDialog.dismiss()
                    }

                    override fun onError(e: Throwable) {
                        mMaterialDialog.dismiss()
                        e.printStackTrace()
                    }

                    override fun onNext(storyDetail: ZhiHuDailyDetail) {
                        zhiHuDailyWebview.loadData(HtmlUtils.structHtml(storyDetail.body, storyDetail.css), "text/html; charset=UTF-8", null)
//                        startBrowserActivity(MODE_SONIC, storyDetail.share_url)
                    }
                })
    }

    private fun startBrowserActivity(mode: Int, url: String) {
        val intent = Intent(this, BrowserActivity::class.java)
        intent.putExtra(PARAM_URL, url)
        intent.putExtra(PARAM_MODE, mode)
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis())
        startActivity(intent)
    }

    private fun initWebView() {
        zhiHuDailyWebview.isVerticalScrollBarEnabled = false
        zhiHuDailyWebview.settings.defaultTextEncodingName = "UTF-8"
    }

}