package com.leeeyou.service.converter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.leeeyou.service.entity.HttpErrorResponseEntity;
import com.leeeyou.service.entity.HttpResultEntity;
import com.leeeyou.service.exception.HttpResultException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import timber.log.Timber;

public class MyGsonConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public MyGsonConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        String responseStr = responseBody.string();
        Timber.e(responseStr);
        if (TextUtils.isEmpty(responseStr)) {
            HttpErrorResponseEntity errorResponse = new HttpErrorResponseEntity(-200, "The result of the request is returned as null");
            HttpResultException resultException = new HttpResultException(errorResponse.getErrorCode(), errorResponse.getErrorMsg());
            Timber.e("this ResultException msg is %s", "request result is null");
            Timber.d("The responseStr is %s ", responseStr);
            throw resultException;
        }

        HttpResultEntity httpResult;
        try {
            httpResult = gson.fromJson(responseStr, HttpResultEntity.class);
        } catch (JsonSyntaxException e) {
            HttpResultException resultException = new HttpResultException(-201, "Json conversion exception");
            Timber.e("this JsonSyntaxException msg is %s", e.getLocalizedMessage());
            Timber.d("The responseStr is %s ", responseStr);
            throw resultException;
        }

        if (httpResult.getErrorCode() == 0) {
            try {
                return gson.fromJson(responseStr, type);
            } catch (JsonSyntaxException e) {
                Timber.e("this JsonSyntaxException msg is %s", e.getMessage());
                Timber.d("The responseStr is %s ", responseStr);
                HttpErrorResponseEntity errorResponse = new HttpErrorResponseEntity(-202, "Json conversion exception");
                throw new HttpResultException(errorResponse.getErrorCode(), errorResponse.getErrorMsg());
            }
        } else {
            try {
                Timber.e("this ResultException msg is %s", "json convert fail");
                Timber.d("The responseStr is %s ", responseStr);
                HttpErrorResponseEntity errorResponse = gson.fromJson(responseStr, HttpErrorResponseEntity.class);
                throw new HttpResultException(errorResponse.getErrorCode(), errorResponse.getErrorMsg());
            } catch (JsonSyntaxException e) {
                Timber.e("this JsonSyntaxException msg is %s", e.getMessage());
                Timber.d("The responseStr is %s ", responseStr);
                HttpErrorResponseEntity errorResponse = new HttpErrorResponseEntity(-203, "Json conversion exception");
                throw new HttpResultException(errorResponse.getErrorCode(), errorResponse.getErrorMsg());
            }
        }
    }
}
