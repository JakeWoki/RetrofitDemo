package com.retrofit.demo.interceptor;

import com.retrofit.demo.BuildConfig;
import com.retrofit.demo.CipherUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class EncryptInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Request.Builder builder = request.newBuilder();

        final RequestBody oldBody = request.body();
        RequestBody newBody = null;
        if (oldBody != null) {
            final Buffer buffer = new Buffer();
            oldBody.writeTo(buffer);
            final String strOldBody = buffer.readUtf8();
            final MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
            String strNewBody = null;
            try {
                strNewBody = CipherUtils.Encrypt(strOldBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
            newBody = RequestBody.create(mediaType, "encrypt=" + strNewBody);
        }
        final Request newRequest = builder.header("User-Agent", getUserAgent()).method(request.method(), newBody)
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