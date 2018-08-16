package com.example.wolf.pointshop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.adpater.pointadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PointshopActivity extends Activity {


    @BindView(R.id.okpointshop)
    Button okpointshop;
    @BindView(R.id.pointshopmoney)
    TextView pointshopmoney;
    @BindView(R.id.pointshopcunt)
    TextView pointshopcunt;
    @BindView(R.id.pointshoprecyclerview)
    RecyclerView pointshoprecyclerview;
    Xutils xutils = new Xutils(PointshopActivity.this);
    int all;
    double allmoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pointshop);
        ButterKnife.bind(this);
        Map<String, String> map = new HashMap<>();
        xutils.get(getResources().getString(R.string.scoreshop), map, new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<pointbean> pointbean = gsonUtil.Gson(result, pointbean.class);
                pointadapter pointadapter = new pointadapter(R.layout.pointitem, pointbean, PointshopActivity.this, okpointshop);
                pointadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @SuppressWarnings("ConstantConditions")
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        List<pointbean> data = adapter.getData();
                        TextView pointcount = (TextView) adapter.getViewByPosition(pointshoprecyclerview, position, R.id.pointcount);
                        TextView pointmoney = (TextView) adapter.getViewByPosition(pointshoprecyclerview, position, R.id.pointmoney);

                        switch (view.getId()) {
                            case R.id.pointjia:
                                String money = pointmoney.getText().toString();
                                allmoney = allmoney + Double.valueOf(money);
                                pointshopmoney.setText("合计：" + allmoney + "元");
                                String s = pointcount.getText().toString();
                                Integer count = Integer.valueOf(s);
                                count++;
                                all++;
                                pointshopcunt.setText("共" + all + "件");
                                pointcount.setText(count + "");
                                break;
                            case R.id.pointjian:
                                String ss = pointcount.getText().toString();
                                Integer counts = Integer.valueOf(ss);
                                counts--;
                                if (counts < 0) {
                                    counts = 0;
                                } else {
                                    all--;
                                    String moneys = pointmoney.getText().toString();
                                    allmoney = allmoney - Double.valueOf(moneys);
                                    pointshopmoney.setText("合计：" + allmoney + "元");
                                    pointshopcunt.setText("共" + all + "件");
                                }

                                pointcount.setText(counts + "");

                                break;
                        }
                    }
                });
                pointadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                pointadapter.isFirstOnly(false);
                pointshoprecyclerview.setLayoutManager(new LinearLayoutManager(PointshopActivity.this));
                pointshoprecyclerview.setAdapter(pointadapter);
                pointadapter.bindToRecyclerView(pointshoprecyclerview);
                pointadapter.setEmptyView(R.layout.loading);
            }
        });


    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(PointshopActivity.this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
           finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}
