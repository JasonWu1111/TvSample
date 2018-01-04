package com.example.tvsample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.tvsample.greendao.DaoMaster;
import com.example.tvsample.greendao.DaoSession;
import com.example.tvsample.network.RetrofitService;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by JasonWu on 2017/12/28
 */

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        LeakCanary.install(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        initDatabase();
        RetrofitService.init();
    }

    public static Context getContext(){
        return sContext;
    }

    private void initDatabase(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "tv-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
