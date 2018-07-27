package com.example.wolf.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Getuserinfo {
    private Context context;

    public Getuserinfo(Context context) {
        this.context = context;
    }

    public Integer getuid() {
        SharedPreferences mySharePerferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        //用getString获取值
        int uid = mySharePerferences.getInt("uid", 0);
        return uid;
    }
    public String getusername() {
        SharedPreferences mySharePerferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        //用getString获取值
        String name = mySharePerferences.getString("userName", "");
        return name;
    }
    public String getpassword() {
        SharedPreferences mySharePerferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        //用getString获取值
        String password = mySharePerferences.getString("password", "");
        return password;
    }

}
