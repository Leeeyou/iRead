package com.leeeyou.movie.service

import retrofit2.http.Field
import retrofit2.http.POST
import rx.Observable

interface LoginService {

    @POST("user/register")
    fun register(@Field("username") username: String,
                 @Field("password") password: String,
                 @Field("rePassword") rePassword: String): Observable<String>

}