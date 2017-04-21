package com.example.leeeyou.zhihuribao.di.module;

import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.StoryDetail;
import com.example.leeeyou.zhihuribao.data.service.ServiceFactory;
import com.example.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

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

    @Provides
    public Observable<RiBao> provideStories() {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService.class, endPoint2)
                .getLatestRiBao(date);
    }

    @Provides
    public Observable<StoryDetail> provideStoryDetail() {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService.class, endPoint)
                .getStoryDetailById(storyId);
    }


}
