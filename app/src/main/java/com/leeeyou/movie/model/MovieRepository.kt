package com.leeeyou.movie.model

import com.leeeyou.movie.model.bean.MovieDetail
import com.leeeyou.movie.model.bean.ResponseHotMovie
import com.leeeyou.movie.service.MovieService
import com.leeeyou.service.ServiceFactory
import rx.Observable

private const val endPoint = "https://api.douban.com/"

fun fetchHotMovieList(): Observable<ResponseHotMovie> {
    return ServiceFactory
            .createRxRetrofitService(MovieService::class.java, endPoint)
            .getHotMovieList()
}

fun fetchMovieDetail(movieId: String): Observable<MovieDetail> {
    return ServiceFactory.createRxRetrofitService(MovieService::class.java, endPoint)
            .fetchMovieDetail(movieId)
}