package com.example.wolf.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.OrderUtils;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.land.orderbeans;
import com.example.wolf.land.userseedandland;
import com.example.wolf.seed.seedbean;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;

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

public class pickadapter extends BaseQuickAdapter<userseedandland, BaseViewHolder> {
    private Context context;
    private Xutils xutils;
    private Button pickbutton;
    private Set<userseedandland> userseedandlandsSet = new LinkedHashSet<>();
    private List<userseedandland> userseedandlandsList = new ArrayList<>();
    private Set<ImageView> hands = new LinkedHashSet<>();
    private List<ImageView> handsList = new ArrayList<>();
    private List<orderbeans.payitem> payitem = new ArrayList<>();
    private int count;
    private List<String> fid = new ArrayList<>();

    public pickadapter(int layoutResId, @Nullable List<userseedandland> data, Context context, Button pickbutton) {
        super(layoutResId, data);
        this.context = context;
        this.pickbutton = pickbutton;
        xutils = new Xutils(context);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseedandland item) {
        userseedandlandsSet.add(item);
        hands.add((ImageView) helper.getView(R.id.pickhand));
        Map<String, String> map = new HashMap<>();
        map.put("sid", item.getSid() + "");
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.pickname, SeedBean.get(0).getSeedname());
                if (helper.getView(R.id.pickday) != null) {
                    long l = System.currentTimeMillis() / 1000;
                    long time = (l - item.getSwoingdate()) / 60 / 60 / 24;
                    helper.setText(R.id.pickday, item.getGrowDate() - time + "");
                }
                Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.pickimage));
            }
        });

        pickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for (int i = 0; i < handsList.size(); i++) {
                    if (handsList.get(i).getVisibility() == View.VISIBLE) {
                        orderbeans.payitem addpayitem = OrderUtils.addpayitem(String.valueOf(userseedandlandsList.get(i).getSid()), "-1", 1, 2.0);
                        payitem.add(addpayitem);
                        fid.add(userseedandlandsList.get(i).getFid());
                        count++;
                    }

                }
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
                        if(UserInfo.get(0).getMoney()>=(count * 2.0)){
                            orderbeans orderbeans = OrderUtils.addorder("采摘种子", 6, count * 2.0, new Getuserinfo(context).getuid(), System.currentTimeMillis() / 1000, payitem);
                            Gson gson = new Gson();
                            String s = gson.toJson(orderbeans);
                            RequestParams requestParams = new RequestParams(context.getResources().getString(R.string.harvest));
                            requestParams.setBodyContent(s);
                            requestParams.setAsJsonContent(true);
                            requestParams.addParameter("fid",fid);
                            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    if (result.equals("success")) {
                                        ToastUtils.showToast(context, "采摘成功");
                                        Intent intent = new Intent(context, MainActivity.class);
                                        intent.putExtra("seed", 2);
                                        context.startActivity(intent);
                                    }
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    ToastUtils.showToast(context, "采摘失败，请检查网络!");
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        }
                        else {
                            ToastUtils.showToast(context,"金额不足！");
                        }
                    }
                });

            }
        });

    }
}
