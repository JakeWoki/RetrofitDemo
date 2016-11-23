package com.retrofit.demo.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.retrofit.demo.CipherUtils;

import java.io.IOException;

import retrofit2.Converter;

/**
 *
 *
 */

final class EncryptRequestBodyStringConverter<T> implements Converter<T, String> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    EncryptRequestBodyStringConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public String convert(T value) throws IOException {
        Log.i("test", "just test:" + value.toString());
        try {
            return "encrypt=" + CipherUtils.Encrypt(new Gson().toJson(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value.toString();
    }
}