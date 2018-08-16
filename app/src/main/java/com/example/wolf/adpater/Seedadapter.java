package com.example.wolf.adpater;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.land.orderbeans;
import com.example.wolf.seed.ProdctBean;
import com.example.wolf.seed.seedActivity;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Seedadapter extends BaseQuickAdapter<ProdctBean, BaseViewHolder> {
    private Context context;
    private BigDecimal moeny = new BigDecimal(0);
    private TextView allmoeny;
    private Button buy;
    private Set<TextView> count = new LinkedHashSet<>();
    private List<String> listsusse = new ArrayList<>();
    private Set<Integer> sid = new LinkedHashSet<>();
    private ZLoadingDialog zloadingDiaLog;
    private Set<String> buymoney = new LinkedHashSet<>();
    private List<String> buymoneys=new ArrayList<>();
    private List<TextView> counts;
    private List<Integer> sids;
    Xutils xutils;
    int l;
    double allmoney;
    private List<orderbeans.payitem> payitem = new ArrayList<>();

    public Seedadapter(int layoutResId, @Nullable List<ProdctBean> data, Context context, TextView allmoeny, Button buy, ZLoadingDialog zloadingDiaLog) {
        super(layoutResId, data);
        this.context = context;
        this.allmoeny = allmoeny;
        this.buy = buy;
        xutils = new Xutils(context);
        this.zloadingDiaLog = zloadingDiaLog;
        if (data.size() == 0) {
            zloadingDiaLog.dismiss();

        }

    }

    @Override
    protected void convert(BaseViewHolder helper, ProdctBean item) {
        Glide.with(context).load(item.getImage()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.k1));
        helper.setText(R.id.k2, item.getName());
        helper.setText(R.id.k3, "￥" + item.getJiage());
        helper.setText(R.id.k5, "剩余数量" + item.getCount());
        count.add((TextView) helper.getView(R.id.k4));
        sid.add(item.getSid());
        buymoneys.add(item.getJiage());
        jiaorjian(helper, item);
        helper.addOnClickListener(R.id.k1);
        zloadingDiaLog.dismiss();

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer uid = new Getuserinfo(context).getuid();
                if (uid == 0) {
                    ToastUtils.showToast(context, "请先登录");
                    return;
                }
                sids = new ArrayList<>(sid);
                counts = new ArrayList<>(count);
         //       buymoneys = new ArrayList<>(buymoney);
                //用getint获取值
                for (int i = 0; i < counts.size(); i++) {
                    if (!counts.get(i).getText().toString().equals("0")) {
                        orderbeans.payitem addpayitem = OrderUtils.addpayitem(String.valueOf(sids.get(i)), "-1", Integer.valueOf(counts.get(i).getText().toString()), Double.valueOf(buymoneys.get(i)));
                        payitem.add(addpayitem);
                        allmoney += Double.valueOf(buymoneys.get(i));
                    }

                }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("购买提醒");
                alertDialog.setMessage("您确定要购买吗？");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                                if (UserInfo.get(0).getMoney() >= allmoney) {
                                    orderbeans orderbeans = OrderUtils.addorder("购买种子", 2, allmoney, new Getuserinfo(context).getuid(), System.currentTimeMillis() / 1000, payitem);
                                    Gson gson = new Gson();
                                    String s = gson.toJson(orderbeans);
                                    RequestParams requestParams = new RequestParams(context.getResources().getString(R.string.buyseed));
                                    requestParams.setBodyContent(s);
                                    requestParams.setAsJsonContent(true);
                                    x.http().post(requestParams, new Callback.CommonCallback<String>() {


                                        @Override
                                        public void onSuccess(String result) {
                                            if (result.equals("success")) {
                                                Toast.makeText(context, "购买成功,详细的交易信息可在个人中心-我的农场-交易记录查看", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(context, MainActivity.class);
                                                context.startActivity(intent);
                                            } else {
                                                Intent intent = new Intent(context, seedActivity.class);
                                                context.startActivity(intent);
                                                Toast.makeText(context, "购买失败", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {
                                            Intent intent = new Intent(context, seedActivity.class);
                                            context.startActivity(intent);
                                            Toast.makeText(context, "购买失败,请检查网络!", Toast.LENGTH_LONG).show();
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

                                    ToastUtils.showToast(context,"余额不足！");
                                }
                            }
                        });

                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();


            }


        });

    }


    private void jiaorjian(final BaseViewHolder helper, final ProdctBean item) {

        helper.getView(R.id.b2).setOnClickListener(new View.OnClickListener() {
            int b1;

            @Override
            public void onClick(View v) {
                TextView a = helper.getView(R.id.k4);
                b1 = Integer.valueOf(a.getText().toString());
                b1++;
                helper.setText(R.id.k4, this.b1 + "");
                BigDecimal b1 = new BigDecimal(item.getJiage());
                moeny = b1.add(moeny);
                allmoeny.setText(moeny.toString());

            }
        });
        helper.getView(R.id.b1).setOnClickListener(new View.OnClickListener() {
            int b1;

            @Override
            public void onClick(View v) {
                TextView a = helper.getView(R.id.k4);
                if (Integer.valueOf(a.getText().toString()) > 0) {
                    b1 = Integer.valueOf(a.getText().toString());
                    b1--;
                    helper.setText(R.id.k4, this.b1 + "");
                    BigDecimal b1 = new BigDecimal(item.getJiage());
                    moeny = moeny.subtract(b1);
                    allmoeny.setText(moeny.toString());

                }
            }
        });
    }

//    holder.k1 = convertView.findViewById(R.id.k1);
//    holder.k2 = convertView.findViewById(R.id.k2);
//    holder.k3 = convertView.findViewById(R.id.k3);
//    holder.k4 = convertView.findViewById(R.id.k4);
//    holder.k5 = convertView.findViewById(R.id.k5);
//    holder.jian = convertView.findViewById(R.id.b1);
//    holder.jia = convertView.findViewById(R.id.b2);
}
