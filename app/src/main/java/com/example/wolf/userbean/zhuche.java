package com.example.wolf.userbean;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wolf.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;
import android.support.v4.app.Fragment;

@ContentView(R.layout.zhuche)
public class zhuche extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
