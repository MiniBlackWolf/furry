package com.example.wolf.cultivation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.land.FarmData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
    @ViewInject(R.id.x6_3)
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
    @ViewInject(R.id.bz)
    private ConstraintLayout constraintLayout;
    Xutils xutils = new Xutils();
    int add = 0;
    int add2 = 0;
    int add3 = 0;
    int add4 = 0;
    int add5 = 0;
    double total = 0;
    int toatalcount;
    List<TextView> addlist;

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
        xutils.get(getResources().getString(R.string.farmData), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil=new GsonUtil();
                FarmData getfarmjson = gsonUtil.getfarmjson(result);
                money.setText(getfarmjson.getCultivating().getSowing()+"元/5m²");
                money_2.setText(getfarmjson.getCultivating().getWatering()+"元/5m²");
                money_3.setText(getfarmjson.getCultivating().getFertilizer()+"元/5m²");
                money_4.setText(getfarmjson.getCultivating().getWeeding()+"元/5m²");
                money_5.setText(getfarmjson.getCultivating().getExterminator()+"元/5m²");

            }
        });

    }

    private void init() {
        jianli g1 = new jianli(shul, money, add);
        jianli g2 = new jianli(shul_2, money_2, add2);
        jianli g3 = new jianli(shul_3, money_3, add3);
        jianli g4 = new jianli(shul_4, money_4, add4);
        jianli g5 = new jianli(shul_5, money_5, add5);
        addlist = new ArrayList<>();
        addlist.add(shul);
        addlist.add(shul_2);
        addlist.add(shul_3);
        addlist.add(shul_4);
        addlist.add(shul_5);
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
    }

    private void buy() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                int uid = mSharedPreferences.getInt("uid", 0);
                for (int i = 0; i < 5; i++) {
                    Map<String, String> map = new HashMap();
                    map.put("uid", String.valueOf(uid));
                    map.put("tid", String.valueOf(i));
                    map.put("count", getcount(addlist.get(i)));
                    map.put("token", new Token().getToken(uid));
                    Log.i("iiiiiiiiiiiiiiiii", getcount(addlist.get(i)) + "");
                    final int finalI = i;
                    xutils.get(getResources().getString(R.string.buyTickets), map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            String s = result.substring(result.indexOf(":") + 2, result.lastIndexOf("\""));
                            Log.i("iiiiiiiiiiiiiiiii", s);
                            if (s.equals("success") && finalI == 4) {
                                Toast.makeText(genyun.this, "购买成功", Toast.LENGTH_SHORT).show();

                            }
                            if (s.equals("fail")) {
                                Toast.makeText(genyun.this, "购买失败", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }

    public String getcount(TextView vd) {


        return vd.getText().toString();
    }

    private class jianli implements View.OnClickListener {
        private TextView vs;
        private TextView moen;
        private int adds;

        public jianli(TextView vs, TextView moen, int adds) {
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


            if (adds <=0) {
                vs.setText("0");
                adds = 0;
            } else {
                adds -= 1;
                toatalcount--;
                vs.setText(adds + "");
                double m = Double.valueOf(moen.getText().toString().substring(0, moen.getText().toString().lastIndexOf("元")));
                total -= m;
                zhongjian.setText(total + "");
                kuaishu.setText(toatalcount + "");
            }
        }

        private View.OnClickListener jiali() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (adds <0) {
                        vs.setText("0");
                        adds = 0;
                    } else {
                        adds += 1;
                        toatalcount++;
                        vs.setText(adds + "");
                        double m = Double.valueOf(moen.getText().toString().substring(0,  moen.getText().toString().lastIndexOf("元")));
                        total += m;
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