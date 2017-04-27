package com.example.leeeyou.zhihuribao.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.adapter.one.MultipleItemQuickAdapterForOneIndex
import com.example.leeeyou.zhihuribao.data.model.one.ID
import com.example.leeeyou.zhihuribao.data.model.one.Index
import com.example.leeeyou.zhihuribao.di.component.DaggerOneComponent
import com.example.leeeyou.zhihuribao.di.module.OneModule
import com.example.leeeyou.zhihuribao.utils.inflate
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem
import com.example.leeeyou.zhihuribao.view.activity.IndexActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by leeeyou on 2017/4/24.
 *
 * 一个主界面
 *
 */
class OneFragment : BaseFragment() {

    @Inject
    lateinit var mIndexObservable: Observable<Index>

    @Inject
    lateinit var mIdObservable: Observable<ID>

    internal var mNoMoreDataView: View? = null

    lateinit var mIdList: Array<String>
    lateinit var mRecyclerView: RecyclerView
    var mIndexList: MutableList<OneIndexMultipleItem> = ArrayList()
    var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null

    var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container!!.inflate(R.layout.fragment_one)
        mRecyclerView = rootView.findViewById(R.id.recyclerView_one) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateData()
    }

    private fun fetchIdData() {
        mIdObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
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
        DaggerOneComponent.builder().oneModule(OneModule(mIdList[position].toInt())).build().inject(this)
        fetchIndexData()
    }

    private fun fetchIndexData() {
        mIndexObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as IndexActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            (activity as IndexActivity).refreshComplete()

                            parseIndexData(it)

                            if (mIndexAdapter == null) {
                                mIndexAdapter = MultipleItemQuickAdapterForOneIndex(mIndexList)
                                mRecyclerView.adapter = mIndexAdapter
                                mIndexAdapter?.setOnLoadMoreListener {
                                    if (mPosition < mIdList.size - 1) {
                                        loadIndexData(++mPosition)
                                    }
                                }
                            } else {
                                mIndexAdapter?.dataAdded()
                            }
                        })
    }

    private fun parseIndexData(index: Index) {
        if (mPosition == 0) {
            mIndexList.clear()
            mIndexList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, index.data.weather))
        }

        val contentList = index.data.content_list
        for (i in contentList.indices) {
            mIndexList.add(OneIndexMultipleItem(if (i == 0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            mIndexList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }

        if (mPosition == mIdList.size - 1) {
            mIndexList.removeAt(mIndexList.size - 1)
            loadMoreComplete()
        }
    }

    override fun checkCanDoRefresh(): Boolean {
        return !mRecyclerView.canScrollVertically(-1)
    }

    override fun updateData() {
        mIndexAdapter?.removeAllFooterView()
        DaggerOneComponent.builder().oneModule(OneModule()).build().inject(this)
        fetchIdData()
    }

    private fun loadMoreComplete() {
        mIndexAdapter?.loadComplete()
        if (mNoMoreDataView == null) {
            mNoMoreDataView = LayoutInflater.from(context).inflate(R.layout.not_loading, null, false)
        }
        mIndexAdapter?.addFooterView(mNoMoreDataView)
    }

}