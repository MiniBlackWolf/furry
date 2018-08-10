package com.example.wolf.atlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.adpater.Atlasadapter;


import java.util.LinkedList;

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
        Atlasadapter Atlasadapter=new Atlasadapter(R.layout.atlasitem,new LinkedList<Userseedimg>(),AtlasActivity.this);
        Atlasadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        Atlasadapter.isFirstOnly(false);
        atlasrecycler.setLayoutManager(new GridLayoutManager(AtlasActivity.this,2));
        atlasrecycler.setAdapter(Atlasadapter);
        Atlasadapter.bindToRecyclerView(atlasrecycler);
        Atlasadapter.setEmptyView(R.layout.loading);

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
