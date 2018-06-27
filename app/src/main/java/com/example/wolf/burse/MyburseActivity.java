package com.example.wolf.burse;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wolf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyburseActivity extends AppCompatActivity {


    @BindView(R.id.moneyv)
    View moneyv;
    @BindView(R.id.mymoney)
    LinearLayout mymoney;
    @BindView(R.id.moneyv2)
    View moneyv2;
    @BindView(R.id.mypoint)
    LinearLayout mypoint;
    Myburse1Fragment f1 = new Myburse1Fragment();
    Myburse2Fragment f2=new Myburse2Fragment();
    FragmentManager FragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myburse);
        ButterKnife.bind(this);
        FragmentManager = getSupportFragmentManager();
      fragmentTransaction = FragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.myburselayout,f1);
        fragmentTransaction.add(R.id.myburselayout,f2);
        fragmentTransaction.hide(f2);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
    @OnClick({R.id.mymoney, R.id.mypoint,R.id.bursefh})
    public void onViewClicked(View view) {
        moneyv.setBackgroundColor(getResources().getColor(R.color.toum));
        moneyv2.setBackgroundColor(getResources().getColor(R.color.toum));
        switch (view.getId()) {
            case R.id.mymoney:
                fragmentTransaction= FragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myburselayout,f1);
                fragmentTransaction.commit();
                moneyv.setBackgroundColor(getResources().getColor(R.color.hong));
                break;
            case R.id.mypoint:
                fragmentTransaction= FragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myburselayout,f2);
                fragmentTransaction.commit();
                moneyv2.setBackgroundColor(getResources().getColor(R.color.hong));
                break;
            case R.id.bursefh:
            finish();
            overridePendingTransition(0,0);
                break;
        }
    }
}
