package com.fastheadline.news.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.fastheadline.news.R;
import com.fastheadline.news.module.home.MainActivity;

/**
 * Created by JasonWu on 2017/9/21
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private static int NotificationId = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
        }

    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String mType = remoteMessage.getData().get("type");
        String mNewsId = remoteMessage.getData().get("newsId");
        String mNewsCat = remoteMessage.getData().get("newsCat");
        String mNewsTitle = remoteMessage.getData().get("newsTitle");
        String mNewsSubTitle = remoteMessage.getData().get("newsSubTitle");
//        Log.e(TAG, "Message data payload: " + remoteMessage.getData());

        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();

        intent.setClass(this, MainActivity.class);
        intent.putExtra("type", mType);
        intent.putExtra("newsId", mNewsId);
        intent.putExtra("newsCat", mNewsCat);
        intent.putExtra("isPush", 1);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent
                , PendingIntent.FLAG_CANCEL_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mNewsTitle)
                .setContentText(mNewsSubTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.red))
                .setSound(defaultSoundUri)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        if (notifyManager != null) {
            notifyManager.notify(NotificationId, builder.build());
            NotificationId++;
        }
    }

//    private boolean isMainActivityTop() {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
//        return name.equals(MainActivity.class.getName());
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "-----------------------" + TAG + "-------onCreate");
    }
}
