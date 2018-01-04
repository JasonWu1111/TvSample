package com.example.tvsample.utils;


import android.content.Context;

import com.example.tvsample.entity.UserEntity;


/**
 * Created by JasonWu on 2017/9/19
 */

public class AccountHelper {


    public static boolean isLogin() {
        return SharedPreferencesUtil.getInstance().getBoolean("isLogin", false);
    }

    public static void onSaveUserInfo(UserEntity userEntity) {
        SharedPreferencesUtil.getInstance().putBoolean("isLogin", true);
        SharedPreferencesUtil.getInstance().putString("memberId", userEntity.getMemberId());
        SharedPreferencesUtil.getInstance().putString("nickname", userEntity.getNickname());
        SharedPreferencesUtil.getInstance().putString("avatar", userEntity.getAvatar());
        SharedPreferencesUtil.getInstance().putString("birth", userEntity.getBirth());
        SharedPreferencesUtil.getInstance().putString("email", userEntity.getEmail());
        SharedPreferencesUtil.getInstance().putInt("sex", userEntity.getSex());
        SharedPreferencesUtil.getInstance().putInt("status", userEntity.getStatus());
        SharedPreferencesUtil.getInstance().putString("token", userEntity.getToken());

    }

    public static void Logout(Context context) {
        int is_rate = SharedPreferencesUtil.getInstance().getInt("is_rate", 0);
        int rate = SharedPreferencesUtil.getInstance().getInt("rate", 0);
        boolean isSubscribe = SharedPreferencesUtil.getInstance().getBoolean("isSubscribe", true);
        SharedPreferencesUtil.getInstance().clearUserData();
        SharedPreferencesUtil.getInstance().putInt("is_rate", is_rate);
        SharedPreferencesUtil.getInstance().putInt("rate", rate);
        SharedPreferencesUtil.getInstance().putBoolean("isSubscribe", isSubscribe);
//        EventBus.getDefault().post(new UserStateChangeEvent("logout"));
    }

}
