package com.retrofit.demo.converter;

import android.support.annotation.StringDef;

import com.retrofit.demo.model.VersionModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *
 */

public interface FieldService {

    String ENDPOINT = "http://api.xxx.com/";

    @FormUrlEncoded
    @POST("test")
    Call<VersionModel> version(@Field("type") @DeviceType String type, @Field("appVersion") String appVersion, @Field("token") String token);

    String ANDROID_DEVICE = "android";

    // Short sort order
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ANDROID_DEVICE
    })
    @interface DeviceType {}
}
