package com.retrofit.demo.converter;

import android.text.TextUtils;

import com.retrofit.demo.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Request.Builder builder = request.newBuilder();
        final String contentType = request.header("Content-Type");
        if (TextUtils.isEmpty(contentType) || !contentType.contains("multipart/form-data")) {
            builder.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }
        final Request newRequest = builder.header("User-Agent", getUserAgent())
                .build();
        return chain.proceed(newRequest);
    }

    public static String getUserAgent() {
        String format = "Mozilla/5.0 (%1$s; %2$s; ) OfficeAndroid/%3$s";
        String hardware = android.os.Build.MODEL.replaceAll(" ", "");
        String osVersion = android.os.Build.VERSION.RELEASE;

        String version = BuildConfig.VERSION_NAME;
        String ua = String.format(format, hardware, osVersion,
                version);
        return ua;
    }

}