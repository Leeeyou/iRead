package com.xyz.leeeyou.zhihuribao.di.module

import com.xyz.leeeyou.zhihuribao.data.model.ribao.RiBao
import com.xyz.leeeyou.zhihuribao.data.model.ribao.StoryDetail
import com.xyz.leeeyou.zhihuribao.data.service.ServiceFactory
import com.xyz.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService
import rx.Observable

private const val endPoint = "http://news-at.zhihu.com"

fun fetchStoryListByDate(date: String): Observable<RiBao> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
            .getLatestRiBao(date)
}

fun fetchStoryDetailById(storyId: Int): Observable<StoryDetail> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
            .getStoryDetailById(storyId)
}