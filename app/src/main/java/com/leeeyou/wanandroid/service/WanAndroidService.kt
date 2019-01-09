package com.leeeyou.wanandroid.service

import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.wanandroid.model.bean.Banner
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.bean.SystemTag
import com.leeeyou.wanandroid.model.bean.SystemTagArticleList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface WanAndroidService {
    @GET("banner/json")
    fun getBannerList(): Observable<HttpResultEntity<List<Banner>>>

    @GET("article/list/{index}/json")
    fun getRecommendList(@Path("index") index: Int): Observable<HttpResultEntity<RecommendList>>

    @GET("tree/json")
    fun getSystemTagList(): Observable<HttpResultEntity<List<SystemTag>>>

    @GET("article/list/{pageIndex}/json")
    fun getSystemTagArticleList(@Path("pageIndex") pageIndex: Int, @Query("cid") cid: Int): Observable<HttpResultEntity<SystemTagArticleList>>

    @GET("article/listproject/{pageIndex}/json")
    fun getProjectListByRecommend(@Path("pageIndex") pageIndex: Int): Observable<HttpResultEntity<RecommendList>>

    @GET("project/list/{pageIndex}/json")
    fun getProjectListByCategory(@Path("pageIndex") pageIndex: Int, @Query("cid") cid: Int): Observable<HttpResultEntity<RecommendList>>

    @GET("project/tree/json")
    fun getProjectCategory(): Observable<HttpResultEntity<List<SystemTag>>>
}