package com.example.wolf;

import android.app.Application;
import android.util.Log;


import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import org.xutils.x;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        x.Ext.init(MyApp.this);
        x.Ext.setDebug(true); //输出debug日志，开启会影响性能
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.i("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.i("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        super.onCreate();
    }
}
