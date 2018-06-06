package com.example.wolf.userbean;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.atlas.AtlasActivity;
import com.example.wolf.cultivation.mycultivation;
import com.example.wolf.me.harvest.HarvestActivity;
import com.example.wolf.land.myland;
import com.example.wolf.me_order.OrderActivity;
import com.example.wolf.seed.myseed;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class usersfarm extends AppCompatActivity {


    @BindView(R.id.c1)
    LinearLayout c1;
    @BindView(R.id.c2)
    LinearLayout c2;
    @BindView(R.id.c3)
    LinearLayout c3;
    @BindView(R.id.c4)
    LinearLayout c4;
    @BindView(R.id.c5)
    LinearLayout c5;
    @BindView(R.id.c6)
    LinearLayout c6;
    @BindView(R.id.framfh)
    ImageView framfh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersfarm);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.framfh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c1:
                Intent intent = new Intent(usersfarm.this, myland.class);
                startActivity(intent);
                break;
            case R.id.c2:
                Intent intent2 = new Intent(usersfarm.this, myseed.class);
                startActivity(intent2);
                break;
            case R.id.c3:
                Intent intent3 = new Intent(usersfarm.this, mycultivation.class);
                startActivity(intent3);
                break;
            case R.id.c4:
                Intent intent4 = new Intent(usersfarm.this, AtlasActivity.class);
                startActivity(intent4);
                break;
            case R.id.c5:
                Intent intent5 = new Intent(usersfarm.this, HarvestActivity.class);
                startActivity(intent5);
                break;
            case R.id.c6:
                Intent intent6 = new Intent(usersfarm.this, OrderActivity.class);
                startActivity(intent6);
                break;
            case R.id.framfh:
                finish();
                break;
        }
    }
}
