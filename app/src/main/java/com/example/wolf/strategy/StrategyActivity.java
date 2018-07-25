package com.example.wolf.strategy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.wolf.R;
import com.example.wolf.adpater.Strategyadapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StrategyActivity extends Activity {


    @BindView(R.id.strategy)
    RecyclerView strategy;
    List<Strategybean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategy);
        ButterKnife.bind(this);
        list=new ArrayList<>();
        initdata(R.mipmap.g2,getResources().getString(R.string.g1));
        initdata(R.mipmap.g3,getResources().getString(R.string.g2));
        initdata(R.mipmap.g4,getResources().getString(R.string.g3));
        initdata(R.mipmap.g5,getResources().getString(R.string.g4));
        initdata(R.mipmap.g6,getResources().getString(R.string.g5));
        initdata(R.mipmap.g7,getResources().getString(R.string.g6));
        initdata(R.mipmap.g8,getResources().getString(R.string.g7));
        initdata(R.mipmap.g9,getResources().getString(R.string.g8));
        initdata(R.mipmap.g10,getResources().getString(R.string.g9));
        initdata(R.mipmap.g11,getResources().getString(R.string.g10));
        initdata(R.mipmap.g12,getResources().getString(R.string.g11));
        initdata(R.mipmap.g13,getResources().getString(R.string.g12));
        initdata(R.mipmap.g12,getResources().getString(R.string.g13));
        initdata(R.mipmap.g14,getResources().getString(R.string.g14));
        initdata(R.mipmap.g15,getResources().getString(R.string.g15));
        Strategyadapter strategyadapter=new Strategyadapter(R.layout.strategyitem,list);
        strategyadapter.addHeaderView(getLayoutInflater().inflate(R.layout.strategyitem2,null));
        strategy.setLayoutManager(new LinearLayoutManager(StrategyActivity.this));
        strategy.setAdapter(strategyadapter);


    }

    private void initdata(int img,String text) {
        Strategybean strategybean = new Strategybean();
        strategybean.setImages(img);
        strategybean.setText(text);
        list.add(strategybean);

    }

    @OnClick(R.id.strategyfh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, 0);
    }
}
