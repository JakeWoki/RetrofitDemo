package com.retrofit.demo.interceptor;

import android.content.Context;

import com.retrofit.demo.converter.*;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class InterceptorPrefs {

    private static volatile InterceptorPrefs singleton;
    private InterceptorService api;

    public static InterceptorPrefs get(Context context) {
        if (singleton == null) {
            synchronized (InterceptorPrefs.class) {
                singleton = new InterceptorPrefs(context);
            }
        }
        return singleton;
    }

    private InterceptorPrefs(Context context) {
    }

    public InterceptorService getApi() {
        if (api == null) createApi();
        return api;
    }

    private void createApi() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new EncryptInterceptor())
                .build();
        api = new Retrofit.Builder()
                .baseUrl(InterceptorService.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InterceptorService.class);
    }
}
