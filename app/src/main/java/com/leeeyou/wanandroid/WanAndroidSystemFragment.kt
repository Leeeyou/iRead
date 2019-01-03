package com.leeeyou.wanandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.inflate
import com.leeeyou.wanandroid.model.bean.SystemTag
import com.leeeyou.wanandroid.model.fetchSystemTagList
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_wan_android_system.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * ClassName:   WeatherFragment
 * Description: Play Android - System
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidSystemFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_system)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchSystemTagList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Timber.d("fetchSystemTagList doOnNext")

                    it.takeIf { response ->
                        response.errorCode >= 0
                    }?.also { response ->
                        Timber.d(response.data.toString())


                        systemFlowLayout.adapter = object : TagAdapter<SystemTag>(response.data) {
                            override fun getView(parent: FlowLayout?, position: Int, t: SystemTag?): View {
                                t?.let {
                                    val textView = LayoutInflater.from(context).inflate(R.layout.item_system_tag, null) as TextView
                                    textView.text = t.name
                                    return textView
                                }
                            }
                        }

                    } ?: IllegalArgumentException("fetchSystemTagList接口返回异常")
                }.subscribe()


    }
}
