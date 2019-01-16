package com.leeeyou.wanandroid.service

import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.wanandroid.model.bean.CollectItem
import retrofit2.http.GET
import rx.Observable

interface CollectService {

    @GET("lg/collect/list/0/json")
    fun getCollectList(): Observable<HttpResultEntity<List<CollectItem>>>

}