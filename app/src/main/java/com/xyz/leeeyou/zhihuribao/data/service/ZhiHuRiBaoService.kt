package com.xyz.leeeyou.zhihuribao.data.service

import com.xyz.leeeyou.zhihuribao.data.model.ribao.RiBao
import com.xyz.leeeyou.zhihuribao.data.model.ribao.StoryDetail
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by leeeyou on 2017/4/24.
 */
interface ZhiHuRiBaoService {
    @GET("/api/4/news/before/{dateTime}")
    fun getLatestRiBao(@Path("dateTime") dateTime: String): Observable<RiBao>

    @GET("/api/4/news/{id}")
    fun getStoryDetailById(@Path("id") id: Int): Observable<StoryDetail>
}