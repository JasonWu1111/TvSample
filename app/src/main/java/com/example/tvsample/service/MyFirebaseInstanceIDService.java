package com.fastheadline.news.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.fastheadline.news.utils.FcmUtil;
import com.fastheadline.news.utils.SharedPreferncesUtil;

/**
 * Created by JasonWu on 2017/9/21
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        sendRegistrationToServer();
    }

    private void sendRegistrationToServer() {
        if (SharedPreferncesUtil.getInstance().getBoolean("isSubscribe", true)) {
            FcmUtil.connectToFcm();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "-----------------------" + TAG + "onCreate");
    }
}
