package com.example.wolf.adpater;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.OrderUtils;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.land.orderbeans;
import com.example.wolf.land.userseedandland;
import com.example.wolf.seed.Seedfarm;
import com.example.wolf.seed.seedbean;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
    List<String> fids = new ArrayList<>();
    List<String> sfids = new ArrayList<>();
    List<orderbeans.payitem> payitem=new ArrayList<>();
    List<Long> swoingdates=new ArrayList<>();
    Handler handler=new Handler();
    public MylandShoveladapter(int layoutResId, @Nullable List<userseedandland> data, Context context, Button Shovelbutton, Dialog dialog) {
        super(layoutResId, data);
        this.context = context;
        this.Shovelbutton = Shovelbutton;
        this.xutils = new Xutils(context);
        this.dialog = dialog;
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
                ZloadingDiaLogkt zloadingDiaLogkt=new ZloadingDiaLogkt(context);
                final ZLoadingDialog ssssss = zloadingDiaLogkt.show();
                int count = 0;
                for (ImageView imageView : chanzhi) {
                    if (imageView.getVisibility() == View.VISIBLE) {
                        count++;

                    }
                    chanzhi2.add(imageView);
                }
                max2.addAll(max);
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
                                    orderbeans.payitem addpayitem = OrderUtils.addpayitem(String.valueOf(max2.get(finalI).getSid()), "-1", 1, 10.0);
                                    payitem.add(addpayitem);
                                    fids.add(max2.get(finalI).getFid());
                                    sfids.add(max2.get(finalI).getSfid());
                                    swoingdates.add(max2.get(finalI).getSwoingdate());
                                }

                            }
                        });

                    }

                }

                final int finalCount1 = count;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(swoingdates.isEmpty()){
                            handler.postDelayed(this, 1000);
                        }
                        orderbeans orderbeans = OrderUtils.addorder("锄种子", 4, finalCount1 * 2, new Getuserinfo(context).getuid(), System.currentTimeMillis() / 1000, payitem);
                        Gson gson=new Gson();
                        String s = gson.toJson(orderbeans);
                        RequestParams requestParams=new RequestParams(context.getResources().getString(R.string.shovel));
                        requestParams.setBodyContent(s);
                        requestParams.setAsJsonContent(true);
                        requestParams.addParameter("fid",fids);
                        requestParams.addParameter("sfid",sfids);
                        requestParams.addParameter("swoingdate",swoingdates);
                        x.http().post(requestParams, new Callback.CommonCallback<String>() {

                            @Override
                            public void onSuccess(String result) {
                                if(result.equals("success")){
                                    ToastUtils.showToast(context,"铲除成功！");
                                    ssssss.dismiss();
                                    dialog.dismiss();
                                }
                                else {
                                    ToastUtils.showToast(context,"铲除失败,请检查网络或数量！");
                                    ssssss.dismiss();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });

                    }
                },1000);


            }
        });

    }

}
