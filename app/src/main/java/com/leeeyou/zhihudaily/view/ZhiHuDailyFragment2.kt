package com.leeeyou.zhihudaily.view

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.T.showShort
import com.leeeyou.zhihudaily.model.bean.ZhiHuDaily
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyItem
import com.leeeyou.zhihudaily.model.fetchZhiHuDailyListByDate
import kotlinx.android.synthetic.main.activity_zhihu_daily.*
import org.joda.time.DateTime
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class ZhiHuDailyFragment2 : BaseFragment() {
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
                        ptrFrameOfZhiHuDaily.refreshComplete()
                        if (item != null) {
                            setDataToAdapter(item.stories)
                        }
                    }

                    override fun onCompleted() {
                        mAdapter.loadMoreComplete()
                    }

                    override fun onError(e: Throwable?) {
                        ptrFrameOfZhiHuDaily.refreshComplete()
                        e?.printStackTrace()
                        showShort(activity, "出错了:" + e?.message)
                    }
                })
    }

    private fun setDataToAdapter(zhiHuDailyItemList: List<ZhiHuDailyItem>) {
        if (mDatePosition == 0) {
            mAdapter.setNewData(zhiHuDailyItemList)
        } else {
            mAdapter.addData(zhiHuDailyItemList)
        }
    }

    private fun initView() {
        ptrFrameOfZhiHuDaily.disableWhenHorizontalMove(true)
        ptrFrameOfZhiHuDaily.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mDatePosition = 0
                fetchZhiHuDailyList(mDateList[mDatePosition])
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return !recyclerViewRiBao.canScrollVertically(-1)
            }
        })

        initAdapter()
    }

    private fun initAdapter() {
        mAdapter = ZhiHuDailyAdapter(R.layout.item_zhihu_daily, null)
        recyclerViewRiBao.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewRiBao.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setOnLoadMoreListener({
            if (mDatePosition < mMostDate - 1) {
                fetchZhiHuDailyList(mDateList[++mDatePosition])
            } else {
                mAdapter.loadMoreEnd()
            }
        }, recyclerViewRiBao)

    }

    private fun initDataList() {
        val now = DateTime.now().plusDays(1)
        Log.e("xxx", now.toString())

        for (i in 0 until mMostDate) {
            val tempDateTime = now.minusDays(i)
            Log.e("xxx", i.toString() + " - " + tempDateTime.toString())
            mDateList[i] = tempDateTime.year.toString() +
                    (if (tempDateTime.monthOfYear < 10) "0" + tempDateTime.monthOfYear else tempDateTime.monthOfYear) +
                    if (tempDateTime.dayOfMonth < 10) "0" + tempDateTime.dayOfMonth else tempDateTime.dayOfMonth

        }
        Log.e("xxx", Arrays.toString(mDateList))
    }


}