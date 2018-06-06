package com.example.wolf.UrlDown;


import android.os.Handler;
import android.os.Message;
import android.util.Log;




import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import org.xutils.x;


public class urldown implements Callback.CacheCallback<String> {
    private String url;
    ; private String result;
    Handler handler;


    public urldown(String url) {
        this.url = url;


    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void gethttp() {

        String result;
        RequestParams params = new RequestParams(url);

        x.http().get(params, this);


    }



    @Override
    public boolean onCache(String result) {
        return false;
    }

    @Override
    public void onSuccess(String result) {
        if (result != null) {
            this.result = result;

        }
        Log.i("iiiiiiiiiiiiii", "onSuccess");
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i("iiiiiiiiiiiiii", "onError");

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
        Message ms=new Message();
        ms.obj=result;
        ms.what=1;
        handler.sendMessage(ms);
       Log.i("iiiiiiiiiiiiii", "onFinished");


    }
}
