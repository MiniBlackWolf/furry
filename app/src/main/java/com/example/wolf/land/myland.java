package com.example.wolf.land;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.loading_show;
import com.example.wolf.adpater.Mylandadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class myland extends AppCompatActivity {

    @BindView(R.id.mylandfhui)
    ImageView mylandfhui;
    @BindView(R.id.mylandrecyclerview)
    RecyclerView mylandrecyclerview;
    @BindView(R.id.used)
    LinearLayout used;
    @BindView(R.id.overdue)
    LinearLayout overdue;
    Getuserinfo getuserinfo = new Getuserinfo(this);
    Mylandadapter mylandadapter;
    List<userland> overduelist;
    List<userland> userland;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.xuzu)
    Button xuzu;
    Xutils xutils = new Xutils(myland.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myland);
        ButterKnife.bind(this);

        Map<String, String> map = new HashMap();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        map.put("token", new Token().getToken(getuserinfo.getuid()));
        xutils.get(getResources().getString(R.string.getUserFrame), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                userland = gsonUtil.Gson(result, userland.class);
                overduelist = new ArrayList();
                for (int x = 0; x < userland.size(); x++) {
                    long s = (userland.get(x).getValidityperiod() - System.currentTimeMillis()) / (1000 * 3600 * 24);
                    if (s < 0) {
                        overduelist.add(userland.get(x));

                        userland.remove(x);
                    }
                }
                mylandrecyclerview.setLayoutManager(new LinearLayoutManager(myland.this));
                mylandadapter = new Mylandadapter(R.layout.mylinditem, userland, myland.this,xuzu);
                mylandadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mylandadapter.bindToRecyclerView(mylandrecyclerview);
                mylandadapter.setEmptyView(R.layout.loading);
                mylandadapter.isFirstOnly(false);
                mylandrecyclerview.setAdapter(mylandadapter);
                mylandadapter.notifyDataSetChanged();

            }
        });


    }

    @OnClick({R.id.used, R.id.overdue, R.id.mylandfhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.used:
                mylandrecyclerview.setLayoutManager(new LinearLayoutManager(myland.this));
                mylandadapter = new Mylandadapter(R.layout.mylinditem, userland, myland.this,xuzu);
                mylandrecyclerview.setAdapter(mylandadapter);
                mylandadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mylandadapter.isFirstOnly(false);
                mylandadapter.notifyDataSetChanged();
                view5.setBackground(getResources().getDrawable(R.color.hong));
                view7.setBackground(getResources().getDrawable(R.color.w));
                break;
            case R.id.overdue:
                mylandrecyclerview.setLayoutManager(new LinearLayoutManager(myland.this));
                mylandadapter = new Mylandadapter(R.layout.mylinditem, overduelist, myland.this,xuzu);
//                Log.i("iiiiiiiiiiii", overduelist.get(0).getValidityperiod() + "");
                mylandrecyclerview.setAdapter(mylandadapter);
                mylandadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mylandadapter.isFirstOnly(false);
                mylandadapter.notifyDataSetChanged();
                mylandadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ImageView xuzhu = view.findViewById(R.id.xuzhu);
                        int visibility = xuzhu.getVisibility();
                        if (visibility == View.GONE) {
                            xuzhu.setVisibility(View.VISIBLE);
                        } else {
                            xuzhu.setVisibility(View.GONE);

                        }
                    }
                });
                view5.setBackground(getResources().getDrawable(R.color.w));
                view7.setBackground(getResources().getDrawable(R.color.hong));
                break;
            case R.id.mylandfhui:
                finish();
                overridePendingTransition(0, 0);
                break;
        }
    }
}
