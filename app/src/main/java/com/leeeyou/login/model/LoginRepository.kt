package com.leeeyou.login.model

import com.leeeyou.login.model.bean.User
import com.leeeyou.movie.service.LoginService
import com.leeeyou.service.ServiceFactory
import com.leeeyou.service.entity.HttpResultEntity
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun postRegister(username: String, password: String): Observable<HttpResultEntity<User>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .register(username, password, password)
}

fun postLogin(username: String, password: String): Observable<HttpResultEntity<User>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .login(username, password)
}

fun logout(): Observable<HttpResultEntity<String>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .logout()
}
