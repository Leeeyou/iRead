package com.leeeyou.movie.service

import com.leeeyou.movie.model.bean.ResponseHotMovie
import retrofit2.http.GET
import rx.Observable

interface MovieService {
    @GET("v2/movie/in_theaters")
    fun getHotMovieList(): Observable<ResponseHotMovie>
}