package com.example.wolf.land;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.adpater.xuandiadapter;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.xuandi)
public class Xuandi extends AppCompatActivity {
    int t1;
    int t2;
    int t3;
    double moeny;
    int sl;
    List<FarmData.SelectionBean> dddList;
    Xutils xutils = new Xutils(Xuandi.this);
    @BindView(R.id.xuandifanhui)
    ImageView xuandifanhui;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.kuaishu)
    TextView kuaishu;
    @BindView(R.id.zhongjian)
    TextView zhongjian;
    @BindView(R.id.xuandirecyclerview)
    RecyclerView xuandirecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuandi);
        ButterKnife.bind(Xuandi.this);
        xutils.get(getResources().getString(R.string.getlandinfo), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<Farminfo> Farminfo = gsonUtil.Gson(result, Farminfo.class);
                xuandiadapter xuandiadapter = new xuandiadapter(R.layout.xuandiitem, Farminfo, Xuandi.this);
                xuandiadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                xuandiadapter.isFirstOnly(false);
                xuandirecyclerview.setLayoutManager(new LinearLayoutManager(Xuandi.this));
                xuandirecyclerview.setAdapter(xuandiadapter);
            }
        });

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
            @Override
            public void onClick(View v) {


            }
        });
    }
}
