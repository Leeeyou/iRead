package com.example.leeeyou.zhihuribao.di.module

import com.example.leeeyou.zhihuribao.data.model.one.Index
import com.example.leeeyou.zhihuribao.data.service.OneService
import com.example.leeeyou.zhihuribao.data.service.ServiceFactory
import dagger.Module
import dagger.Provides
import rx.Observable
import javax.inject.Singleton

/**
 * Created by leeeyou on 2017/4/24.
 */
@Singleton
@Module
class OneModule {
    val endPoint = "http://v3.wufazhuce.com:8000"

    var id: Int = 0

    @Singleton
    @Provides
    fun provideOne(): Observable<Index> {
        return ServiceFactory.Companion
                .createRxRetrofitService(OneService::class.java, endPoint)
                .getIndexList(id)
    }
}