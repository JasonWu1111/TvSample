package com.example.tvsample.network;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JasonWu on 29/12/2017
 */

public class RetrofitService {
    private static final String HOST = "";

    private static Api api;

    public static void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(createHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private static OkHttpClient createHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new StethoInterceptor())
                .build();
    }
}
