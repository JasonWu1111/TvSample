package com.example.tvsample.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by JasonWu on 28/12/2017
 */

public class ToastUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static void showToast(String s) {
      Toast.makeText(sContext, s, Toast.LENGTH_SHORT).show();
    }

    public static void showToastForLong(String s) {
        Toast.makeText(sContext, s, Toast.LENGTH_SHORT).show();
    }
}
