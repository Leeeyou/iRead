package com.example.leeeyou.zhihuribao.di.module

import com.example.leeeyou.zhihuribao.data.model.RiBao
import com.example.leeeyou.zhihuribao.data.model.StoryDetail
import com.example.leeeyou.zhihuribao.data.service.ServiceFactory
import com.example.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService
import dagger.Module
import dagger.Provides
import rx.Observable
import javax.inject.Singleton

/**
 * Created by leeeyou on 2017/4/24.
 */
@Singleton
@Module
class StoryModule {
    val endPoint = "http://news-at.zhihu.com"
    val endPoint2 = "http://news.at.zhihu.com"

    var storyId: Int = 0
    var date: String = ""

    constructor()

    constructor(date: String) {
        this.date = date
    }

    @Singleton
    @Provides
    fun provideStories(): Observable<RiBao> {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint2)
                .getLatestRiBao(date)
    }

    @Singleton
    @Provides
    fun provideStoryDetail(): Observable<StoryDetail> {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
                .getStoryDetailById(storyId)
    }

}