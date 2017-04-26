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
import com.example.leeeyou.zhihuribao.data.model.one.Index
import com.example.leeeyou.zhihuribao.di.component.DaggerOneComponent
import com.example.leeeyou.zhihuribao.di.module.OneModule
import com.example.leeeyou.zhihuribao.utils.inflate
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by leeeyou on 2017/4/24.
 */
class OneFragment : Fragment() {

    //    TODO
    @Inject
    lateinit var indexObservable: Observable<Index>

    lateinit var mRecyclerView: RecyclerView
    var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null

    var indexList: MutableList<OneIndexMultipleItem> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container!!.inflate(R.layout.fragment_one)
        mRecyclerView = rootView.findViewById(R.id.recyclerView_one) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerOneComponent.builder().oneModule(OneModule(4002)).build().inject(this)
        fetchOneData()
    }

    private fun fetchOneData() {
        indexObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { it.printStackTrace() },
                        onCompleted = { println("Done!") },
                        onNext = {
                            print(it.data)

//                            TODO() pull up or pull down
                            parseData(it)

                            if (mIndexAdapter == null) {
                                mIndexAdapter = MultipleItemQuickAdapterForOneIndex(indexList)
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

        indexList.clear()
        indexList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, weather))

        for (i in contentList.indices) {
            indexList.add(OneIndexMultipleItem(if (i == 0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            indexList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }
    }


}