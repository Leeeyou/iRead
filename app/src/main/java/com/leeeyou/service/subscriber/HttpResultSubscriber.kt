package com.leeeyou.service.subscriber

import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.service.exception.HttpResultException
import rx.Subscriber
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

abstract class HttpResultSubscriber<T> : Subscriber<HttpResultEntity<T>>() {
    override fun onCompleted() {

    }

    override fun onError(e: Throwable) {
        var code = -1000
        var msg: String? = null
        if (e is ConnectException
                || e is SocketTimeoutException
                || e is TimeoutException) {
            code = -9999
            msg = "network anomaly"

        } else if (e is HttpResultException) {
            code = e.code
            msg = e.msg
        }
        Timber.e("Request to enter the onError of HttpResultSubscriber , status is %s , msg is %s", code, msg)
        _onError(code, msg)
        onCompleted()
    }

    override fun onNext(httpResult: HttpResultEntity<T>) {
        httpResult.takeIf { it.isSuccess }?.also {
            onSuccess(httpResult.data)
        } ?: _onError(httpResult.errorCode, "")
    }

    abstract fun onSuccess(data: T?)

    abstract fun _onError(status: Int, msg: String?)
}
