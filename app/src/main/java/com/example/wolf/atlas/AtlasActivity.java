package com.example.wolf.atlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.wolf.R;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtlasActivity extends AppCompatActivity {


    @BindView(R.id.atlasfh)
    ImageView atlasfh;
    @BindView(R.id.atlasrecycler)
    RecyclerView atlasrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atlas);
        ButterKnife.bind(this);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

    @OnClick(R.id.atlasfh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, 0);
    }
}
