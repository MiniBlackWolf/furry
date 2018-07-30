package com.example.wolf.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
    private Button buy;
    private Xutils xutils;
    private Set<TextView> sl1 = new LinkedHashSet<>();
    private Set<TextView> sl2 = new LinkedHashSet<>();
    private Set<TextView> sl3 = new LinkedHashSet<>();
    private List<TextView> sls1;
    private List<TextView> sls2;
    private List<TextView> sls3;
    private List<Userfarm> u1 = new ArrayList<>();
    private List<Userfarm> u2 = new ArrayList<>();
    private List<Userfarm> u3 = new ArrayList<>();
    private List<Farminfo> data;
    private List<Userfarm> g10m = new ArrayList<>();
    private List<Userfarm> g15m = new ArrayList<>();
    private List<Userfarm> g20m = new ArrayList<>();
    private List<Userfarm> g10m2 = new ArrayList<>();
    private List<Userfarm> g15m2 = new ArrayList<>();
    private List<Userfarm> g20m2 = new ArrayList<>();
    private List<Userfarm> g10m3 = new ArrayList<>();
    private List<Userfarm> g15m3 = new ArrayList<>();
    private List<Userfarm> g20m3 = new ArrayList<>();
    ZloadingDiaLogkt zloadingDiaLogkt;
    ZLoadingDialog show;
    double ALLprice;
    double a1;
    double a2;
    double a3;
    List<orderbeans> orderbeans = new ArrayList<>();
    List<orderbeans.payitem> payitems = new ArrayList<>();

    public xuandiadapter(int layoutResId, @Nullable List<Farminfo> data, Context context, Button buy) {
        super(layoutResId, data);
        this.data = data;
        this.context = context;
        this.buy = buy;
        xutils = new Xutils(context);
        zloadingDiaLogkt = new ZloadingDiaLogkt(context);
    }


    @Override
    protected void convert(BaseViewHolder helper, Farminfo item) {
        helper.addOnClickListener(R.id.jia);
        helper.addOnClickListener(R.id.jia2);
        helper.addOnClickListener(R.id.jia3);
        helper.addOnClickListener(R.id.jian);
        helper.addOnClickListener(R.id.jian2);
        helper.addOnClickListener(R.id.jian3);
        sl1.add((TextView) helper.getView(R.id.shuliang));
        sl2.add((TextView) helper.getView(R.id.shuliang2));
        sl3.add((TextView) helper.getView(R.id.shuliang3));
        switch (item.getType()) {
            case "a":
                helper.setText(R.id.jiage_1, "¥" + data.get(0).getPrice());
                helper.setText(R.id.jiage_2, "¥" + data.get(1).getPrice());
                helper.setText(R.id.jiage_3, "¥" + data.get(2).getPrice());
                helper.setImageResource(R.id.imageland, R.mipmap.a1);
                break;
            case "b":
                helper.setText(R.id.jiage_1, "¥" + data.get(0).getPrice());
                helper.setText(R.id.jiage_2, "¥" + data.get(1).getPrice());
                helper.setText(R.id.jiage_3, "¥" + data.get(2).getPrice());
                helper.setImageResource(R.id.imageland, R.mipmap.a2);
                break;
            case "c":
                helper.setText(R.id.jiage_1, "¥" + data.get(0).getPrice());
                helper.setText(R.id.jiage_2, "¥" + data.get(1).getPrice());
                helper.setText(R.id.jiage_3, "¥" + data.get(2).getPrice());
                helper.setImageResource(R.id.imageland, R.mipmap.a3);
                break;
        }

        buy.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View view) {
                Integer uid = new Getuserinfo(context).getuid();
                if (uid == 0) {
                    ToastUtils.showToast(context, "请先登录");
                    return;
                }
                show = zloadingDiaLogkt.show();
                sls1 = new ArrayList<>(sl1);
                sls2 = new ArrayList<>(sl2);
                sls3 = new ArrayList<>(sl3);
                xutils.get(context.getResources().getString(R.string.getAllfarm), new HashMap<String, String>(), new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        GsonUtil gsonUtil = new GsonUtil();
                        List<Userfarm> Userfarms = gsonUtil.Gson(result, Userfarm.class);
                        for (Userfarm Userfarm : Userfarms) {
                            String Fidtop = Userfarm.getFid().substring(0, 1);
                            switch (Fidtop) {
                                case "A":
                                    u1.add(Userfarm);
                                    break;
                                case "B":
                                    u2.add(Userfarm);
                                    break;
                                case "C":
                                    u3.add(Userfarm);
                                    break;
                            }


                        }
                        getlangenre(u1);
                        getlangenre2(u2);
                        getlangenre3(u3);
                        for (int i = 0; i < sls1.size(); i++) {
                            String s1 = sls1.get(i).getText().toString();
                            String s2 = sls2.get(i).getText().toString();
                            String s3 = sls3.get(i).getText().toString();
                            switch (i) {
                                case 0:
                                    a1 = (Integer.valueOf(s1) * 120) + (Integer.valueOf(s2) * 180) + (Integer.valueOf(s3) * 240);
                                    break;
                                case 1:
                                    a2 = (Integer.valueOf(s1) * 120) + (Integer.valueOf(s2) * 180) + (Integer.valueOf(s3) * 240);
                                    break;
                                case 2:
                                    a3 = (Integer.valueOf(s1) * 120) + (Integer.valueOf(s2) * 180) + (Integer.valueOf(s3) * 240);
                                    break;
                            }
                        }
                        ALLprice = a1 + a2 + a3;
                        for (int i = 0; i < sls1.size(); i++) {
                            String s1 = sls1.get(i).getText().toString();
                            String s2 = sls2.get(i).getText().toString();
                            String s3 = sls3.get(i).getText().toString();
                            if (i == 0) {

                                if (buyland(g10m, g15m, g20m, i, s1, s2, s3)) return;
                            }
                            if (i == 1) {

                                if (buyland(g10m2, g15m2, g20m2, i, s1, s2, s3)) return;
                            }
                            if (i == 2) {

                                if (buyland(g10m3, g15m3, g20m3, i, s1, s2, s3)) return;
                            }


                        }
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
                                if(UserInfo.get(0).getMoney()>=ALLprice){
                                    buyfarm();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("seed", 2);
                                    context.startActivity(intent);
                                }
                                else {

                                    ToastUtils.showToast(context,"金额不足!");
                                }
                            }
                        });


                    }
                });
            }

            private boolean buyland(List<Userfarm> g1, List<Userfarm> g2, List<Userfarm> g3, int i, String s1, String s2, String s3) {
                if (g1.size() < Integer.valueOf(s1)) {
                    switch (i) {
                        case 0:
                            ToastUtils.showToast(context, "A地10㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 1:
                            ToastUtils.showToast(context, "B地10㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 2:
                            ToastUtils.showToast(context, "C地10㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;

                    }
                    return true;
                }
                if (g2.size() < Integer.valueOf(s2)) {
                    switch (i) {
                        case 0:
                            ToastUtils.showToast(context, "A地15㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 1:
                            ToastUtils.showToast(context, "B地15㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 2:
                            ToastUtils.showToast(context, "C地15㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;

                    }
                    ;
                    return true;
                }
                if (g3.size() < Integer.valueOf(s3)) {
                    switch (i) {
                        case 0:
                            ToastUtils.showToast(context, "A地20㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 1:
                            ToastUtils.showToast(context, "B地20㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;
                        case 2:
                            ToastUtils.showToast(context, "C地20㎡土地数量不足！，购买失败！");
                            show.dismiss();
                            break;

                    }
                    return true;
                }
                if (!s1.equals("0")) {

                    for (int a = 0; a < Integer.valueOf(s1); a++) {
                        //     buyfarm(g1.get(a).getFid(), 120.00);
                        orderbeans.payitem payitem = new orderbeans.payitem();
                        payitem.setManyid(g1.get(a).getFid());
                        payitem.setItemtype("A");
                        payitem.setCounts(Integer.valueOf(s1));
                        payitem.setUnitprice(120.0);
                        payitems.add(payitem);

                    }

                }
                if (!s2.equals("0")) {

                    for (int a = 0; a < Integer.valueOf(s2); a++) {
                        //   buyfarm(g2.get(a).getFid(), 180.00);
                        orderbeans.payitem payitem = new orderbeans.payitem();
                        payitem.setManyid(g2.get(a).getFid());
                        payitem.setItemtype("B");
                        payitem.setCounts(Integer.valueOf(s2));
                        payitem.setUnitprice(180.0);
                        payitems.add(payitem);

                    }

                }
                if (!s3.equals("0")) {

                    for (int a = 0; a < Integer.valueOf(s3); a++) {
                        //       buyfarm(g3.get(a).getFid(), 240.00);
                        orderbeans.payitem payitem = new orderbeans.payitem();
                        payitem.setManyid(g3.get(a).getFid());
                        payitem.setItemtype("C");
                        payitem.setCounts(Integer.valueOf(s3));
                        payitem.setUnitprice(240.0);
                        payitems.add(payitem);
                    }

                }
                return false;
            }

            private void getlangenre(List<Userfarm> u) {
                for (Userfarm g : u) {
                    switch (g.getGenre()) {
                        case 1:
                            g10m.add(g);
                            break;
                        case 2:
                            g15m.add(g);
                            break;
                        case 3:
                            g20m.add(g);
                            break;
                    }
                }
            }

            private void getlangenre2(List<Userfarm> u) {
                for (Userfarm g : u) {
                    switch (g.getGenre()) {
                        case 1:
                            g10m2.add(g);
                            break;
                        case 2:
                            g15m2.add(g);
                            break;
                        case 3:
                            g20m2.add(g);
                            break;
                    }
                }
            }

            private void getlangenre3(List<Userfarm> u) {
                for (Userfarm g : u) {
                    switch (g.getGenre()) {
                        case 1:
                            g10m3.add(g);
                            break;
                        case 2:
                            g15m3.add(g);
                            break;
                        case 3:
                            g20m3.add(g);
                            break;
                    }
                }
            }

            private void buyfarm() {
                orderbeans xuandibean = new orderbeans();
                xuandibean.setGoodsname("购买土地");
                xuandibean.setGoodstype(3);
                xuandibean.setPrice(ALLprice);
                xuandibean.setUserid(new Getuserinfo(context).getuid());
                xuandibean.setBuytime(System.currentTimeMillis() / 1000);
                xuandibean.setPayitem(payitems);
                Gson gson = new Gson();
                String s = gson.toJson(xuandibean);
                Log.i("iiiiiiiii", s);
                RequestParams requestParams=new RequestParams(context.getResources().getString(R.string.buyfarm));
                requestParams.setBodyContent(s);
                requestParams.setAsJsonContent(true);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        if(result.equals("success")){
                            ToastUtils.showToast(context,"购买成功!");
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
//                final Map<String, String> map = new HashMap<>();
//                map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
//                map.put("fid", fid);
//                map.put("price", String.valueOf(price));
//                //    map.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
//                xutils.post(context.getResources().getString(R.string.buyfarm), map, new Xutils.XCallBack() {
//                    @Override
//                    public void onResponse(String result) {
//                        Pattern compile = Pattern.compile("success");
//                        Matcher matcher = compile.matcher(result);
//                        if (matcher.find()) {
//                            Map<String, String> map2 = new HashMap<>();
//                            map2.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
//                            map2.put("money", String.valueOf(-price));
//                            map2.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
//                            xutils.get(context.getResources().getString(R.string.clientMoney), map2, new Xutils.XCallBack() {
//                                @Override
//                                public void onResponse(String result) {
//                                    ToastUtils.showToast(context, "购买成功！");
//                                    show.dismiss();
//                                }
//                            });
//                        }
//
//                    }
//                });
            }
        });
    }


}
