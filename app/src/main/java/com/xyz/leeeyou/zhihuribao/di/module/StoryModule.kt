package com.xyz.leeeyou.zhihuribao.di.module

import com.xyz.leeeyou.zhihuribao.data.model.ribao.RiBao
import com.xyz.leeeyou.zhihuribao.data.model.ribao.StoryDetail
import com.xyz.leeeyou.zhihuribao.data.service.ServiceFactory
import com.xyz.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService
import dagger.Module
import dagger.Provides
import rx.Observable
import javax.inject.Singleton

/**
 * ClassName:   StoryModule
 * Description:
 *
 * Author:      leeeyou
 * Date:        2018/2/24 15:16
 */
private const val endPoint = "http://news-at.zhihu.com"

@Singleton
@Module
class StoryModule {
    var storyId: Int = 0
    var date: String = ""

    constructor()

    constructor(date: String) {
        this.date = date
    }

    @Singleton
    @Provides
    fun provideStories(): Observable<RiBao> {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
                .getLatestRiBao(date)
    }

    @Singleton
    @Provides
    fun provideStoryDetail(): Observable<StoryDetail> {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
                .getStoryDetailById(storyId)
    }

}