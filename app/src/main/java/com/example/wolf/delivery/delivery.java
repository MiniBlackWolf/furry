package com.example.wolf.delivery;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;

import com.example.wolf.R;

import org.xutils.view.annotation.ContentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ContentView(R.layout.delivery)
public class delivery extends AppCompatActivity {
    @BindView(R.id.vv1)
    View vv1;
    float toff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);
        ButterKnife.bind(this);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        toff = (float) (width / 2.2);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.xuandifanhui, R.id.pick2, R.id.d1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xuandifanhui:
                finish();
                break;
            case R.id.pick2:
                if(vv1.getTranslationX()==toff){
                    return;
                }
                ObjectAnimator translationX = ObjectAnimator.ofFloat(vv1, "translationX", vv1.getTranslationX(), toff);
                translationX.setDuration(500);
                translationX.start();
                break;
            case R.id.d1:
                ObjectAnimator translationXx = ObjectAnimator.ofFloat(vv1,"translationX",vv1.getTranslationX(),0f);
                translationXx.setDuration(500);
                translationXx.start();
                break;
        }
    }
}
