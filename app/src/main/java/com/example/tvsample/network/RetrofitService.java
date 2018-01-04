package com.example.tvsample.network;

import android.content.pm.PackageManager;

import com.example.tvsample.MyApplication;
import com.example.tvsample.entity.BaseInfo;
import com.example.tvsample.entity.UserEntity;
import com.example.tvsample.utils.SharedPreferencesUtil;
import com.example.tvsample.utils.SignatureUtil;
import com.example.tvsample.utils.VerifySignUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JasonWu on 29/12/2017
 */

public class RetrofitService {
    private static final String HOST = "http://192.168.5.170:8090/";

    private static Api api;

    public static void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(createHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new StethoInterceptor())
                .build();
    }

    //请求登录
    public static Flowable<BaseInfo<UserEntity>> getLoginInfo(String identifier, String name, String email, String gender
            , String avatar, int sourceType) {
        long time = System.currentTimeMillis();
        String sign = VerifySignUtil.getSignature(SignatureUtil.loginInfoToString(identifier, time, name, email, gender, avatar, sourceType, getVersionCode()));
        return api.getLoginInfo(identifier, time, name, email, gender, avatar, sourceType, getVersionCode(), sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //請求登出
    public static Flowable<BaseInfo> getLogoutInfo() {
        String memberId = SharedPreferencesUtil.getInstance().getString("memberId", null);
        String token = SharedPreferencesUtil.getInstance().getString("token", null);
        long time = System.currentTimeMillis();
        String sign = VerifySignUtil.getSignature(SignatureUtil.logoutIfoToString(memberId, time, token, getVersionCode()));
        return api.getLogoutInfo(memberId, time, token, getVersionCode(), sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static String getVersionCode() {
        try {
            return String.valueOf(MyApplication.getContext().getPackageManager().getPackageInfo(MyApplication.getContext().getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
