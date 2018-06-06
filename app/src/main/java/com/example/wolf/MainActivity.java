package com.example.wolf;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolf.Utils.Xutils;
import com.example.wolf.fragment.tab1;
import com.example.wolf.fragment.tab2;
import com.example.wolf.fragment.tab3;
import com.example.wolf.fragment.tab4;
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
    @ViewInject(R.id.p1)
    private ImageView p1;
    @ViewInject(R.id.p2)
    private ImageView p2;
    @ViewInject(R.id.p3)
    private ImageView p3;
    @ViewInject(R.id.p4)
    private ImageView p4;
    @ViewInject(R.id.p5)
    private ImageView p5;
    @ViewInject(R.id.luyou)
    private TextView luyou;
    @ViewInject(R.id.nongchang)
    private TextView nongchang;
    @ViewInject(R.id.yanglao)
    private TextView yanglao;
    @ViewInject(R.id.nengyuan)
    private TextView nengyuan;
    @ViewInject(R.id.wod)
    private TextView wod;
    FragmentStatePagerAdapter fragmentPagerAdapter;
    Xutils xutils = new Xutils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(MainActivity.this);
        getfragment();
        denglu();


//        //注册方法
//        Map maps = new HashMap();
//        maps.put("userName", "qqqq");
//        maps.put("password", "123456");
//        Xutils xutils = new Xutils();
//        xutils.post("http://203p75796g.iok.la:43975/net/userBase/register", maps, new Xutils.XCallBack() {
//            @Override
//            public void onResponse(String result) {
//                Log.i("iiiiiiiiiiiii", "注册" + result);
//            }
//        });
    }

    private void denglu() {

        //实例化SharedPreferences对象
        SharedPreferences mySharePerferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        //用getString获取值
        final String name = mySharePerferences.getString("userName", "");
        String password = mySharePerferences.getString("password", "");
        Map<String, String> map = new HashMap();
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
                        fragmentlist.remove(4);
                        fragmentlist.add(tab5_2);
                        fragmentPagerAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"欢迎回来"+name,Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        //  Log.i("iiiiiiiiiiiiiiiiii",name);
        //登陆
        String s = getIntent().getStringExtra("success");
        String z = getIntent().getStringExtra("zhuxiao");
        if (s != null) {
            if (s.equals("success")) {
                tab5_2 tab5_2 = new tab5_2();
                fragmentlist.remove(4);
                fragmentlist.add(tab5_2);
                fragmentPagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(4);

            }
        }
        //注销
        if (z != null) {
            if (z.equals("zhuxiao")) {
                tab5 tab5 = new tab5();
                fragmentlist.remove(4);
                fragmentlist.add(tab5);
                fragmentPagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(4);

            }
        }
    }

    private void getfragment() {
        tab1 tab1 = new tab1();
        tab2 tab2 = new tab2();
        tab3 tab3 = new tab3();
        tab4 tab4 = new tab4();
        tab5 tab5 = new tab5();
        fragmentlist = new ArrayList<>();
        fragmentlist.add(tab1);
        fragmentlist.add(tab2);
        fragmentlist.add(tab3);
        fragmentlist.add(tab4);
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
                p1.setImageResource(R.mipmap.p1);
                p2.setImageResource(R.mipmap.p2);
                p3.setImageResource(R.mipmap.p3);
                p4.setImageResource(R.mipmap.p4);
                p5.setImageResource(R.mipmap.p5);
                luyou.setTextColor(getResources().getColor(R.color.black));
                nongchang.setTextColor(getResources().getColor(R.color.black));
                yanglao.setTextColor(getResources().getColor(R.color.black));
                nengyuan.setTextColor(getResources().getColor(R.color.black));
                wod.setTextColor(getResources().getColor(R.color.black));
                switch (position) {
                    case 0: {
                        p1.setImageResource(R.mipmap.p1_2);
                        luyou.setTextColor(getResources().getColor(R.color.hong));
                        break;

                    }
                    case 1: {
                        p2.setImageResource(R.mipmap.p2_2);

                        nongchang.setTextColor(getResources().getColor(R.color.hong));
                        break;
                    }
                    case 2: {

                        p3.setImageResource(R.mipmap.p3_3);
                        yanglao.setTextColor(getResources().getColor(R.color.hong));
                        break;
                    }
                    case 3: {

                        p4.setImageResource(R.mipmap.p4_4);
                        nengyuan.setTextColor(getResources().getColor(R.color.hong));
                        break;

                    }
                    case 4: {
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
                    case R.id.p1: {
                        viewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.p2: {
                        viewPager.setCurrentItem(1);
                        break;
                    }
                    case R.id.p3: {
                        viewPager.setCurrentItem(2);
                        break;
                    }
                    case R.id.p4: {
                        viewPager.setCurrentItem(3);
                        break;
                    }
                    case R.id.p5: {
                        viewPager.setCurrentItem(4);
                        break;
                    }
                    case R.id.luyou: {
                        viewPager.setCurrentItem(0);
                        break;

                    }
                    case R.id.nongchang: {
                        viewPager.setCurrentItem(1);
                        break;

                    }
                    case R.id.yanglao: {
                        viewPager.setCurrentItem(2);
                        break;

                    }
                    case R.id.nengyuan: {
                        viewPager.setCurrentItem(3);
                        break;

                    }
                    case R.id.wod: {
                        viewPager.setCurrentItem(4);
                        break;

                    }

                }
            }
        };
        p1.setOnClickListener(onClickListener);
        p2.setOnClickListener(onClickListener);
        p3.setOnClickListener(onClickListener);
        p4.setOnClickListener(onClickListener);
        p5.setOnClickListener(onClickListener);
        luyou.setOnClickListener(onClickListener);
        nongchang.setOnClickListener(onClickListener);
        yanglao.setOnClickListener(onClickListener);
        nengyuan.setOnClickListener(onClickListener);
        wod.setOnClickListener(onClickListener);

    }
}
