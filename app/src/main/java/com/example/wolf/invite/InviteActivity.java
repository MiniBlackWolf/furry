package com.example.wolf.invite;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZXingUtils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteActivity extends Activity  {


    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.invitefh)
    ImageView invitefh;
    @BindView(R.id.textView41)
    TextView textView41;
    @BindView(R.id.invitetext)
    ImageView invitetext;
    @BindView(R.id.weima)
    ImageView weima;
    @BindView(R.id.invitescore)
    TextView invitescore;
    @BindView(R.id.invitepople)
    TextView invitepople;
    @BindView(R.id.view15)
    View view15;
    private int mLayoutHeight = 0;
    private boolean isOpen = true;
    Xutils xutils = new Xutils(InviteActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite);
        ButterKnife.bind(this);
        invitetext.setAlpha(0f);
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(InviteActivity.this).getuid()));
        map.put("token", new Token().getToken(new Getuserinfo(InviteActivity.this).getuid()));
        xutils.get(getResources().getString(R.string.recommended), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                Log.i("iiiiiiiii", i);
                Bitmap bitmap = ZXingUtils.createQRImage(i, weima.getWidth(), weima.getHeight());
                weima.setImageBitmap(bitmap);
            }
        });
        Map<String, String> map2 = new HashMap<>();
        map.put("userName",new Getuserinfo(InviteActivity.this).getusername() );
        xutils.get(getResources().getString(R.string.Userinfo), map, new Xutils.XCallBack() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String result) {
                String userinfo = null;
                try {
                    userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.i("iiiiiiiii",result);
                GsonUtil gsonUtil = new GsonUtil();
                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
                invitescore.setText(UserInfo.get(0).getScore() + "分");
                invitepople.setText(UserInfo.get(0).getPeople()+"人");

            }
        });
    }

    @OnClick({R.id.more, R.id.invitefh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more:
                if (isOpen) {
                    invitetext.setVisibility(View.VISIBLE);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(more, "translationY", 0f, 700f);
                    ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(more, "rotation", 0f, 180f);
                    ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(invitetext, "alpha", 0f, 1f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator, objectAnimator2, objectAnimator6);
                    animatorSet.setDuration(1000);
                    animatorSet.start();
                    isOpen = false;


                } else {
                    ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(more, "translationY", 700f, 0f);
                    ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(more, "rotation", 180f, 0f);
                    ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(invitetext, "alpha", 1f, 0f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator3, objectAnimator4, objectAnimator5);
                    animatorSet.setDuration(1000);
                    animatorSet.start();
                    animatorSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            invitetext.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    isOpen = true;
                }

//
                break;
            case R.id.invitefh:
                finish();
                overridePendingTransition(0, 0);
                break;
        }
    }



}
