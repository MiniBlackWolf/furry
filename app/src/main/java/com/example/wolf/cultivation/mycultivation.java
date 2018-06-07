package com.example.wolf.cultivation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.adpater.Mycultivationadapter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class mycultivation extends AppCompatActivity {

    @BindView(R.id.cufh)
    ImageView mygyfh;
    @BindView(R.id.cugenyun)
    LinearLayout cugenyun;
    @BindView(R.id.culingping)
    LinearLayout culingping;
    @BindView(R.id.curecyclerview)
    RecyclerView curecyclerview;
    Xutils xutils = new Xutils();
    Getuserinfo getuserinfo = new Getuserinfo(mycultivation.this);
    @BindView(R.id.full)
    ConstraintLayout full;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycultivation);
        ButterKnife.bind(this);

        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        xutils.get(getResources().getString(R.string.getTickets), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                List<usercultivation> usercultivation = gsonUtil.Gson(result, usercultivation.class);
                Mycultivationadapter mycultivationadapter = new Mycultivationadapter(R.layout.mycultivationitem, usercultivation, mycultivation.this);
                mycultivationadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mycultivationadapter.isFirstOnly(false);
                mycultivationadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (position) {
                            case 0: {

                                break;
                            }
                            case 1: {

                                break;
                            }
                            case 2: {

                                break;
                            }
                            case 3: {

                                break;
                            }
                            case 4: {

                                break;
                            }

                        }
                    }
                });
                curecyclerview.setLayoutManager(new LinearLayoutManager(mycultivation.this));
                curecyclerview.setAdapter(mycultivationadapter);
            }
        });
    }

    @OnClick(R.id.cufh)
    public void onViewClicked() {
        finish();
        ButterKnife.bind(this);
        overridePendingTransition(0, 0);
    }
}
