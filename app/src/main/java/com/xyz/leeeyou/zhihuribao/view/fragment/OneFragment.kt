package com.xyz.leeeyou.zhihuribao.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.adapter.one.MultipleItemQuickAdapterForOneIndex
import com.xyz.leeeyou.zhihuribao.data.model.one.ID
import com.xyz.leeeyou.zhihuribao.data.model.one.Index
import com.xyz.leeeyou.zhihuribao.di.component.DaggerOneComponent
import com.xyz.leeeyou.zhihuribao.di.module.OneModule
import com.xyz.leeeyou.zhihuribao.utils.inflate
import com.xyz.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem
import com.xyz.leeeyou.zhihuribao.view.activity.IndexActivity
import kotlinx.android.synthetic.main.fragment_one.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * ClassName: OneFragment
 * Description: 【一个】fragment , Kotlin style
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class OneFragment : BaseFragment() {

    @Inject
    lateinit var mIndexObservable: Observable<Index>

    @Inject
    lateinit var mIdObservable: Observable<ID>

    private var mNoMoreDataView: View? = null

    private lateinit var mIdList: Array<String>
    private var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null

    private var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = container!!.inflate(R.layout.fragment_one)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        updateData()
    }

    private fun initAdapter() {
        recyclerViewOne?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mIndexAdapter = MultipleItemQuickAdapterForOneIndex(null)
        mIndexAdapter?.setOnLoadMoreListener({
            if (mPosition < mIdList.size - 1) {
                loadIndexData(++mPosition)
            }
        }, recyclerViewOne)
        mIndexAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)

        recyclerViewOne.adapter = mIndexAdapter
    }

    //get the ID collection for all categories
    private fun fetchIdData() {
        mIdObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as IndexActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            mIdList = it.data
                            mPosition = 0
                            loadIndexData(mPosition)
                        }
                )
    }

    private fun loadIndexData(position: Int) {
        DaggerOneComponent.builder()
                .oneModule(OneModule(mIdList[position].toInt()))
                .build()
                .inject(this)

        fetchIndexData()
    }

    private fun fetchIndexData() {
        mIndexObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as IndexActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            (activity as IndexActivity).refreshComplete()

                            val indexData = parseIndexData(it)

                            val isPullToRefresh = mPosition == 0
                            if (isPullToRefresh) {
                                mIndexAdapter?.setNewData(indexData)
                            } else {
                                mIndexAdapter?.addData(indexData)
                                mIndexAdapter?.loadMoreComplete()
                            }
                        })
    }

    private fun parseIndexData(index: Index): MutableList<OneIndexMultipleItem> {
        val tempDataList: MutableList<OneIndexMultipleItem> = ArrayList()

        //if it is a drop-down refresh, add the weather UI
        val isPullToRefresh = mPosition == 0
        if (isPullToRefresh) {
            tempDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, index.data.weather))
        }

        //parse data
        val contentList = index.data.content_list
        for (i in contentList.indices) {
            tempDataList.add(OneIndexMultipleItem(if (i == 0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            tempDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }

        //if the current value of mPosition is equal to the maximum value of mIdList, the end is loaded more
        val isLoadMoreEnd = mPosition == mIdList.size - 1
        if (isLoadMoreEnd) {
            loadMoreEnd()
        }

        return tempDataList
    }

    override fun checkCanDoRefresh(): Boolean = !recyclerViewOne.canScrollVertically(-1)

    override fun updateData() {
        mIndexAdapter?.setEnableLoadMore(true)
        mIndexAdapter?.removeAllFooterView()
        DaggerOneComponent.builder().oneModule(OneModule()).build().inject(this)
        fetchIdData()
    }


    private fun loadMoreEnd() {
        mIndexAdapter?.loadMoreEnd()
        mIndexAdapter?.setEnableLoadMore(false)
        if (mNoMoreDataView == null) {
            @SuppressWarnings("unchecked")
            mNoMoreDataView = LayoutInflater.from(context).inflate(R.layout.not_loading, null, false)
        }
        mIndexAdapter?.addFooterView(mNoMoreDataView)
    }

}