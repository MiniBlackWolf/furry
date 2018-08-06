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
import android.view.MotionEvent;
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
    boolean Visibility = true;
    AlertDialog aldia;
    double allmoney;
    int allcount;
    private boolean isRiskMove;
    private int mRiskLastX;
    private int mRiskLastY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuandi);
        ButterKnife.bind(Xuandi.this);
        q.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        isRiskMove = true;
                        //计算距离上次移动了多远
                        int deltaX = x - mRiskLastX;
                        int deltaY = y - mRiskLastY;
                        int translationX = (int) (q.getTranslationX() + deltaX);
                        int translationY = (int) (q.getTranslationY() + deltaY);
                        //使mFloatRiskBtn根据手指滑动平移
                        q.setTranslationX(translationX);
                        q.setTranslationY(translationY);
                        break;
                    case MotionEvent.ACTION_UP:
                        //平移回到该view水平方向的初始点
                        q.setTranslationX(0);
                        //判断什么情况下需要回到原点
//                        if(q.getY()<0 || q.getY()>(q.getMeasuredHeight()-q.getMeasuredHeight())) {
//                            q.setTranslationY(0);
//                        }
                        break;
                    default:
                        break;
                }
                //记录上次手指离开时的位置
                mRiskLastX = x;
                mRiskLastY = y;
                return false;
            }
        });
        q.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRiskMove){
                    isRiskMove=false;
                    return;
                }
                if (Visibility) {
                    q4.setY(q.getY());
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
                                allmoney += 120;
                                allcount++;
                                zhongjian.setText(allmoney + "");
                                kuaishu.setText(allcount + "");
                                shuliang.setText(sl1 + "");
                                break;
                            case R.id.jia2:
                                String s2 = shuliang2.getText().toString();
                                Integer sl2 = Integer.valueOf(s2);
                                sl2++;
                                allcount++;
                                allmoney += 180;
                                zhongjian.setText(allmoney + "");
                                kuaishu.setText(allcount + "");
                                shuliang2.setText(sl2 + "");
                                break;
                            case R.id.jia3:
                                String s3 = shuliang3.getText().toString();
                                Integer sl3 = Integer.valueOf(s3);
                                sl3++;
                                allmoney += 240;
                                allcount++;
                                zhongjian.setText(allmoney + "");
                                kuaishu.setText(allcount + "");
                                shuliang3.setText(sl3 + "");
                                break;
                            case R.id.jian:
                                String ss = shuliang.getText().toString();
                                Integer ssl = Integer.valueOf(ss);
                                ssl--;
                                if (ssl < 0) {
                                    ssl = 0;
                                    shuliang.setText(ssl + "");
                                } else {
                                    allmoney -= 120;
                                    allcount--;
                                    shuliang.setText(ssl + "");
                                    zhongjian.setText(allmoney + "");
                                    kuaishu.setText(allcount + "");

                                }


                                break;
                            case R.id.jian2:
                                String ss2 = shuliang2.getText().toString();
                                Integer ssl2 = Integer.valueOf(ss2);
                                ssl2--;
                                if (ssl2 < 0) {
                                    ssl2 = 0;
                                    shuliang2.setText(ssl2 + "");
                                } else {
                                    allmoney -= 180;
                                    allcount--;
                                    shuliang2.setText(ssl2 + "");
                                    zhongjian.setText(allmoney + "");
                                    kuaishu.setText(allcount + "");

                                }
                                break;
                            case R.id.jian3:
                                String ss3 = shuliang3.getText().toString();
                                Integer ssl3 = Integer.valueOf(ss3);
                                ssl3--;
                                if (ssl3 < 0) {
                                    ssl3 = 0;
                                    shuliang3.setText(ssl3 + "");
                                } else {
                                    allmoney -= 240;
                                    allcount--;
                                    shuliang3.setText(ssl3 + "");
                                    zhongjian.setText(allmoney + "");
                                    kuaishu.setText(allcount + "");
                                }
                                break;
                            case R.id.imageland:
                                View views = getLayoutInflater().inflate(R.layout.xuandiinfoitem, null);
                                ImageView xuandiinfoimage = views.findViewById(R.id.xuandiinfoimage);
                                TextView xuandiinfotext = views.findViewById(R.id.xuandiinfotext);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Xuandi.this);
                                alertDialog.setView(views);
                                xuandiinfotext.setText("菜鸟农场的土地是以科学化管理为标准、“先废弃后开垦”为原则所培育的有机土地。现将土地分为多种规格，你可以根据自己的需求选择不同规格的土地进行种植。");
                                switch (position) {
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
                                break;

                        }

                    }
                });

                xuandifanhui.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Xuandi.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                ZLoadingDialog.dismiss();
            }


        });
    }
}