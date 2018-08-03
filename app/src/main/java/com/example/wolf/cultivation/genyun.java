package com.example.wolf.cultivation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.OrderUtils;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.land.Farminfo;
import com.example.wolf.land.orderbeans;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.genyun)
public class genyun extends AppCompatActivity {
    @ViewInject(R.id.genyunfanhui)
    private ImageView genyunfanhui;
    @ViewInject(R.id.x6)
    private Button jian;
    @ViewInject(R.id.x7)
    private Button jia;
    @ViewInject(R.id.x6_2)
    private Button jian_2;
    @ViewInject(R.id.x7_2)
    private Button jia_2;
    @ViewInject(R.id.x6_3_2_1)
    private Button jian_3;
    @ViewInject(R.id.x7_3)
    private Button jia_3;
    @ViewInject(R.id.x6_4)
    private Button jian_4;
    @ViewInject(R.id.x7_4)
    private Button jia_4;
    @ViewInject(R.id.x6_5)
    private Button jian_5;
    @ViewInject(R.id.x7_5)
    private Button jia_5;
    @ViewInject(R.id.x8)
    private TextView shul;
    @ViewInject(R.id.x8_2)
    private TextView shul_2;
    @ViewInject(R.id.x8_3)
    private TextView shul_3;
    @ViewInject(R.id.x8_4)
    private TextView shul_4;
    @ViewInject(R.id.x8_5)
    private TextView shul_5;
    @ViewInject(R.id.x5)
    private TextView money;
    @ViewInject(R.id.x5_2)
    private TextView money_2;
    @ViewInject(R.id.x5_3)
    private TextView money_3;
    @ViewInject(R.id.x5_3_2)
    private TextView money_3_2;
    @ViewInject(R.id.x5_4)
    private TextView money_4;
    @ViewInject(R.id.x5_5)
    private TextView money_5;
    @ViewInject(R.id.zhongjian)
    private TextView zhongjian;
    @ViewInject(R.id.buy)
    private Button buy;
    @ViewInject(R.id.gkuaishu)
    private TextView kuaishu;
    @ViewInject(R.id.x6_3_2_2)
    private Button jian6;
    @ViewInject(R.id.x7_3_2)
    private Button jia6;
    @ViewInject(R.id.x8_3_2)
    private TextView shul_6;
    Xutils xutils = new Xutils(genyun.this);
    int add = 0;
    int add2 = 0;
    int add3 = 0;
    int add4 = 0;
    int add5 = 0;
    int add6 = 0;
    double total = 0;
    int toatalcount;
    List<TextView> addlist;
    List<Double> price = new ArrayList<>();
    List<orderbeans.payitem> payitems = new ArrayList<>();
    double allprice;
    double off = 1.0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //返回
        back();
        //加减数量价格
        init();
        //购买
        buy();
        xutils.get(getResources().getString(R.string.getcultivation), new HashMap<String, String>(), new Xutils.XCallBack() {
            @SuppressLint("SetTextI18n")
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                List<Farminfo> Farminfo = gsonUtil.Gson(result, Farminfo.class);
                for (Farminfo farminfo : Farminfo) {
                    price.add(farminfo.getPrice());
                    switch (farminfo.getType()) {
                        case "0":
                            money.setText(farminfo.getPrice() + "元/5m²");
                            break;
                        case "1":
                            money_2.setText(farminfo.getPrice() + "元/5m²");
                            break;
                        case "2":
                            money_3.setText(farminfo.getPrice() + "元/5m²");
                            break;
                        case "3":
                            money_3_2.setText(farminfo.getPrice() + "元/5m²");
                            break;
                        case "4":
                            money_4.setText(farminfo.getPrice() + "元/5m²");
                            break;
                        case "5":
                            money_5.setText(farminfo.getPrice() + "元/5m²");
                            break;

                    }

                }


            }
        });

    }

    private void init() {
        jianli g1 = new jianli(shul, money, add);
        jianli g2 = new jianli(shul_2, money_2, add2);
        jianli g3 = new jianli(shul_3, money_3, add3);
        jianli g4 = new jianli(shul_4, money_4, add4);
        jianli g5 = new jianli(shul_5, money_5, add5);
        jianli g6 = new jianli(shul_6, money_3_2, add6);
        addlist = new ArrayList<>();
        addlist.add(shul);
        addlist.add(shul_2);
        addlist.add(shul_3);
        addlist.add(shul_4);
        addlist.add(shul_5);
        addlist.add(shul_6);
        jia.setOnClickListener(g1.jiali());
        jian.setOnClickListener(g1);
        jia_2.setOnClickListener(g2.jiali());
        jian_2.setOnClickListener(g2);
        jia_3.setOnClickListener(g3.jiali());
        jian_3.setOnClickListener(g3);
        jia_4.setOnClickListener(g4.jiali());
        jian_4.setOnClickListener(g4);
        jia_5.setOnClickListener(g5.jiali());
        jian_5.setOnClickListener(g5);
        jia6.setOnClickListener(g6.jiali());
        jian6.setOnClickListener(g6);
    }

    private void buy() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer uids = new Getuserinfo(genyun.this).getuid();
                if (uids == 0) {
                    ToastUtils.showToast(genyun.this, "请先登录");
                    return;
                }
                SharedPreferences mSharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                final int uid = mSharedPreferences.getInt("uid", 0);
                for (int i = 0; i < 6; i++) {
                    if (Integer.valueOf(addlist.get(i).getText().toString()) != 0) {
                        orderbeans.payitem addpayitem = OrderUtils.addpayitem(String.valueOf(i), String.valueOf(i), Integer.valueOf(addlist.get(i).getText().toString()), price.get(i));
                        payitems.add(addpayitem);
                        allprice += Integer.valueOf(addlist.get(i).getText().toString()) * price.get(i);
                    }

                }
                Map<String, String> map2 = new HashMap<>();
                map2.put("userName", new Getuserinfo(genyun.this).getusername());
                xutils.get(getResources().getString(R.string.Userinfo), map2, new Xutils.XCallBack() {
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
                        if (UserInfo.get(0).getMoney() >= allprice) {
                            orderbeans orderbeans = OrderUtils.addorder("购买耕耘券", 1, allprice, uid, System.currentTimeMillis() / 1000, payitems);
                            Gson gson = new Gson();
                            String s = gson.toJson(orderbeans);
                            RequestParams requestParams = new RequestParams(getResources().getString(R.string.buyticket));
                            requestParams.setBodyContent(s);
                            requestParams.setAsJsonContent(true);
                            x.http().post(requestParams, new Callback.CommonCallback<String>() {

                                @Override
                                public void onSuccess(String result) {
                                    if (result.equals("success")) {
                                        ToastUtils.showToast(genyun.this, "购买成功!");
                                    } else {
                                        ToastUtils.showToast(genyun.this, "购买失败!");

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
                        } else {
                            ToastUtils.showToast(genyun.this, "余额不足,请充值!");
                        }
                    }
                });

            }
        });
    }

    public String getcount(TextView vd) {
        return vd.getText().toString();
    }

    @SuppressLint("SetTextI18n")
    private class jianli implements View.OnClickListener {
        private TextView vs;
        private TextView moen;
        private int adds;

        private jianli(TextView vs, TextView moen, int adds) {
            this.vs = vs;
            this.moen = moen;
            this.adds = adds;
        }

        public int getAdds() {
            return adds;
        }

        public void setAdds(int adds) {
            this.adds = adds;
        }


        @Override
        public void onClick(View v) {


            if (adds <= 0) {
                vs.setText("0");
                adds = 0;
            } else {
                adds -= 1;
                toatalcount--;
                vs.setText(adds + "");
                double m = Double.valueOf(moen.getText().toString().substring(0, moen.getText().toString().lastIndexOf("元")));
                if (adds >= 4) {
                    BigDecimal bigDecimal= BigDecimal.valueOf(off);
                    if (bigDecimal.equals(new BigDecimal(1.0))) {
                        bigDecimal =  BigDecimal.valueOf(1.0);
                    }
                    BigDecimal bigDecimal1 =  BigDecimal.valueOf(m);
                    BigDecimal multiply = bigDecimal1.multiply(bigDecimal);
                    m=multiply.doubleValue();
                    BigDecimal add = bigDecimal.add(BigDecimal.valueOf(0.05));
                    off=add.doubleValue();
                }
                total=(new BigDecimal(total).subtract(new BigDecimal(m))).doubleValue();
                zhongjian.setText(total + "");
                kuaishu.setText(toatalcount + "");
            }
        }

        private View.OnClickListener jiali() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (adds < 0) {
                        vs.setText("0");
                        adds = 0;
                    } else {
                        adds += 1;
                        toatalcount++;
                        vs.setText(adds + "");
                        double m = Double.valueOf(moen.getText().toString().substring(0, moen.getText().toString().lastIndexOf("元")));
                        if (adds > 4) {
                            BigDecimal bigDecimal =  BigDecimal.valueOf(off);
                            BigDecimal add = bigDecimal.subtract(BigDecimal.valueOf(0.05));
                            off=add.doubleValue();
                            BigDecimal bigDecimal1 = BigDecimal.valueOf (m);
                            BigDecimal multiply = bigDecimal1.multiply(add);
                            m =multiply.doubleValue() ;
                        }
                        total=(new BigDecimal(total).add(new BigDecimal(m))).doubleValue();
//                        total += m;
                        zhongjian.setText(total + "");
                        kuaishu.setText(toatalcount + "");
                    }
                }
            };

        }

    }

    private void back() {
        genyunfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(genyun.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
