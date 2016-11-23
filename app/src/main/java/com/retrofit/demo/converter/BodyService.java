package com.retrofit.demo.converter;

import com.retrofit.demo.model.VersionModel;
import com.retrofit.demo.model.VersionParam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 */

public interface BodyService {

    String ENDPOINT = "http://api.xxx.com/";

    @POST("test")
    Call<VersionModel> version(@Body VersionParam param);
}
