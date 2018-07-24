package com.example.wolf.pick;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.adpater.pickadapter;
import com.example.wolf.land.userseedandland;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class pick extends AppCompatActivity {

    @BindView(R.id.pickfanhui)
    ImageView pickfanhui;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.pick3)
    LinearLayout pick3;
    @BindView(R.id.pick2)
    LinearLayout pick2;
    @BindView(R.id.pick1)
    LinearLayout pick1;
    @BindView(R.id.pickrecyclerview)
    RecyclerView pickrecyclerview;
    @BindView(R.id.pickbutton)
    Button pickbutton;
    int trustoff;
    Xutils xutils = new Xutils(pick.this);
    List<userseedandland> aboutpick = new ArrayList<>();
    List<userseedandland> nowpick = new ArrayList<>();
    List<userseedandland> Grow = new ArrayList<>();
    pickadapter pickadapter;
    ZLoadingDialog show;



    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picklayout);
        ButterKnife.bind(this);
        ZloadingDiaLogkt zloadingDiaLogkt = new ZloadingDiaLogkt(pick.this);
        show = zloadingDiaLogkt.show();
        Window window = getWindow();
        WindowManager windowManager = window.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        trustoff = (int) (width / 2.3);
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(pick.this).getuid()));
        xutils.get(getResources().getString(R.string.getuserseed), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                @SuppressWarnings("unchecked") List<userseedandland> userseedandlands = gsonUtil.Gson(result, userseedandland.class);
                long l = System.currentTimeMillis() / 1000;
                for (userseedandland us : userseedandlands) {
                    long time = (l - us.getSwoingdate()) / 60 / 60 / 24;
                    if (us.getGrowDate() - time < 10 && us.getGrowDate() - time > 0) {
                        aboutpick.add(us);
                    } else if (us.getGrowDate() - time < 0) {
                        nowpick.add(us);
                    } else {
                        Grow.add(us);
                    }

                }
                pickadapter = new pickadapter(R.layout.pickitem, nowpick, pick.this,pickbutton);
                pickadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ImageView pickhand = (ImageView) adapter.getViewByPosition(pickrecyclerview, position, R.id.pickhand);
                        assert pickhand != null;
                        int visibility = pickhand.getVisibility();
                        if (visibility == View.GONE) {
                            pickhand.setVisibility(View.VISIBLE);
                        } else {

                            pickhand.setVisibility(View.GONE);
                        }
                    }
                });
                pickadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                pickadapter.isFirstOnly(false);
                pickrecyclerview.setLayoutManager(new LinearLayoutManager(pick.this));
                pickrecyclerview.setAdapter(pickadapter);
                pickadapter.bindToRecyclerView(pickrecyclerview);
                pickadapter.setEmptyView(R.layout.loading);
                show.dismiss();

            }
        });

    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();

    }

    @OnClick({R.id.pickfanhui, R.id.pick3, R.id.pick2, R.id.pick1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pickfanhui:
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.pick3:
                pickbutton.setVisibility(View.GONE);
                pickadapter = new pickadapter(R.layout.pickitem2, Grow, pick.this,pickbutton);
                pickadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                pickadapter.isFirstOnly(false);
                pickrecyclerview.setLayoutManager(new LinearLayoutManager(pick.this));
                pickrecyclerview.setAdapter(pickadapter);
                pickadapter.bindToRecyclerView(pickrecyclerview);
                pickadapter.setEmptyView(R.layout.loading);
                ObjectAnimator1(trustoff * 2);
                break;
            case R.id.pick2:
                pickbutton.setVisibility(View.GONE);
                pickadapter = new pickadapter(R.layout.pickitem2, aboutpick, pick.this,pickbutton);
                pickadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                pickadapter.isFirstOnly(false);
                pickrecyclerview.setLayoutManager(new LinearLayoutManager(pick.this));
                pickrecyclerview.setAdapter(pickadapter);
                pickadapter.bindToRecyclerView(pickrecyclerview);
                pickadapter.setEmptyView(R.layout.loading);
                ;
                ObjectAnimator2();
                break;
            case R.id.pick1:
                pickbutton.setVisibility(View.VISIBLE);
                pickadapter = new pickadapter(R.layout.pickitem, nowpick, pick.this,pickbutton);
                pickadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ImageView pickhand = (ImageView) adapter.getViewByPosition(pickrecyclerview, position, R.id.pickhand);
                        assert pickhand != null;
                        int visibility = pickhand.getVisibility();
                        if (visibility == View.GONE) {
                            pickhand.setVisibility(View.VISIBLE);
                        } else {

                            pickhand.setVisibility(View.GONE);
                        }
                    }
                });
                pickadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                pickadapter.isFirstOnly(false);
                pickrecyclerview.setLayoutManager(new LinearLayoutManager(pick.this));
                pickrecyclerview.setAdapter(pickadapter);
                pickadapter.bindToRecyclerView(pickrecyclerview);
                pickadapter.setEmptyView(R.layout.loading);
                ObjectAnimator1(0f);
                break;

        }
    }

    private void ObjectAnimator1(float v) {
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view3, "translationX", view3.getTranslationX(), v);
        objectAnimator3.setDuration(500);
        objectAnimator3.start();
    }

    private void ObjectAnimator2() {
        if (view3.getTranslationX() > trustoff) {
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view3, "translationX", view3.getTranslationX(), trustoff);
            objectAnimator2.setDuration(500);
            objectAnimator2.start();
            return;
        }
        if (view3.getTranslationX() == trustoff) {
            return;
        }
        ObjectAnimator1(trustoff + view3.getX());
    }
}
