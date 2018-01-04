package com.example.tvsample.utils;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.UUID;

/**
 * Created by JasonWu on 2017/11/8
 */

public class FcmUtil {
    public static void connectToFcm() {
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("752998004384" + "@gcm.googleapis.com")
                .setMessageId("m-" + UUID.randomUUID().toString())
                .addData("memberId", SharedPreferencesUtil.getInstance().getString("memberId", ""))
                .addData("type", "subscribe")
                .addData("isSimple", "1")
                .build());
    }

    public static void unConnectToFcm() {
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("752998004384" + "@gcm.googleapis.com")
                .setMessageId("m-" + UUID.randomUUID().toString())
                .addData("memberId", SharedPreferencesUtil.getInstance().getString("memberId", ""))
                .addData("type", "unSubscribe")
                .addData("isSimple", "1")
                .build());
    }
}
