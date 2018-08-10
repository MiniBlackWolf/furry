package com.example.wolf;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.fragment.tab2;
import com.example.wolf.fragment.tab5;
import com.example.wolf.fragment.tab5_2;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private String result;
    private List<Fragment> fragmentlist;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    @ViewInject(R.id.p2)
    private ImageView p2;
    @ViewInject(R.id.p5)
    private ImageView p5;
    @ViewInject(R.id.nongchang)
    private TextView nongchang;
    @ViewInject(R.id.wod)
    private TextView wod;
    FragmentStatePagerAdapter fragmentPagerAdapter;
    Xutils xutils = new Xutils(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        overridePendingTransition(0,0);
        getfragment();
        denglu();

    }

    private void denglu() {

        //实例化SharedPreferences对象
        final SharedPreferences mySharePerferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        //用getString获取值
        String name = mySharePerferences.getString("userName", "");
        String password = mySharePerferences.getString("password", "");
        Map<String, String> map = new HashMap<>();
        map.put("userName", name);
        map.put("password", password);
        //本地验证登陆
        xutils.post(getResources().getString(R.string.Login), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                if (result != null) {
                    String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                    if (i.equals("success")) {
                        tab5_2 tab5_2 = new tab5_2();
                        fragmentlist.remove(1);
                        fragmentlist.add(tab5_2);
                        fragmentPagerAdapter.notifyDataSetChanged();
                        if (mySharePerferences.getBoolean("loginstatus", false)) {
                            ToastUtils.showToast(MainActivity.this, "欢迎回来");
                            SharedPreferences.Editor edit = mySharePerferences.edit();
                            edit.putBoolean("loginstatus", true);
                            edit.apply();
                        }

                    }
                }
            }
        });/*
        种子转跳！！2Page
        */

        int se = getIntent().getIntExtra("seed", 0);
        if (se == 2) {
            viewPager.setCurrentItem(0);
        }
        //登陆
        String s = getIntent().getStringExtra("success");
        String z = getIntent().getStringExtra("zhuxiao");
        if (s != null) {
            if (s.equals("success")) {
                tab5_2 tab5_2 = new tab5_2();
                fragmentlist.remove(1);
                fragmentlist.add(tab5_2);
                fragmentPagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(1);

            }
        }
        //注销
        if (z != null) {
            if (z.equals("zhuxiao")) {
                tab5 tab5 = new tab5();
                fragmentlist.remove(1);
                fragmentlist.add(tab5);
                fragmentPagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(1);

            }
        }
    }

    private void getfragment() {
        //     tab1 tab1 = new tab1();
        tab2 tab2 = new tab2();
        //       tab3 tab3 = new tab3();
        //       tab4 tab4 = new tab4();
        tab5 tab5 = new tab5();
        fragmentlist = new ArrayList<>();
        //       fragmentlist.add(tab1);
        fragmentlist.add(tab2);
//        fragmentlist.add(tab3);
//        fragmentlist.add(tab4);
        fragmentlist.add(tab5);
        fragmentPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentlist.get(position);
            }

            @Override
            public int getCount() {
                return fragmentlist.size();
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                p2.setImageResource(R.mipmap.p2);
                p5.setImageResource(R.mipmap.p5);
                nongchang.setTextColor(getResources().getColor(R.color.black));
                wod.setTextColor(getResources().getColor(R.color.black));
                switch (position) {

                    case 0: {
                        p2.setImageResource(R.mipmap.p2_2);

                        nongchang.setTextColor(getResources().getColor(R.color.hong));
                        break;
                    }

                    case 1: {
                        p5.setImageResource(R.mipmap.p5_5);
                        wod.setTextColor(getResources().getColor(R.color.hong));
                        break;

                    }


                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.p2: {
                        viewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.p5: {
                        viewPager.setCurrentItem(1);
                        break;
                    }

                    case R.id.nongchang: {
                        viewPager.setCurrentItem(0);
                        break;

                    }
                    case R.id.wod: {
                        viewPager.setCurrentItem(1);
                        break;

                    }

                }
            }
        };
        p2.setOnClickListener(onClickListener);
        p5.setOnClickListener(onClickListener);
        nongchang.setOnClickListener(onClickListener);
        wod.setOnClickListener(onClickListener);

    }
}
