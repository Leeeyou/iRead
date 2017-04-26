package com.example.leeeyou.zhihuribao.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
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
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by leeeyou on 2017/4/24.
 */
class OneFragment : Fragment() {

    @Inject
    lateinit var mIndexObservable: Observable<Index>
    @Inject
    lateinit var mIdObservable: Observable<ID>

    lateinit var mRecyclerView: RecyclerView
//    lateinit var mPtrFrameLayout: PtrFrameLayout
    var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null
    var mIndexList: MutableList<OneIndexMultipleItem> = ArrayList()
    lateinit var mIdList: Array<String>
    lateinit var mOneModule: OneModule

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container!!.inflate(R.layout.fragment_one)
//        mPtrFrameLayout = rootView.findViewById(R.id.store_house_ptr_frame) as PtrFrameLayout
        mRecyclerView = rootView.findViewById(R.id.recyclerView_one) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mOneModule = OneModule()
        DaggerOneComponent.builder().oneModule(mOneModule).build().inject(this)
        fetchIdData()

//        mPtrFrameLayout.isEnabledNextPtrAtOnce = true
//        mPtrFrameLayout.isKeepHeaderWhenRefresh = true
//        mPtrFrameLayout.offsetToKeepHeaderWhileLoading = 55
//        mPtrFrameLayout.setLoadingMinTime(1000)
//        mPtrFrameLayout.resistance = 4.0f
//        mPtrFrameLayout.disableWhenHorizontalMove(true)
//
//        mPtrFrameLayout.setPtrHandler(object : PtrHandler {
//            override fun onRefreshBegin(frame: PtrFrameLayout) {
//                Log.e("TAGTAG", "onRefreshBegin onRefreshBegin")
//            }
//
//            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
//                return !mRecyclerView.canScrollVertically(-1) && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
//            }
//        })
//
//        mPtrFrameLayout.postDelayed({ mPtrFrameLayout.autoRefresh() }, 100)
    }

    private fun fetchIdData() {
        mIdObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { it.printStackTrace() },
                        onCompleted = { println("Done!") },
                        onNext = {
                            mIdList = it.data
                            mOneModule.id = mIdList[0].toInt()
                            DaggerOneComponent.builder().oneModule(mOneModule).build().inject(this)
                            fetchOneData()
                        }
                )
    }

    private fun fetchOneData() {
        mIndexObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { it.printStackTrace() },
                        onCompleted = { println("Done!") },
                        onNext = {
                            //                            TODO() pull up or pull down
                            parseData(it)

                            if (mIndexAdapter == null) {
                                mIndexAdapter = MultipleItemQuickAdapterForOneIndex(mIndexList)
                                mRecyclerView.adapter = mIndexAdapter
                            } else {
                                mIndexAdapter!!.dataAdded()
                            }
                        })
    }

    private fun parseData(index: Index) {
        val data = index.data
        val weather = data.weather
        val contentList = data.content_list

        mIndexList.clear()
        mIndexList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, weather))

        for (i in contentList.indices) {
            mIndexList.add(OneIndexMultipleItem(if (i == 0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            mIndexList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }
    }


}