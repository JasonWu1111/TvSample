package com.example.tvsample.utils;

import android.widget.Toast;

import com.example.tvsample.MyApplication;

/**
 * Created by JasonWu on 28/12/2017
 */

public class ToastUtil {

    public static void showToast(String s) {
        Toast.makeText(MyApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static void showToastForLong(String s) {
        Toast.makeText(MyApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
