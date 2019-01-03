package com.leeeyou.wanandroid.service

import com.leeeyou.wanandroid.model.bean.ResponseBanner
import com.leeeyou.wanandroid.model.bean.ResponseRecommendList
import com.leeeyou.wanandroid.model.bean.ResponseSystemTag
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface WanAndroidService {

    @GET("banner/json")
    fun getBannerList(): Observable<ResponseBanner>

    @GET("article/list/{index}/json")
    fun getRecommendList(@Path("index") index: Int): Observable<ResponseRecommendList>

    @GET("tree/json")
    fun getSystemTagList():Observable<ResponseSystemTag>
}