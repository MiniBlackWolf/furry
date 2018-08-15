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
import android.util.Log;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.xuandi)
public class Xuandi extends AppCompatActivity {
    Xutils xutils = new Xutils(Xuandi.this);
    @BindView(R.id.xuandifanhui)
    ImageView xuandifanhui;
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
                if (isRiskMove) {
                    isRiskMove = false;
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
                xuandiadapter xuandiadapter = new xuandiadapter(R.layout.xuandiitem, Farminfo, Xuandi.this);
                xuandiadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                xuandiadapter.isFirstOnly(false);
                xuandirecyclerview.setLayoutManager(new LinearLayoutManager(Xuandi.this));
                xuandirecyclerview.setAdapter(xuandiadapter);
                xuandiadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @SuppressWarnings("ConstantConditions")
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        TextView landname = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.landname);
                        TextView landcount = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.landcount);
                        Pattern compile = Pattern.compile("A");
                        Matcher matcher = compile.matcher(landname.getText().toString());
                        if (matcher.find()) {
                            xuandidailogfragment xuandidailogfragment = new xuandidailogfragment();
                            Bundle bundle=new Bundle();
                            bundle.putInt("lastcount", Integer.valueOf(landcount.getText().toString()));
                            xuandidailogfragment.setArguments(bundle);
                            xuandidailogfragment.show(getSupportFragmentManager(), "A");
                        }
                        Pattern compile2 = Pattern.compile("B");
                        Matcher matcher2 = compile2.matcher(landname.getText().toString());
                        if (matcher2.find()) {
                            xuandidailogfragment xuandidailogfragment = new xuandidailogfragment();
                            Bundle bundle=new Bundle();
                            bundle.putInt("lastcount", Integer.valueOf(landcount.getText().toString()));
                            xuandidailogfragment.setArguments(bundle);
                            xuandidailogfragment.show(getSupportFragmentManager(), "B");
                        }
                        Pattern compile3 = Pattern.compile("C");
                        Matcher matcher3 = compile3.matcher(landname.getText().toString());
                        if (matcher3.find()) {
                            xuandidailogfragment xuandidailogfragment = new xuandidailogfragment();
                            Bundle bundle=new Bundle();
                            bundle.putInt("lastcount", Integer.valueOf(landcount.getText().toString()));
                            xuandidailogfragment.setArguments(bundle);
                            xuandidailogfragment.show(getSupportFragmentManager(), "C");
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

    @Override
    protected void onResume() {
        Log.i("iiiiiiiiiiiiiii","onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("iiiiiiiiiiiiiii","onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("iiiiiiiiiiiiiii","onPause");
        super.onPause();
    }
}