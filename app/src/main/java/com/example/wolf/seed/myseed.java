package com.example.wolf.seed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.adpater.Myseedadapter;
import com.example.wolf.adpater.myseedadapter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class myseed extends AppCompatActivity {
    @BindView(R.id.seedfh)
    ImageView seedfh;
    @BindView(R.id.myseedrecycler)
    RecyclerView myseedrecycler;
    @BindView(R.id.usev)
    View usev;
    @BindView(R.id.use)
    LinearLayout use;
    @BindView(R.id.nouserv)
    View nouserv;
    @BindView(R.id.nouse)
    LinearLayout nouse;
    @BindView(R.id.overduev)
    View overduev;
    @BindView(R.id.overdue)
    LinearLayout overdue;
    private Xutils xutils = new Xutils();
    private Getuserinfo getuserinfo = new Getuserinfo(myseed.this);
    private List<seedbean> userseed;
    Myseedadapter myseedadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myseed);
        ButterKnife.bind(this);
        Map<String, String> map = new HashMap();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        map.put("token", new Token().getToken(getuserinfo.getuid()));
        xutils.get(getResources().getString(R.string.getUserUseSeed), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                userseed = gsonUtil.Gson(result, seedbean.class);
                setadapter(userseed);
            }

        });
    }

    private void setadapter(List<seedbean> list) {
        myseedadapter = new Myseedadapter(R.layout.myseeditem, list, myseed.this);
        myseedadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        myseedadapter.isFirstOnly(false);
        myseedrecycler.setLayoutManager(new GridLayoutManager(myseed.this, 2));
        myseedrecycler.setAdapter(myseedadapter);
        myseedadapter.notifyDataSetChanged();
    }

    @OnClick(R.id.seedfh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, 0);
    }

    @OnClick({R.id.use, R.id.nouse, R.id.overdue})
    public void onViewClicked(View view) {
        usev.setBackgroundColor(getResources().getColor(R.color.toum));
        nouserv.setBackgroundColor(getResources().getColor(R.color.toum));
        overduev.setBackgroundColor(getResources().getColor(R.color.toum));
        switch (view.getId()) {
            case R.id.use:
                setadapter(userseed);
                usev.setBackgroundColor(getResources().getColor(R.color.hong));
                break;
            case R.id.nouse:
                Map<String,String> map=new HashMap();
                map.put("uid", String.valueOf(getuserinfo.getuid()));
                map.put("token",new Token().getToken(getuserinfo.getuid()));
                map.put("getMethod","1");
                xutils.get(getResources().getString(R.string.getUserRemainSeed), map, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        GsonUtil gsonUtil=new GsonUtil();
                        List<userseed> userseed = gsonUtil.Gson(result, userseed.class);
                        myseedadapter2 myseedadapter2=new myseedadapter2(R.layout.myseeditem,userseed,myseed.this);
                        myseedadapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        myseedadapter2.isFirstOnly(false);
                        myseedrecycler.setLayoutManager(new GridLayoutManager(myseed.this, 2));
                        myseedrecycler.setAdapter(myseedadapter2);
                        myseedadapter2.notifyDataSetChanged();
                    }
                });
                nouserv.setBackgroundColor(getResources().getColor(R.color.hong));
                break;
            case R.id.overdue:
                Map<String,String> map2=new HashMap();
                map2.put("uid", String.valueOf(getuserinfo.getuid()));
                map2.put("token",new Token().getToken(getuserinfo.getuid()));
                map2.put("getMethod","2");
                xutils.get(getResources().getString(R.string.getUserRemainSeed), map2, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        GsonUtil gsonUtil=new GsonUtil();
                        List<userseed> userseed = gsonUtil.Gson(result, userseed.class);
                        myseedadapter2 myseedadapter2=new myseedadapter2(R.layout.myseeditem,userseed,myseed.this);
                        myseedadapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        myseedadapter2.isFirstOnly(false);
                        myseedrecycler.setLayoutManager(new GridLayoutManager(myseed.this, 2));
                        myseedrecycler.setAdapter(myseedadapter2);
                        myseedadapter2.notifyDataSetChanged();
                    }
                });
                overduev.setBackgroundColor(getResources().getColor(R.color.hong));
                break;
        }
    }
}