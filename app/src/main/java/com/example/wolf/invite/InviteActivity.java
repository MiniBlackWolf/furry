package com.example.wolf.invite;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZXingUtils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.UserInfo;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteActivity extends Activity {


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
    private boolean isOpen = true;
    Xutils xutils = new Xutils(InviteActivity.this);
    private static final String APP_ID = "wx3743a302c699ab6b";
    private IWXAPI api;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite);
        ButterKnife.bind(this);
        //微信app注册
        api= WXAPIFactory.createWXAPI(InviteActivity.this,APP_ID,true);
        api.registerApp(APP_ID);
        //----
        invitetext.setAlpha(0f);
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(InviteActivity.this).getuid()));
        map.put("token", new Token().getToken(new Getuserinfo(InviteActivity.this).getuid()));
        xutils.get(getResources().getString(R.string.recommended), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                Log.i("iiiiiiiii", i);
                bitmap = ZXingUtils.createQRImage(i, weima.getWidth(), weima.getHeight());
                weima.setImageBitmap(bitmap);
            }
        });
        Map<String, String> map2 = new HashMap<>();
        map.put("userName", new Getuserinfo(InviteActivity.this).getusername());
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
                Log.i("iiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
                invitescore.setText(UserInfo.get(0).getScore() + "分");
                invitepople.setText(UserInfo.get(0).getPeople() + "人");

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    @OnClick({R.id.more, R.id.invitefh, R.id.QQ, R.id.wex, R.id.wexfd})
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
            case R.id.QQ:
                break;
            case R.id.wex:
                WXImageObject wxImageObject=new WXImageObject(bitmap);
                WXMediaMessage wxMediaMessage=new WXMediaMessage();
                wxMediaMessage.mediaObject=wxImageObject;
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
                bitmap.recycle();
                wxMediaMessage.thumbData =bmpToByteArray(scaledBitmap,true);
                SendMessageToWX.Req req=new SendMessageToWX.Req();
                req.transaction= buildTransaction("img");
                req.message=wxMediaMessage;
                req.scene = SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);

                break;
            case R.id.wexfd:
                break;
        }
    }
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0,i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                //F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
