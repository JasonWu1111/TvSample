package com.example.tvsample;

import android.app.Application;

import com.example.tvsample.greendao.DaoMaster;
import com.example.tvsample.greendao.DaoSession;
import com.example.tvsample.network.RetrofitService;
import com.example.tvsample.utils.ToastUtil;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by JasonWu on 2017/12/28
 */

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(getApplicationContext());
        LeakCanary.install(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        initDatabase();

//        RetrofitService.init();
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
