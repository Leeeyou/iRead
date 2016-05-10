package com.example.leeeyou.zhihuribao.data.service;

import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by leeeyou on 16/5/10.
 */
public interface ZhiHuRiBaoService {

    @GET("/api/4/news/latest")
    Observable<RiBao> getLatestRiBao();

}
