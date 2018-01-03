package com.example.tvsample.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


/**
 * Created by JasonWu on 2017/10/31
 */

public class ShareUtil {

    public static void shareLink(Context context, String newsTitle, String sourceUrl, String platform) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, newsTitle);
        shareIntent.putExtra(Intent.EXTRA_TEXT, newsTitle + "\n" + sourceUrl);
        switch (platform) {
            case "facebook":
                shareIntent.setPackage("com.facebook.katana");
                break;
            case "line":
                ComponentName cn = new ComponentName("jp.naver.line.android"
                        , "jp.naver.line.android.activity.selectchat.SelectChatActivity");
                shareIntent.setComponent(cn);
                break;
            case "message":
                shareIntent.setPackage("com.facebook.orca");
                break;
        }
        context.startActivity(Intent.createChooser(shareIntent, ""));
    }


    public static void rate(Context context) {
        String mAddress = "market://details?id=" + context.getPackageName();
        Intent marketIntent = new Intent("android.intent.action.VIEW");
        marketIntent.setData(Uri.parse(mAddress));
        marketIntent.setPackage("com.android.vending");
        try {
            context.startActivity(marketIntent);
        } catch (Exception e) {
            try {
                Intent marketIntent2 = new Intent("android.intent.action.VIEW");
                marketIntent2.setData(Uri.parse(mAddress));
                context.startActivity(marketIntent2);
            } catch (Exception e1) {
//                ToastUtil.showToast(context.getString(R.string.no_market));
            }
        }
    }
}
