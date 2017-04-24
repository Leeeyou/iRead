package com.example.leeeyou.zhihuribao.data.service

import com.example.leeeyou.zhihuribao.data.model.RiBao
import com.example.leeeyou.zhihuribao.data.model.StoryDetail
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by leeeyou on 2017/4/24.
 */
interface ZhiHuRiBaoServiceKT {
    @GET("/api/4/news/before/{dateTime}")
    fun getLatestRiBao(@Path("dateTime") dateTime: String): Observable<RiBao>

    @GET("/api/4/news/{id}")
    fun getStoryDetailById(@Path("id") id: Int): Observable<StoryDetail>
}