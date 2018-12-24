package com.leeeyou.wanandroid.service

import com.leeeyou.wanandroid.model.bean.ResponseBanner
import retrofit.http.GET
import rx.Observable

interface WanAndroidService {

    @GET("banner/json")
    fun getBannerList(): Observable<ResponseBanner>

}