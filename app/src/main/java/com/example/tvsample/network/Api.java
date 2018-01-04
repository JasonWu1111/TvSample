package com.example.tvsample.network;

import com.example.tvsample.entity.BaseInfo;
import com.example.tvsample.entity.BaseListInfo;
import com.example.tvsample.entity.UserEntity;
import com.example.tvsample.entity.VideoDetailEntity;
import com.example.tvsample.entity.VideoListEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by JasonWu on 29/12/2017
 */

public interface Api {

    //列表查询
    @GET("tv/find")
    Flowable<BaseListInfo<VideoListEntity>> getVideoListInfo(@Query("memberId") String memberId, @Query("ts") long ts, @Query("page") int page
            , @Query("size") int size, @Query("itemTypes") List<String> itemTypes, @Query("tags") List<String> tags
            , @Query("version") String version, @Query("sign") String sign);

    //详情页查询
    @GET("tv/detail")
    Flowable<BaseInfo<VideoDetailEntity>> getVideoDetailInfo(@Query("memberId") String memberId, @Query("ts") long ts, @Query("videoId") String videoId
            , @Query("deviceId") String deviceId, @Query("version") String version, @Query("sign") String sign);

    //登录
    @POST("login")
    Flowable<BaseInfo<UserEntity>> getLoginInfo(@Query("identifier") String identifier, @Query("ts") long ts, @Query("name") String name
            , @Query("email") String email, @Query("gender") String gender, @Query("avatar") String avatar
            , @Query("sourceType") int sourceType, @Query("version") String version, @Query("sign") String sign);

    //登出
    @POST("logout")
    Flowable<BaseInfo> getLogoutInfo(@Query("memberId") String memberId, @Query("token") String token, @Query("ts") long ts
            , @Query("version") String version, @Query("sign") String sign);

    //收藏文章
    @POST("me/favorite/add")
    Flowable<BaseInfo> getFavoriteInfo(@Query("memberId") String memberId, @Query("token") String token, @Query("ts") long ts
            , @Query("videoId") String videoId, @Query("version") String version, @Query("sign") String sign);

    //取消收藏文章
    @POST("me/favorite/cancel")
    Flowable<BaseInfo> getCancelFavoriteInfo(@Query("memberId") String memberId, @Query("token") String token, @Query("ts") long ts
            , @Query("videoId") String videoId, @Query("version") String version, @Query("sign") String sign);


    //todo 修改返回结果字段类型，去掉dataList
    //查询收藏文章
    @POST("me/favorite/add")
    Flowable<BaseInfo> getFavoriteListInfo(@Query("memberId") String memberId, @Query("token") String token, @Query("ts") long ts
            , @Query("page") int page, @Query("size") int size, @Query("version") String version, @Query("sign") String sign);
}
