package com.example.wolf.me.harvest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.wolf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HarvestActivity extends AppCompatActivity {


    @BindView(R.id.hafh)
    ImageView hafh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.harvest);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.hafh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0,0);
    }
}
