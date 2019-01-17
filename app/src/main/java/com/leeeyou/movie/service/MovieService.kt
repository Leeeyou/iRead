package com.leeeyou.movie.service

import com.leeeyou.movie.model.bean.MovieDetail
import com.leeeyou.movie.model.bean.ResponseHotMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface MovieService {
    @GET("v2/movie/in_theaters")
    fun getHotMovieList(): Observable<ResponseHotMovie>

    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("v2/movie/subject/{id}")
    fun fetchMovieDetail(@Path("id") id: String): Observable<MovieDetail>

    /**
     * 获取豆瓣电影top250
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     */
    @GET("v2/movie/top250")
    fun fetchMovieTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<String>
}