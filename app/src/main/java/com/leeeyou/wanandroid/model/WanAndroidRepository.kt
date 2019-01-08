package com.leeeyou.wanandroid.model

import com.leeeyou.service.ServiceFactory
import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.wanandroid.model.bean.Banner
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.bean.SystemTag
import com.leeeyou.wanandroid.model.bean.SystemTagArticleList
import com.leeeyou.wanandroid.service.WanAndroidService
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun fetchBannerList(): Observable<HttpResultEntity<List<Banner>>> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getBannerList()
}

fun fetchRecommendList(index: Int = 0): Observable<HttpResultEntity<RecommendList>> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getRecommendList(index)
}

fun fetchSystemTagList(): Observable<HttpResultEntity<List<SystemTag>>> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getSystemTagList()
}

fun fetchSystemTagArticleList(pageIndex: Int, cid: Int): Observable<HttpResultEntity<SystemTagArticleList>> {
    return ServiceFactory
            .createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getSystemTagArticleList(pageIndex, cid)
}

fun fetchProjectList(pageIndex: Int): Observable<HttpResultEntity<RecommendList>> {
    return ServiceFactory.createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getProjectList(pageIndex)
}