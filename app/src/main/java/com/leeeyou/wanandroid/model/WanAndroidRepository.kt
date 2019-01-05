package com.leeeyou.wanandroid.model

import com.leeeyou.factory.ServiceFactory
import com.leeeyou.wanandroid.model.bean.ResponseBanner
import com.leeeyou.wanandroid.model.bean.ResponseRecommendList
import com.leeeyou.wanandroid.model.bean.ResponseSystemTag
import com.leeeyou.wanandroid.model.bean.ResponseSystemTagArticleList
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

fun fetchSystemTagList(): Observable<ResponseSystemTag> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getSystemTagList()
}

fun fetchSystemTagArticleList(pageIndex: Int, cid: Int): Observable<ResponseSystemTagArticleList> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getSystemTagArticleList(pageIndex, cid)
}