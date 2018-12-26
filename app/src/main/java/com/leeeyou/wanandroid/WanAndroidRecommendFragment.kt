package com.leeeyou.wanandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.inflate
import com.leeeyou.wanandroid.model.bean.Banner
import com.leeeyou.wanandroid.model.bean.ResponseBanner
import com.leeeyou.wanandroid.model.fetchBannerList
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_wan_android_recommend.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber


/**
 * ClassName:   WeatherFragment
 * Description: Play Android - Recommend
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidRecommendFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_recommend)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()

        fetchBannerList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseBanner>() {
                    override fun onNext(t: ResponseBanner) {
                        println("fetchBannerList onNext")
                        println(t)

                        t.takeIf {
                            it.errorCode >= 0
                        }?.also {
                            renderBanner(it.data)
                        } ?: onError(IllegalArgumentException("接口返回异常"))
                    }

                    override fun onCompleted() {
                        println("fetchBannerList onCompleted")
                    }

                    override fun onError(e: Throwable?) {
                        println("fetchBannerList onError")
                    }
                })
    }

    private fun initBanner() {
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                if (context != null && imageView != null) {
                    Glide.with(context)
                            .load(path)
                            .apply(RequestOptions()
                                    .override(banner.width, banner.height)
                                    .fitCenter()
                                    .transforms(CenterCrop(), RoundedCorners(30)))
                            .into(imageView)
                }
            }
        })
        banner.setDelayTime(3000)
        banner.setOnBannerListener {
            Timber.d("click position is %s", it.toString())
        }
    }

    private fun renderBanner(bannerList: List<Banner>) {
        bannerList.map { it.imagePath }.run {
            banner.setImages(this)
            banner.setBannerAnimation(Transformer.Default)
            banner.start()
        }
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

}