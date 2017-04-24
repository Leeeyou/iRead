package com.example.leeeyou.zhihuribao.di.module;

import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.StoryDetail;
import com.example.leeeyou.zhihuribao.data.service.ServiceFactory;
import com.example.leeeyou.zhihuribao.data.service.ZhiHuRiBaoServiceKT;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Singleton
@Module
public class StoryModule {

    private String endPoint = "http://news-at.zhihu.com";
    private String endPoint2 = "http://news.at.zhihu.com";

    public int storyId;

    public String date;//格式形如:20160928

    public StoryModule() {
    }

    public StoryModule(String date) {
        this.date = date;
    }

    @Singleton
    @Provides
    public Observable<RiBao> provideStories() {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoServiceKT.class, endPoint2)
                .getLatestRiBao(date);
    }

    @Singleton
    @Provides
    public Observable<StoryDetail> provideStoryDetail() {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoServiceKT.class, endPoint)
                .getStoryDetailById(storyId);
    }


}
