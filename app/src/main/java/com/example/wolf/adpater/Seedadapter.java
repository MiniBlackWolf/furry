package com.example.wolf.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.seed.ProdctBean;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seedadapter extends BaseQuickAdapter<ProdctBean, BaseViewHolder> {
    private Context context;
    private BigDecimal moeny = new BigDecimal(0);
    private TextView allmoeny;
    private Button buy;
    private List<TextView> count = new ArrayList<>();
    private List<String> listsusse = new ArrayList<>();
    private List<Integer> sid = new ArrayList<>();
    private ZLoadingDialog zloadingDiaLog;
    private List<String> buymoney = new ArrayList<>();
    int l;

    public Seedadapter(int layoutResId, @Nullable List<ProdctBean> data, Context context, TextView allmoeny, Button buy, ZLoadingDialog zloadingDiaLog) {
        super(layoutResId, data);
        this.context = context;
        this.allmoeny = allmoeny;
        this.buy = buy;
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
        buymoney.add(item.getJiage());
        jiaorjian(helper, item);
        buy(item, buymoney);
        zloadingDiaLog.dismiss();
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

    public void buy(final ProdctBean item, final List<String> buymoney) {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Xutils xutils = new Xutils(context);


                //用getint获取值
                for (int i = 0; i < count.size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
                    map.put("sid", String.valueOf(sid.get(i)));
                    map.put("buycount", count.get(i).getText().toString());
                    map.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
                    final int finalI1 = i;
                    xutils.get(context.getResources().getString(R.string.buyseed), map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            Log.i("iiiiiiiiiiii", result);
                            String su = result.substring(result.lastIndexOf("\"") - 7, result.lastIndexOf("\""));
                            if (su.equals("success")) {
                                if (Integer.valueOf(count.get(finalI1).getText().toString()) != 0) {
                                    BigDecimal big1=new BigDecimal(count.get(finalI1).getText().toString());
                                    BigDecimal big2=new BigDecimal(buymoney.get(finalI1));
                                    float seedmoney=big1.multiply(big2).floatValue();
                                    Map<String, String> map = new HashMap<>();
                                    map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
                                    map.put("money", "-"+seedmoney );
                                    map.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
                                    xutils.get(context.getResources().getString(R.string.clientMoney), map, new Xutils.XCallBack() {
                                        @Override
                                        public void onResponse(String result) {
                                            String su = result.substring(result.lastIndexOf("\"") - 7, result.lastIndexOf("\""));
                                            if (su.equals("success")) {
                                                ToastUtils.showToast(context, "购买成功");
                                                Intent intent=new Intent(context, MainActivity.class);
                                                intent.putExtra("seed",2);
                                                context.startActivity(intent);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                    if (item.getCount() == 0 && Integer.valueOf(count.get(i).getText().toString()) > 0) {
                        Log.i("iiiiiiiii", item.getCount() + count.get(i).getText().toString());
                        Toast.makeText(context, "购买失败，请检查网络或种子数量", Toast.LENGTH_LONG).show();
                        break;


                    }

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
