package com.leeeyou.service

import android.content.Context
import android.os.Environment
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.leeeyou.service.converter.MyGsonConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit


/**
 * ClassName:   ServiceFactory
 * Description: The factory class mainly offers two kinds of Retrofit services, Rx mode and normal mode
 *
 * Author:      leeeyou
 * Date:        2018/4/24 15:11
 */
class ServiceFactory {
    companion object {
        private const val DEFAULT_TIMEOUT: Long = 10
        lateinit var DEFAULT_CONTEXT: WeakReference<Context>

        private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("HttpLogging\$ Start")
            Timber.d("┌────────────────────────────────────────────────────────────────────")
            Timber.tag("HttpLogging\$Result")
            Timber.d("| %s", it)
            Timber.tag("HttpLogging\$TheEnd")
            Timber.d("└────────────────────────────────────────────────────────────────────")
        })

        private fun getOkHttpClient(): OkHttpClient {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            val builder = OkHttpClient.Builder()
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            builder.addInterceptor(loggingInterceptor)
            builder.cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(DEFAULT_CONTEXT.get())))
            builder.cache(Cache(File(Environment.getExternalStorageDirectory(), "RsKotlin"), 10 * 1024 * 1024))
            return builder.build()
        }

        fun <T> createRxRetrofitService(clazz: Class<T>, endPoint: String): T {
            val retrofit = Retrofit.Builder()
                    .baseUrl(endPoint)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(MyGsonConverterFactory.create())
                    .build()
            return retrofit.create(clazz)
        }

        fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
            return Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .build()
                    .create(clazz)
        }
    }
}