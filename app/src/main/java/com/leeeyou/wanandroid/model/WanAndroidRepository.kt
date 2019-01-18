package com.leeeyou.wanandroid.model

import com.leeeyou.service.ServiceFactory
import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.wanandroid.model.bean.*
import com.leeeyou.wanandroid.service.CollectService
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

fun fetchProjectListByRecommend(pageIndex: Int): Observable<HttpResultEntity<RecommendList>> {
    return ServiceFactory.createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getProjectListByRecommend(pageIndex)
}

fun fetchProjectListByCategory(pageIndex: Int, categoryId: Int): Observable<HttpResultEntity<RecommendList>> {
    return ServiceFactory.createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getProjectListByCategory(pageIndex, categoryId)
}

fun fetchProjectCategory(): Observable<HttpResultEntity<List<SystemTag>>> {
    return ServiceFactory.createRxRetrofitService(WanAndroidService::class.java, endPoint)
            .getProjectCategory()
}

fun fetchCollectionList(pageIndex: Int): Observable<HttpResultEntity<CollectList>> {
    return ServiceFactory.createRxRetrofitService(CollectService::class.java, endPoint)
            .getCollectList(pageIndex)
}

fun collectInsideArticle(articleId: Int): Observable<HttpResultEntity<String>> {
    return ServiceFactory.createRxRetrofitService(CollectService::class.java, endPoint)
            .collectInsideArticle(articleId)
}

fun unCollectInsideArticle(articleId: Int): Observable<HttpResultEntity<String>> {
    return ServiceFactory.createRxRetrofitService(CollectService::class.java, endPoint)
            .unCollectInsideArticle(articleId)
}