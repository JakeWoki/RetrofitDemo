package com.retrofit.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.retrofit.demo.converter.BodyPrefs;
import com.retrofit.demo.converter.FieldPrefs;
import com.retrofit.demo.converter.FieldService;
import com.retrofit.demo.interceptor.InterceptorPrefs;
import com.retrofit.demo.interceptor.InterceptorService;
import com.retrofit.demo.model.VersionModel;
import com.retrofit.demo.model.VersionParam;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FieldPrefs fieldPrefs;
    BodyPrefs bodyPrefs;
    InterceptorPrefs interceptorPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fieldPrefs = FieldPrefs.get(this);
        bodyPrefs = BodyPrefs.get(this);
        interceptorPrefs = InterceptorPrefs.get(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //stringConverter not work
                final Call<VersionModel> commentsCall = fieldPrefs.getApi().version(FieldService.ANDROID_DEVICE, BuildConfig.VERSION_NAME, "dddddddddddddd");




                //requestBodyConverter can work
//                VersionParam param = new VersionParam();
//                param.type = "android";
//                param.appVersion = "1.0";
//                param.token = "gggggggggggg";
//                final Call<VersionModel> commentsCall = bodyPrefs.getApi().version(param);




                //the first time work,the second time fail,the third time work...
//                final Call<VersionModel> commentsCall = interceptorPrefs.getApi().version(InterceptorService.ANDROID_DEVICE, BuildConfig.VERSION_NAME, "gggggggggggg");



                commentsCall.enqueue(new Callback<VersionModel>() {
                    @Override
                    public void onResponse(Call<VersionModel> call, Response<VersionModel> response) {
                        if (response.isSuccessful()) {
                            Log.v("test", "Successful");
                        } else {
                            Log.v("test", "====");
                        }
                    }

                    @Override public void onFailure(Call<VersionModel> call, Throwable t) {
                        Log.v("test", "onFailure");
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
