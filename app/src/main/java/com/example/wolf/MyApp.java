package com.example.wolf;

import android.app.Application;



import org.xutils.x;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        x.Ext.init(MyApp.this);
        x.Ext.setDebug(true); //输出debug日志，开启会影响性能
        super.onCreate();
    }
}
