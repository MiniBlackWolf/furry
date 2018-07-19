package com.example.wolf.adpater;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.land.userseedandland;
import com.example.wolf.seed.seedbean;
import com.example.wolf.userbean.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MylandShoveladapter extends BaseQuickAdapter<userseedandland, BaseViewHolder> {
    private Context context;
    private Button Shovelbutton;
    private Xutils xutils;
    private Dialog dialog;
    private Set<ImageView> chanzhi = new HashSet<>();
    private Set<userseedandland> max = new HashSet<>();
    private List<ImageView> chanzhi2 = new ArrayList<>();
    private List<userseedandland> max2 = new ArrayList<>();

    public MylandShoveladapter(int layoutResId, @Nullable List<userseedandland> data, Context context, Button Shovelbutton,Dialog dialog) {
        super(layoutResId, data);
        this.context = context;
        this.Shovelbutton = Shovelbutton;
        this.xutils = new Xutils(context);
        this.dialog=dialog;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseedandland item) {
        chanzhi.add((ImageView) helper.getView(R.id.chanzi));
        max.add(item);
        Map<String, String> map = new HashMap<>();
        map.put("sid", String.valueOf(item.getSid()));
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.mylanddialogitemname, SeedBean.get(0).getSeedname() + "");
                Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.mylanddialogitemimage));
            }
        });
        Shovelbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int count = 0;
                for (ImageView imageView : chanzhi) {
                    if (imageView.getVisibility() == View.VISIBLE) {
                        count++;

                    }
                    chanzhi2.add(imageView);
                }
                for (userseedandland userseedandland : max) {
                    max2.add(userseedandland);
                }
                for (int i = 0; i < max2.size(); i++) {
                    if (chanzhi2.get(i).getVisibility() == View.VISIBLE) {
                        Map<String, String> map2 = new HashMap<>();
                        map2.put("userName", new Getuserinfo(context).getusername());
                        final int finalCount = count;
                        final int finalI = i;
                        xutils.get(context.getResources().getString(R.string.Userinfo), map2, new Xutils.XCallBack() {
                            @SuppressLint("SetTextI18n")
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
                                float money = UserInfo.get(0).getMoney();
                                if ((finalCount * 2) > UserInfo.get(0).getMoney()) {
                                    ToastUtils.showToast(context, "金额不足请充值");

                                } else {
                                    Map<String, String> map = new HashMap<>();
                                    map.put("price",finalCount*2+"");
                                    map.put("swoingdate", String.valueOf(max2.get(finalI).getSwoingdate()));
                                    map.put("sid", String.valueOf(max2.get(finalI).getSid()));
                                    map.put("sfid", max2.get(finalI).getSfid());
                                    map.put("fid", max2.get(finalI).getFid());
                                    map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
                                    xutils.get(context.getResources().getString(R.string.shovel), map, new Xutils.XCallBack() {
                                        @Override
                                        public void onResponse(String result) {
                                            if (result.equals("success")) {
                                                Map<String, String> map = new HashMap<>();
                                                map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
                                                map.put("money", "-2");
                                                map.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
                                                xutils.get(context.getResources().getString(R.string.clientMoney), map, new Xutils.XCallBack() {
                                                    @Override
                                                    public void onResponse(String result) {
                                                        String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                                                        if (i.equals("success")) {
                                                            ToastUtils.showToast(context, "铲除成功!");
                                                            dialog.dismiss();
                                                        }
                                                    }
                                                });
                                            } else {
                                                ToastUtils.showToast(context, "铲除失败!");

                                            }
                                        }
                                    });


                                }

                            }
                        });

                    }

                }

            }
        });

    }

}
