package com.example.tvsample.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.tvsample.R;

/**
 * Created by JasonWu on 04/01/2018
 */

public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        this(context, R.style.TransparentStyle);
        View view = View.inflate(context, R.layout.view_alert_loading, null);
        setView(view);
    }

    private LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

