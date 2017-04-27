package com.xyz.leeeyou.zhihuribao.data.service

import com.xyz.leeeyou.zhihuribao.data.model.one.ID
import com.xyz.leeeyou.zhihuribao.data.model.one.Index
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by leeeyou on 2017/4/24.
 *
 * restful
 */
interface OneService {
    @GET("/api/onelist/{id}/0?cchannel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    fun getIndexList(@Path("id") id: Int): Observable<Index>

    @GET("/api/onelist/idlist/?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    fun getIdList(): Observable<ID>
}