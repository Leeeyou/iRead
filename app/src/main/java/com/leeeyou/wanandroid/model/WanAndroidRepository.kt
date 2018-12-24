package com.leeeyou.wanandroid.model

import com.leeeyou.factory.ServiceFactory
import com.leeeyou.wanandroid.model.bean.ResponseBanner
import com.leeeyou.wanandroid.service.WanAndroidService
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun fetchBannerList(): Observable<ResponseBanner> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getBannerList()
}