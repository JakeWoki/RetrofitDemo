# RetrofitDemo

<br>***First***
<br>final Call<VersionModel> commentsCall = fieldPrefs.getApi().version(FieldService.ANDROID_DEVICE, BuildConfig.VERSION_NAME, "dddddddddddddd");
<br>if @Field stringConverter not work.
<br>
<br>
<br>
<br>***Second***
``` Java
VersionParam param = new VersionParam();
param.type = "android";
param.appVersion = "1.0";
param.token = "gggggggggggg";
final Call<VersionModel> commentsCall = bodyPrefs.getApi().version(param);
```
<br>if @Body requestBodyConverter can work.
<br>
<br>
<br>
<br>***Third***
<br>
<br>final Call<VersionModel> commentsCall = interceptorPrefs.getApi().version(InterceptorService.ANDROID_DEVICE, BuildConfig.VERSION_NAME, "gggggggggggg");
<br>if used Interceptor,the first time work,the second time fail,the third time work...
<br>
<br>![image](https://github.com/JakeWoki/RetrofitDemo/blob/master/20161123105759.png?raw=true)
<br>![image](https://github.com/JakeWoki/RetrofitDemo/blob/master/20161123105837.png?raw=true)

