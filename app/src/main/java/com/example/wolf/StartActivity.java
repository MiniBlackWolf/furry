package com.example.wolf;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StartActivity extends AppCompatActivity {


    @BindView(R.id.startimg2)
    ImageView startimg2;
    @BindView(R.id.startview)
    ConstraintLayout startview;
    AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        ButterKnife.bind(this);
        startview.setBackgroundResource(R.mipmap.index);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator o1 = ObjectAnimator.ofFloat(startimg2, "scaleX", 1f, 0f);
                ObjectAnimator o2 = ObjectAnimator.ofFloat(startimg2, "scaleY", 1f, 0f);
               ObjectAnimator o3 = ObjectAnimator.ofFloat(startimg2, "rotation", 0f, 360f);
//                ObjectAnimator o4 = ObjectAnimator.ofFloat(startimg2, "translationX", 1000f);
//                ObjectAnimator o5 = ObjectAnimator.ofFloat(startimg2, "translationY", -1000f);
                animatorSet = new AnimatorSet();
                animatorSet.setDuration(1500);
                animatorSet.playTogether(o1, o2,o3);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                    }
                });
                animatorSet.start();


            }
        }, 2000);

        //       startimg2.

    }

    @Override
    protected void onRestart() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0,0);
        super.onRestart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        animatorSet.cancel();
        super.onDestroy();
    }
}
