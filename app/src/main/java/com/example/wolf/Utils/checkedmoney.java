package com.example.wolf.Utils;

import android.content.Context;

import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class checkedmoney  {


    public static boolean checke(Context context, final double money){

        Xutils xutils=new Xutils(context);
        Map<String, String> map2 = new HashMap<>();
        map2.put("userName", new Getuserinfo(context).getusername());
        xutils.get(context.getResources().getString(R.string.Userinfo), map2, new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                String userinfo = null;
                try {
                    userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                GsonUtil gsonUtil = new GsonUtil();
                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
                if(UserInfo.get(0).getMoney()>=money){

                }
            }
        });


        return true;

    }


}
