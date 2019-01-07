package com.leeeyou.service.subscriber;

import com.leeeyou.service.entity.HttpResultEntity;
import com.leeeyou.service.exception.HttpResultException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import timber.log.Timber;

public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResultEntity<T>> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        int code = -1000;
        String msg = null;
        if (e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof TimeoutException) {
            code = -9999;
            msg = "network anomaly";

        } else if (e instanceof HttpResultException) {
            HttpResultException resultException = (HttpResultException) e;
            code = resultException.getCode();
            msg = resultException.getMsg();
        }
        Timber.e("Request to enter the onError of HttpResultSubscriber , status is %s , msg is %s", code, msg);
        _onError(code, msg);
        onCompleted();
    }

    @Override
    public void onNext(HttpResultEntity<T> httpResult) {
        if (httpResult.isSuccess()) {
            onSuccess(httpResult.getData());
        } else {
            _onError(httpResult.getErrorCode(), "");
        }
    }

    public abstract void onSuccess(T t);

    public abstract void _onError(int status, String msg);
}
