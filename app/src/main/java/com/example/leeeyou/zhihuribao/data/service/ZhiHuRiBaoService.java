package com.example.leeeyou.zhihuribao.data.service;

import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.StoryDetail;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by leeeyou on 16/5/10.
 * <p>
 * 知乎日报业务服务类
 */
public interface ZhiHuRiBaoService {

    @GET("/api/4/news/before/{dateTime}")
    Observable<RiBao> getLatestRiBao(@Path("dateTime") String dateTime);

    @GET("/api/4/news/{id}")
    Observable<StoryDetail> getStoryDetailById(@Path("id") int id);

}
