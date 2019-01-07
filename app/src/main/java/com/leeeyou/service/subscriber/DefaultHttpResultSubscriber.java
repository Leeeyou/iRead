package com.leeeyou.service.subscriber;

import timber.log.Timber;

public abstract class DefaultHttpResultSubscriber<T> extends HttpResultSubscriber<T> {
    @Override
    public void _onError(int status, String msg) {

    }

    @Override
    public void onCompleted() {
        Timber.d(" --> onCompleted");
    }
}
