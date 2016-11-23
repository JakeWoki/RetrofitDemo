package com.retrofit.demo.converter;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 */

public class FieldPrefs {

    private static volatile FieldPrefs singleton;
    private FieldService api;

    public static FieldPrefs get(Context context) {
        if (singleton == null) {
            synchronized (FieldPrefs.class) {
                singleton = new FieldPrefs(context);
            }
        }
        return singleton;
    }

    private FieldPrefs(Context context) {
    }

    public FieldService getApi() {
        if (api == null) createApi();
        return api;
    }

    private void createApi() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();
        api = new Retrofit.Builder()
                .baseUrl(FieldService.ENDPOINT)
                .client(client)
                .addConverterFactory(EDConverterFactory.create())
                .build()
                .create(FieldService.class);
    }
}
