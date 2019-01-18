package com.leeeyou.movie.service

import com.leeeyou.login.model.bean.User
import com.leeeyou.service.entity.HttpResultEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface LoginService {

    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") username: String,
                 @Field("password") password: String,
                 @Field("repassword") rePassword: String): Observable<HttpResultEntity<User>>


    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<HttpResultEntity<User>>

    @GET("user/logout/json")
    fun logout(): Observable<HttpResultEntity<String>>
}