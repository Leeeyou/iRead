package com.example.leeeyou.zhihuribao.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.data.model.one.Index
import com.example.leeeyou.zhihuribao.di.component.DaggerOneComponent
import com.example.leeeyou.zhihuribao.di.module.OneModule
import com.example.leeeyou.zhihuribao.utils.inflate
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.fragment_one)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val oneModule = OneModule()
        oneModule.id = 4002

        DaggerOneComponent
                .builder()
                .oneModule(oneModule)
                .build()
                .inject(this)

        fetchOneData()
    }

    private fun fetchOneData() {
        indexObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { it.printStackTrace() },
                        onCompleted = { println("Done!") },
                        onNext = ::println)
    }

}