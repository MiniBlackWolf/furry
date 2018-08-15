package com.example.wolf.adpater;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.cultivation.genyun;
import com.example.wolf.land.Farminfo;
import com.example.wolf.land.Userfarm;
import com.example.wolf.land.orderbeans;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class xuandiadapter extends BaseQuickAdapter<Farminfo, BaseViewHolder> {
    private Context context;
    Xutils xutils;
    List<Userfarm> userfarmsA = new ArrayList<>();
    List<Userfarm> userfarmsB = new ArrayList<>();
    List<Userfarm> userfarmsC = new ArrayList<>();

    public xuandiadapter(int layoutResId, @Nullable List<Farminfo> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.xutils = new Xutils(context);

    }


    @Override
    protected void convert(final BaseViewHolder helper, final Farminfo item) {
        helper.addOnClickListener(R.id.landnext);
        xutils.get(context.getResources().getString(R.string.getAllfarm), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<Userfarm> Userfarms = gsonUtil.Gson(result, Userfarm.class);
                for (Userfarm userfarm : Userfarms) {
                    switch (userfarm.getFid().substring(0,1)) {
                        case "A":
                            userfarmsA.add(userfarm);
                            break;
                        case "B":
                            userfarmsB.add(userfarm);
                            break;
                        case "C":
                            userfarmsC.add(userfarm);
                            break;
                    }
                }
                switch (item.getType()) {
                    case "a":
                        helper.setImageResource(R.id.imageland, R.mipmap.a1);
                        helper.setText(R.id.landname, "菜鸟农场|A区土地");
                        helper.setText(R.id.landcount, userfarmsA.size()+"");
                        break;
                    case "b":
                        helper.setImageResource(R.id.imageland, R.mipmap.a2);
                        helper.setText(R.id.landname, "菜鸟农场|B区土地");
                        helper.setText(R.id.landcount, userfarmsB.size()+"");
                        break;
                    case "c":
                        helper.setImageResource(R.id.imageland, R.mipmap.a3);
                        helper.setText(R.id.landname, "菜鸟农场|C区土地");
                        helper.setText(R.id.landcount, userfarmsC.size()+"");
                        break;
                }

            }
        });
    }

    private void buyfarm() {
//        orderbeans xuandibean = new orderbeans();
//        xuandibean.setGoodsname("购买土地");
//        xuandibean.setGoodstype(3);
//        xuandibean.setPrice(ALLprice);
//        xuandibean.setUserid(new Getuserinfo(context).getuid());
//        xuandibean.setBuytime(System.currentTimeMillis() / 1000);
//        xuandibean.setPayitem(payitems);
//        Gson gson = new Gson();
//        final String s = gson.toJson(xuandibean);
//        Log.i("iiiiiiiii", s);
//        RequestParams requestParams = new RequestParams(context.getResources().getString(R.string.buyfarm));
//        requestParams.setBodyContent(s);
//        requestParams.setAsJsonContent(true);
//        x.http().post(requestParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                if (result.equals("success")) {
//                    Toast.makeText(context, "购买成功,详细的交易信息可在个人中心-我的农场-交易记录查看", Toast.LENGTH_LONG).show();
//                    show.dismiss();
//                    Intent intent = new Intent(context, MainActivity.class);
//                    context.startActivity(intent);
//                } else {
//                    Toast.makeText(context, "购买失败", Toast.LENGTH_LONG).show();
//                    show.dismiss();
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }


}
