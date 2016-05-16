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

    public int storyId;

    public StoryModule() {
    }

    public StoryModule(int storyId) {
        this.storyId = storyId;
    }

    @Provides
    public Observable<RiBao> provideStories() {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService.class, endPoint)
                .getLatestRiBao();
    }

    @Provides
    public Observable<StoryDetail> provideStoryDetail() {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService.class, endPoint)
                .getStoryDetailById(storyId);
    }


}
