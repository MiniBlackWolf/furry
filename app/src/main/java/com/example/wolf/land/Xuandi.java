package com.example.wolf.land;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.adpater.xuandiadapter;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.view.annotation.ContentView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.xuandi)
public class Xuandi extends AppCompatActivity {
    Xutils xutils = new Xutils(Xuandi.this);
    @BindView(R.id.xuandifanhui)
    ImageView xuandifanhui;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.kuaishu)
    TextView kuaishu;
    @BindView(R.id.zhongjian)
    TextView zhongjian;
    @BindView(R.id.xuandirecyclerview)
    RecyclerView xuandirecyclerview;
    @BindView(R.id.q)
    ImageView q;
    @BindView(R.id.q4)
    ImageView q4;
    boolean Visibility=true;
    AlertDialog aldia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuandi);
        ButterKnife.bind(Xuandi.this);
        q.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Visibility) {
                    q4.setVisibility(View.VISIBLE);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(q4, "scaleX", 0f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(q4, "scaleY", 0f, 1f);
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(q4, "translationX", -200f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX, translationX, scaleY);
                    animatorSet.setDuration(500);
                    animatorSet.start();
                    Visibility = false;
                } else {
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(q4, "scaleX", 1f, 0f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(q4, "scaleY", 1f, 0f);
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(q4, "translationX", 200f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX, translationX, scaleY);
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation, boolean isReverse) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation, boolean isReverse) {
                            q4.setVisibility(View.GONE);
                        }
                    });
                    animatorSet.setDuration(500);
                    animatorSet.start();
                    Visibility = true;

                }
            }
        });
        ZloadingDiaLogkt zloadingDiaLogkt = new ZloadingDiaLogkt(Xuandi.this);
        final ZLoadingDialog ZLoadingDialog = zloadingDiaLogkt.show();
        xutils.get(getResources().getString(R.string.getlandinfo), new HashMap<String, String>(), new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<Farminfo> Farminfo = gsonUtil.Gson(result, Farminfo.class);
                xuandiadapter xuandiadapter = new xuandiadapter(R.layout.xuandiitem, Farminfo, Xuandi.this, buy);
                xuandiadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                xuandiadapter.isFirstOnly(false);
                xuandirecyclerview.setLayoutManager(new LinearLayoutManager(Xuandi.this));
                xuandirecyclerview.setAdapter(xuandiadapter);
                xuandiadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @SuppressWarnings("ConstantConditions")
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        TextView shuliang = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang);
                        TextView shuliang2 = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang2);
                        TextView shuliang3 = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang3);
                        switch (view.getId()) {

                            case R.id.jia:
                                String s = shuliang.getText().toString();
                                Integer sl1 = Integer.valueOf(s);
                                sl1++;
                                shuliang.setText(sl1 + "");
                                break;
                            case R.id.jia2:
                                String s2 = shuliang2.getText().toString();
                                Integer sl2 = Integer.valueOf(s2);
                                sl2++;
                                shuliang2.setText(sl2 + "");
                                break;
                            case R.id.jia3:
                                String s3 = shuliang3.getText().toString();
                                Integer sl3 = Integer.valueOf(s3);
                                sl3++;
                                shuliang3.setText(sl3 + "");
                                break;
                            case R.id.jian:
                                String ss = shuliang.getText().toString();
                                Integer ssl = Integer.valueOf(ss);
                                ssl--;
                                if (ssl <= 0) {
                                    ssl = 0;
                                }
                                shuliang.setText(ssl + "");
                                break;
                            case R.id.jian2:
                                String ss2 = shuliang2.getText().toString();
                                Integer ssl2 = Integer.valueOf(ss2);
                                ssl2--;
                                if (ssl2 <= 0) {
                                    ssl2 = 0;
                                }
                                shuliang2.setText(ssl2 + "");
                                break;
                            case R.id.jian3:
                                String ss3 = shuliang2.getText().toString();
                                Integer ssl3 = Integer.valueOf(ss3);
                                ssl3--;
                                if (ssl3 <= 0) {
                                    ssl3 = 0;
                                }
                                shuliang3.setText(ssl3 + "");
                                break;

                        }
                    }
                });
                xuandiadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        View views = getLayoutInflater().inflate(R.layout.xuandiinfoitem, null);
                        ImageView xuandiinfoimage=views.findViewById(R.id.xuandiinfoimage);
                        TextView xuandiinfotext = views.findViewById(R.id.xuandiinfotext);
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Xuandi.this);
                        alertDialog.setView(R.layout.xuandiinfoitem);
                        xuandiinfotext.setText("菜鸟农场的土地是以科学化管理为标准、“先废弃后开垦”为原则所培育的有机土地。现将土地分为多种规格，你可以根据自己的需求选择不同规格的土地进行种植。");
                        switch (position){
                            case 0:
                                xuandiinfoimage.setImageResource(R.mipmap.aa1);
                                alertDialog.setView(views);
                                aldia = alertDialog.show();
                                break;
                            case 1:
                                xuandiinfoimage.setImageResource(R.mipmap.aa2);
                                alertDialog.setView(views);
                                aldia = alertDialog.show();
                                break;
                            case 2:
                                xuandiinfoimage.setImageResource(R.mipmap.aa3);
                                alertDialog.setView(views);
                                aldia = alertDialog.show();
                                break;

                        }
                    }
                });

                ZLoadingDialog.dismiss();
            }
        });


        xuandifanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Xuandi.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
