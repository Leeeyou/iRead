package com.example.leeeyou.zhihuribao.data.service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     * @param clazz    Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRxRetrofitService(final Class<T> clazz, final String endPoint) {
        Retrofit rxRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();

        T service = rxRetrofit.create(clazz);

        return service;
    }

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     * @param clazz    Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        Retrofit rxRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();

        T service = rxRetrofit.create(clazz);

        return service;
    }
}
