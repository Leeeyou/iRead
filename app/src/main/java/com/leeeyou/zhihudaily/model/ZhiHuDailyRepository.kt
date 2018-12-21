package com.leeeyou.zhihudaily.model

import com.leeeyou.ServiceFactory
import com.leeeyou.zhihudaily.model.bean.ZhiHuDaily
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyDetail
import com.leeeyou.zhihudaily.service.ZhiHuDailyService
import rx.Observable

private const val endPoint = "http://news-at.zhihu.com"

fun fetchStoryListByDate(date: String): Observable<ZhiHuDaily> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuDailyService::class.java, endPoint)
            .getLatestZhiHuDaily(date)
}

fun fetchStoryDetailById(storyId: Int): Observable<ZhiHuDailyDetail> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuDailyService::class.java, endPoint)
            .getZhiHuDailyDetailById(storyId)
}