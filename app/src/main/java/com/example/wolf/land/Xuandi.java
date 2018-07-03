package com.example.wolf.land;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.cultivation.genyun;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContentView(R.layout.xuandi)
public class Xuandi extends AppCompatActivity {
    @ViewInject(R.id.jiage_2)
    private TextView jiage_2;
    @ViewInject(R.id.guige_2)
    private TextView guige_2;
    @ViewInject(R.id.jiage_2_2)
    private TextView jiage_2_2;
    @ViewInject(R.id.guige_2_2)
    private TextView guige_2_2;
    @ViewInject(R.id.jiage_2_3)
    private TextView jiage_2_3;
    @ViewInject(R.id.guige_2_3)
    private TextView guige_2_3;
    @ViewInject(R.id.tianjia)
    private Button tianjia;
    @ViewInject(R.id.tianjia2)
    private Button tianjia2;
    @ViewInject(R.id.tianjian3)
    private Button tianjia3;
    @ViewInject(R.id.jian)
    private Button jian;
    @ViewInject(R.id.jian2)
    private Button jian2;
    @ViewInject(R.id.jian3)
    private Button jian3;
    @ViewInject(R.id.shuliang)
    private TextView shuliang;
    @ViewInject(R.id.shuliang2)
    private TextView shuliang2;
    @ViewInject(R.id.shuliang3)
    private TextView shuliang3;
    @ViewInject(R.id.zhongjian)
    private TextView zhongjian;
    @ViewInject(R.id.kuaishu)
    private TextView kuaishu;
    @ViewInject(R.id.buy)
    private Button buy;
    @ViewInject(R.id.xuandifanhui)
    private ImageView xuandifanhui;
    int t1;
    int t2;
    int t3;
    double moeny;
    int sl;
    List<FarmData.SelectionBean> dddList;
    Xutils xutils = new Xutils(Xuandi.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //http get农场选地
        settext(xutils);
        //数量总价
        listener();
        //购买
        buy(xutils);
        xuandifanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Xuandi.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void buy(final Xutils xutils) {
        buy.setOnClickListener(new OnClickListener() {
            Map<String, String> FarmCountmap;

            @Override
            public void onClick(View v) {

                getland("A", "9999", t1, jiage_2);
                getland("B", "9999", t2, jiage_2_2);
                getland("c", "9999", t3, jiage_2_3);
            }

            private void getland(final String land, final String pageItemSize, final int count, final TextView jiage) {
                FarmCountmap = new HashMap<String, String>();
                FarmCountmap.put("pageItemSize", pageItemSize);
                FarmCountmap.put("type", land);
                xutils.get(getResources().getString(R.string.getFarmCount), FarmCountmap, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(final String result) {
                        String s = result.substring(result.lastIndexOf("}") - 1, result.lastIndexOf("}"));
                        Map<String, String> FarmLimitmap = new HashMap<>();
                        for (int i = 1; i <= Integer.valueOf(s); i++) {
                            FarmLimitmap.put("currentPage", String.valueOf(i));
                            FarmLimitmap.put("pageItemSize", pageItemSize);
                            FarmLimitmap.put("type", land);
                            xutils.get(getResources().getString(R.string.getFarmLimit), FarmLimitmap, new Xutils.XCallBack() {
                                @Override
                                public void onResponse(String result) {
                                    GsonUtil gsonUtil = new GsonUtil();
                                    List<getFarmLimit> Farm = gsonUtil.Gson(result, getFarmLimit.class);
                                    List<String> listd = new ArrayList<String>();
                                    for (int i = 0; i < Farm.size(); i++) {
                                        if (Farm.get(i).getUid() == -1) {
                                            listd.add(Farm.get(i).getFid());
                                        }
                                    }
                                    Log.i("iiiiiiiiii", listd.size() + ";" + count);
                                    if (count > listd.size()) {
                                        Toast.makeText(Xuandi.this, "购买失败,土地数量不够", Toast.LENGTH_SHORT).show();
                                        return;

                                    }
                                    if (count <= listd.size()) {
                                        SharedPreferences mySharePerferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                                        int name = mySharePerferences.getInt("uid", 0);
                                        if (count != 0) {
                                            if (listd.size() != 0) {
                                                for (int i = 0; i < count; i++) {
                                                    Map<String, String> map = new HashMap<>();
                                                    map.put("uid", String.valueOf(name));
                                                    map.put("fid", listd.get(i));
                                                    map.put("year", "1");
                                                    Log.i("iiiiiiiiiii", listd.get(i));
                                                    map.put("token", new Token().getToken(name));

                                                    xutils.get(getResources().getString(R.string.buyFrame), map, new Xutils.XCallBack() {
                                                        @Override
                                                        public void onResponse(String result) {
                                                            String s = result.substring(result.indexOf(":") + 2, result.lastIndexOf("\""));
                                                            Log.i("iiiiiiiiiiiiiiiii", s);
                                                            if (s.equals("success")) {
                                                                String s1 = jiage.getText().toString();
                                                                String s2 = s1.substring(0, s1.indexOf("元"));
                                                                BigDecimal big1 = new BigDecimal(count);
                                                                BigDecimal big2 = new BigDecimal(s2);
                                                                float seedmoney = big1.multiply(big2).floatValue();
                                                                Map<String, String> map = new HashMap<>();
                                                                map.put("uid", String.valueOf(new Getuserinfo(Xuandi.this).getuid()));
                                                                map.put("money", "-" + seedmoney);
                                                                map.put("token", new Token().getToken(new Getuserinfo(Xuandi.this).getuid()));
                                                                xutils.get(getResources().getString(R.string.clientMoney), map, new Xutils.XCallBack() {
                                                                    @Override
                                                                    public void onResponse(String result) {
                                                                        String su = result.substring(result.lastIndexOf("\"") - 7, result.lastIndexOf("\""));
                                                                        if (su.equals("success")) {
                                                                            ToastUtils.showToast(Xuandi.this, "购买成功");
                                                                            Intent intent = new Intent(Xuandi.this, MainActivity.class);
                                                                            intent.putExtra("seed", 2);
                                                                            startActivity(intent);
                                                                        }
                                                                    }
                                                                });

                                                            }
                                                            if (s.equals("fail")) {
                                                                Toast.makeText(Xuandi.this, "购买失败", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }

                                }
                            });
                        }
                    }

                });
            }
        });
    }

    private void listener() {
        //A地添加删除
        tianjia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                t1 += 1;
                shuliang.setText(String.valueOf(t1));
                Double m;
                if (dddList != null) {
                    m = dddList.get(0).getMoney();
                } else {
                    m = 10d;
                }
                moeny += m;
                zhongjian.setText(String.valueOf(moeny));
                sl += 1;
                kuaishu.setText(String.valueOf(sl));

            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1 <= 0) {
                    t1 = 0;
                    shuliang.setText(String.valueOf(t1));
                } else {
                    t1 -= 1;
                    shuliang.setText(String.valueOf(t1));
                    Double m;
                    if (dddList != null) {
                        m = dddList.get(0).getMoney();
                    } else {
                        m = 10d;

                    }
                    moeny -= m;
                    zhongjian.setText(String.valueOf(moeny));
                    sl -= 1;
                    kuaishu.setText(String.valueOf(sl));
                }
            }
        });
        //B地添加删除
        tianjia2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                t2 += 1;
                shuliang2.setText(String.valueOf(t2));
                Double m;
                if (dddList != null) {
                    m = dddList.get(1).getMoney();
                } else {
                    m = 10d;

                }
                moeny += m;
                zhongjian.setText(String.valueOf(moeny));
                sl += 1;
                kuaishu.setText(String.valueOf(sl));
            }
        });
        jian2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t2 <= 0) {
                    t2 = 0;
                    shuliang2.setText(String.valueOf(t2));
                } else {
                    t2 -= 1;
                    shuliang2.setText(String.valueOf(t2));
                    Double m;
                    if (dddList != null) {
                        m = dddList.get(1).getMoney();
                    } else {
                        m = 10d;

                    }
                    moeny -= m;
                    zhongjian.setText(String.valueOf(moeny));
                    sl -= 1;
                    kuaishu.setText(String.valueOf(sl));
                }
            }
        });
        //c地添加删除
        tianjia3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                t3 += 1;
                shuliang3.setText(String.valueOf(t3));
                Double m;
                if (dddList != null) {
                    m = dddList.get(2).getMoney();
                } else {
                    m = 10d;

                }
                moeny += m;
                zhongjian.setText(String.valueOf(moeny));
                sl += 1;
                kuaishu.setText(String.valueOf(sl));
            }
        });

        jian3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t3 <= 0) {
                    t3 = 0;
                    shuliang3.setText(String.valueOf(t3));
                } else {
                    t3 -= 1;
                    shuliang3.setText(String.valueOf(t3));
                    Double m;
                    if (dddList != null) {
                        m = dddList.get(2).getMoney();
                    } else {
                        m = 10d;

                    }
                    moeny -= m;
                    zhongjian.setText(String.valueOf(moeny));
                    sl -= 1;
                    kuaishu.setText(String.valueOf(sl));
                }
            }
        });
    }


    private void settext(Xutils xutils) {
        xutils.get(getResources().getString(R.string.farmData), new HashMap<String, String>(), new Xutils.XCallBack() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiiiiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                FarmData FarmData = gsonUtil.getfarmjson(result);
                dddList = FarmData.getSelection();


                //选地1
                jiage_2.setText(String.valueOf(dddList.get(0).getMoney()) + "元");
                guige_2.setText(String.valueOf(dddList.get(0).getM()) + "/㎡");
                //选地2
                jiage_2_2.setText(String.valueOf(dddList.get(1).getMoney()) + "元");
                guige_2_2.setText(String.valueOf(dddList.get(1).getM()) + "/㎡");
                //选地3
                jiage_2_3.setText(String.valueOf(dddList.get(2).getMoney()) + "元");
                guige_2_3.setText(String.valueOf(dddList.get(2).getM()) + "/㎡");
                Log.i("iiiiiiiiiiiiiiiii", String.valueOf(dddList.get(0).getM()));
            }

        });
    }
}
