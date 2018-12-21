package com.xyz.leeeyou.zhihuribao.di.module

import com.xyz.leeeyou.zhihuribao.data.model.ribao.ZhiHuDaily
import com.xyz.leeeyou.zhihuribao.data.model.ribao.ZhiHuDailyDetail
import com.xyz.leeeyou.zhihuribao.data.service.ServiceFactory
import com.xyz.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService
import rx.Observable

private const val endPoint = "http://news-at.zhihu.com"

fun fetchStoryListByDate(date: String): Observable<ZhiHuDaily> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
            .getLatestZhiHuDaily(date)
}

fun fetchStoryDetailById(storyId: Int): Observable<ZhiHuDailyDetail> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
            .getZhiHuDailyDetailById(storyId)
}