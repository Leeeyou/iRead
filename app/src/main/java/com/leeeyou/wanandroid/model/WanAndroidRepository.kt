package com.leeeyou.wanandroid.model

import com.leeeyou.factory.ServiceFactory
import com.leeeyou.wanandroid.model.bean.ResponseBanner
import com.leeeyou.wanandroid.model.bean.ResponseRecommendList
import com.leeeyou.wanandroid.service.WanAndroidService
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun fetchBannerList(): Observable<ResponseBanner> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getBannerList()
}

fun fetchRecommendList(index: Int = 0): Observable<ResponseRecommendList> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getRecommendList(index)
}