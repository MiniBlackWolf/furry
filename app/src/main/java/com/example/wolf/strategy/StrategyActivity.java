package com.example.wolf.strategy;

import android.os.Bundle;
import android.app.Activity;

import com.example.wolf.R;

import butterknife.ButterKnife;


public class StrategyActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategy);
        ButterKnife.bind(this);

    }

}
