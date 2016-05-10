package com.example.leeeyou.zhihuribao.di.module;

import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.data.service.ServiceFactory;
import com.example.leeeyou.zhihuribao.data.service.ZhiHuRiBaoService;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class StoryModule {

    private String endPoint = "http://news-at.zhihu.com";

    @Provides
    public Observable<RiBao> provideStories() {
        return ServiceFactory
                .createRxRetrofitService(ZhiHuRiBaoService.class, endPoint)
                .getLatestRiBao();
    }

}
