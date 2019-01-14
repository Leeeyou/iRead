package com.leeeyou.login.model

import com.leeeyou.movie.service.LoginService
import com.leeeyou.service.ServiceFactory
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun postRegister(username: String, password: String): Observable<String> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .register(username, password, password)
}