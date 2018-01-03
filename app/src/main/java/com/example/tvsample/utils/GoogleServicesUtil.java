package com.example.tvsample.utils;

import android.content.Context;

import com.example.tvsample.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by JasonWu on 2017/11/16
 */

public class GoogleServicesUtil {
    public static boolean isGooglePlayServiceAvailable(Context context) {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        return status == ConnectionResult.SUCCESS;
    }

    public static boolean isGooglePlayServiceAvailableForToast(Context context) {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (status == ConnectionResult.SUCCESS) {
            return true;
        } else {
            ToastUtil.showToast(context.getString(R.string.no_gs));
            return false;
        }
    }
}
