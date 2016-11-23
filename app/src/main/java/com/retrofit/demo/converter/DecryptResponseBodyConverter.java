package com.retrofit.demo.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.retrofit.demo.CipherUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 *
 * Created by owp on 2016/11/22.
 */

final class DecryptResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DecryptResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        /*JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }*/
        //
        final String response = value.string();
        try {
            return adapter.fromJson(CipherUtils.Decrypt(response));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            value.close();
        }
    }
}