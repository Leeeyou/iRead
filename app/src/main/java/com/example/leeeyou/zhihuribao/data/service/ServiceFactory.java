package com.example.leeeyou.zhihuribao.data.service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ServiceFactory {

    //返回带Rx功能的Retrofit
    public static <T> T createRxRetrofitService(final Class<T> clazz, final String endPoint) {
        Retrofit rxRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();

        T service = rxRetrofit.create(clazz);

        return service;
    }

    //返回原生的Retrofit
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        Retrofit rxRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();

        T service = rxRetrofit.create(clazz);

        return service;
    }
}
