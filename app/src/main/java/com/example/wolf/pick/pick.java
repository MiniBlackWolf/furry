package com.example.wolf.pick;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wolf.R;

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
    int trustoff;
    float off;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picklayout);
        ButterKnife.bind(this);
        Window window = getWindow();
        WindowManager windowManager = window.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        trustoff = (int) (width / 2.3);
        off = view3.getX();


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
                ObjectAnimator1(trustoff * 2);
                break;
            case R.id.pick2:
                ObjectAnimator2();
                break;
            case R.id.pick1:
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
