package com.example.wolf.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.adpater.Myvideoadapter;
import com.example.wolf.cultivation.genyun;
import com.example.wolf.delivery.delivery;
import com.example.wolf.land.Xuandi;
import com.example.wolf.land.userland;
import com.example.wolf.pick.pick;
import com.example.wolf.seed.seedActivity;
import com.example.wolf.strategy.StrategyActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.tab2)
public class tab2 extends Fragment {
    @ViewInject(R.id.slider)
    private SliderLayout mDemoSlider1;
    @ViewInject(R.id.s1)
    private ImageView xuan;
    @ViewInject(R.id.s2)
    private ImageView s2;
    @ViewInject(R.id.s3)
    private ImageView s3;
    @ViewInject(R.id.s4)
    private ImageView s4;
    @ViewInject(R.id.s5)
    private ImageView s5;
    @ViewInject(R.id.s6)
    private ImageView s6;
    @ViewInject(R.id.tab2cardview)
    CardView tab2cardview;
    List<userland> list = new ArrayList<>();
    int uid;
    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(tab2.this, inflater, container);
        Myvideoadapter myvideoadapter = new Myvideoadapter(R.layout.videoitem, list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        uid = new Getuserinfo(getActivity()).getuid();
        slider(view);
        addonclick();
        MediaController mc = new MediaController(getActivity());
        mc.setVisibility(View.INVISIBLE);
        tab2cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StrategyActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void addonclick() {
        xuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Xuandi.class);
                startActivity(intent);


            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), seedActivity.class);
                startActivity(intent);

            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), genyun.class);
                startActivity(intent);


            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid == 0) {
                    ToastUtils.showToast(getActivity(), "请先登录");
                } else {
                    Intent intent = new Intent(getActivity(), pick.class);
                    startActivity(intent);
                }

            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid == 0) {
                    ToastUtils.showToast(getActivity(), "请先登录");
                } else {
                    Intent intent = new Intent(getActivity(), delivery.class);
                    startActivity(intent);
                }

            }


        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("警告！");
                builder.setMessage("此功能正在建设中！");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }

        });
    }

    private void slider(View view) {
        HashMap<String, Integer> maps = new HashMap<>();
        maps.put("", R.mipmap.possss);
        maps.put(" ", R.mipmap.b1);
        maps.put("  ", R.mipmap.b2_1);
        maps.put("   ", R.mipmap.b2_2);
        for (String name : maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    //    .description(name)
                    .image(maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Log.i("iiiiii", slider.toString());
                        }
                    });

            //add your extra information 点击图片时可用到
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider1.addSlider(textSliderView);
        }
        // 设置默认过渡效果(可在xml中设置)
        mDemoSlider1.setPresetTransformer(SliderLayout.Transformer.Stack);
        // 设置默认指示器位置(默认指示器白色,位置在sliderlayout底部)
        mDemoSlider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        // 设置自定义指示器(位置自定义)
        mDemoSlider1.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator2));
        // 设置TextView自定义动画
        mDemoSlider1.setCustomAnimation(new DescriptionAnimation());

        //  mDemoSlider1.setCustomAnimation(new DescriptionAnimation()); // 多种效果，进入该类修改，参考效果github/daimajia/AndroidViewAnimations
        // 设置持续时间
        mDemoSlider1.setDuration(2000);
    }
}
