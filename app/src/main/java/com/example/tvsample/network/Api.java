package com.example.tvsample.network;

import com.example.tvsample.entity.BaseInfo;
import com.example.tvsample.entity.BaseListInfo;
import com.example.tvsample.entity.UserEntity;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by JasonWu on 29/12/2017
 */

public interface Api {
    //登录
    @POST("login")
    Flowable<BaseInfo<UserEntity>> getLoginInfo(@Query("identifier") String identifier, @Query("ts") long ts, @Query("name") String name
            , @Query("email") String email, @Query("gender") String gender, @Query("avatar") String avatar
            , @Query("sourceType") int sourceType, @Query("version") String version, @Query("sign") String sign);

    //登出
    @POST("logout")
    Flowable<BaseInfo> getLogoutInfo(@Query("memberId") String memberId, @Query("ts") long ts, @Query("token") String token
            , @Query("version") String version, @Query("sign") String sign);
}
