package com.example.wolf.userbean;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.Utils.Xutils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Loginpage extends AppCompatActivity {

    Xutils xutils = new Xutils();
    @BindView(R.id.accountview)
    View accountview;
    @BindView(R.id.accountlogin)
    LinearLayout accountlogin;
    @BindView(R.id.phoneview)
    View phoneview;
    @BindView(R.id.phonelogin)
    LinearLayout phonelogin;
    @BindView(R.id.fanhui)
    ImageView fanhui;
    FragmentManager fragmentManager;
    PhoneloginFragment f1 = new PhoneloginFragment();
    LoginZhnaghaoFragment f2 = new LoginZhnaghaoFragment();
    @BindView(R.id.zhuche)
    Button zhuche;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.accountfragment, f2);
        fragmentTransaction.commit();
    }

    @OnClick({R.id.accountview, R.id.accountlogin, R.id.fanhui,R.id.phonelogin,R.id.zhuche})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.accountlogin:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.accountfragment, f2);
                fragmentTransaction.commit();
                accountview.setBackgroundColor(Color.WHITE);
                phoneview.setBackgroundColor(0x000000f);
                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.phonelogin:
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.accountfragment, f1);
                fragmentTransaction2.commit();
                phoneview.setBackgroundColor(Color.WHITE);
                accountview.setBackgroundColor(0x000000f);
                break;
            case R.id.zhuche:
                Intent intent=new Intent(Loginpage.this,LoginZhucheActivity.class);
                startActivity(intent);
                break;
        }
    }

}
