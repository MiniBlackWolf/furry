package com.example.wolf.pick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wolf.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.picklayout)
public class pick extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

    }
}
