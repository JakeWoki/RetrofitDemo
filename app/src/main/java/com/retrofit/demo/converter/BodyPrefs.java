package com.retrofit.demo.converter;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 */

public class BodyPrefs {

    private static volatile BodyPrefs singleton;
    private BodyService api;

    public static BodyPrefs get(Context context) {
        if (singleton == null) {
            synchronized (BodyPrefs.class) {
                singleton = new BodyPrefs(context);
            }
        }
        return singleton;
    }

    private BodyPrefs(Context context) {
    }

    public BodyService getApi() {
        if (api == null) createApi();
        return api;
    }

    private void createApi() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor())
                .build();
        api = new Retrofit.Builder()
                .baseUrl(BodyService.ENDPOINT)
                .client(client)
                .addConverterFactory(EDConverterFactory.create())
                .build()
                .create(BodyService.class);
    }
}
