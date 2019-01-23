package com.leeeyou.zhihudaily.view

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.manager.MyLoadMoreView
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.widget.LoadingDialog
import com.leeeyou.zhihudaily.model.bean.ZhiHuDaily
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyDetail
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyItem
import com.leeeyou.zhihudaily.model.fetchZhiHuDailyDetailById
import com.leeeyou.zhihudaily.model.fetchZhiHuDailyListByDate
import kotlinx.android.synthetic.main.activity_zhihu_daily.*
import org.joda.time.DateTime
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class ZhiHuDailyFragment : BaseFragment() {
    private val mMostDate = 7
    private val mDateList = Array(7) { "" }
    private var mDatePosition = 0
    private lateinit var mAdapter: ZhiHuDailyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_zhihu_daily, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataList()
        initView()
        fetchZhiHuDailyList(mDateList[0])
    }

    private fun updateAdapter(zhiHuDailyItemList: List<ZhiHuDailyItem>) {
        if (mDatePosition == 0) {
            mAdapter.setNewData(zhiHuDailyItemList)
        } else {
            mAdapter.addData(zhiHuDailyItemList)
        }
    }

    private fun initView() {
        initPtr()
        initAdapter()
    }

    private fun initPtr() {
        initHeadView()
        ptrFrameOfZhiHuDaily.disableWhenHorizontalMove(true)
        ptrFrameOfZhiHuDaily.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mDatePosition = 0
                fetchZhiHuDailyList(mDateList[mDatePosition])
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return !recyclerViewRiBao.canScrollVertically(-1) && (activity as com.leeeyou.IndexActivity).appBarLayoutVerticalOffset >= 0
            }
        })
    }

    private fun initHeadView() {
        val header = StoreHouseHeader(context)
        header.setTextColor(resources.getColor(R.color.colorTxtSelected))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("ZhiHu Daily", 15)
        ptrFrameOfZhiHuDaily.headerView = header
        ptrFrameOfZhiHuDaily.addPtrUIHandler(header)
    }

    private fun initAdapter() {
        mAdapter = ZhiHuDailyAdapter(R.layout.item_zhihu_daily, null)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setLoadMoreView(MyLoadMoreView())
        mAdapter.setOnItemChildClickListener { adapter, _, position ->

            var loadingDialog: LoadingDialog? = null
            this@ZhiHuDailyFragment.context?.also {
                loadingDialog = LoadingDialog(it)
                loadingDialog?.show()
            }

            val item: ZhiHuDailyItem? = adapter.getItem(position) as ZhiHuDailyItem
            item?.also {
                fetchZhiHuDailyDetailById(it.id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Subscriber<ZhiHuDailyDetail>() {
                            override fun onCompleted() {
                                Timber.i("fetchZhiHuDailyDetailById onCompleted")
                                loadingDialog?.dismiss()
                            }

                            override fun onError(e: Throwable) {
                                Timber.e(e)
                                loadingDialog?.dismiss()
                            }

                            override fun onNext(storyDetail: ZhiHuDailyDetail) {
                                Timber.i("fetchZhiHuDailyDetailById onNext")
                                loadingDialog?.dismiss()
                                startBrowserActivity(context!!, storyDetail.share_url, storyDetail.title)
                            }
                        })
            }
        }
        mAdapter.setOnLoadMoreListener({
            if (mDatePosition < mMostDate - 1) {
                fetchZhiHuDailyList(mDateList[++mDatePosition])
            } else {
                mAdapter.loadMoreEnd()
            }
        }, recyclerViewRiBao)

        recyclerViewRiBao.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewRiBao.adapter = mAdapter
    }

    private fun initDataList() {
        val now = DateTime.now().plusDays(1)
        for (i in 0 until mMostDate) {
            val tempDateTime = now.minusDays(i)
            val year = tempDateTime.year.toString()
            val month = if (tempDateTime.monthOfYear < 10) "0" + tempDateTime.monthOfYear else tempDateTime.monthOfYear.toString()
            val day = if (tempDateTime.dayOfMonth < 10) "0" + tempDateTime.dayOfMonth else tempDateTime.dayOfMonth.toString()
            mDateList[i] = year + month + day
        }
    }

    private fun fetchZhiHuDailyList(date: String) {
        fetchZhiHuDailyListByDate(date)
                .subscribeOn(Schedulers.newThread())
                .filter { zhiHuDaily ->
                    for (item in zhiHuDaily.stories) {
                        item.date = StringBuilder(zhiHuDaily.date)
                                .insert(4, "-")
                                .insert(7, "-")
                                .toString()
                    }
                    true
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ZhiHuDaily>() {
                    override fun onNext(item: ZhiHuDaily?) {
                        ptrFrameOfZhiHuDaily?.refreshComplete()
                        if (item != null) {
                            updateAdapter(item.stories)
                        }
                    }

                    override fun onCompleted() {
                        mAdapter.loadMoreComplete()
                    }

                    override fun onError(e: Throwable?) {
                        ptrFrameOfZhiHuDaily?.refreshComplete()
                        e?.printStackTrace()
                        Timber.e(e)
                    }
                })
    }
}