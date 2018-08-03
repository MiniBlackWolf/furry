package com.example.wolf;

import android.app.Application;

import com.zhangke.websocket.WebSocketSetting;

import org.xutils.BuildConfig;
import org.xutils.x;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        x.Ext.init(MyApp.this);
        x.Ext.setDebug(true); //输出debug日志，开启会影响性能
        super.onCreate();
    }
}
