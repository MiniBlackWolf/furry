package com.example.wolf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.Loginpage;
import com.example.wolf.userbean.UserBase;
import com.example.wolf.userbean.zhuche;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@ContentView(R.layout.tab5)
public class tab5 extends Fragment {
    @BindView(R.id.m4_4)
    LinearLayout m44;
    @BindView(R.id.m5_4)
    LinearLayout m54;
    @BindView(R.id.m6_4)
    LinearLayout m64;
    @BindView(R.id.m7_4)
    LinearLayout m74;
    @BindView(R.id.m1_4)
    ImageView m14;
    @BindView(R.id.m2_4)
    ImageView m24;
    @BindView(R.id.m3_4)
    ImageView m34;
    Unbinder unbinder;
    @ViewInject(R.id.denglu)
    private Button denglu;
    @ViewInject(R.id.zhuche2)
    private Button zhuche;
    Xutils xutils = new Xutils(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(tab5.this, inflater, container);
        //     http();
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Loginpage.class);
                startActivity(intent);
            }
        });
        zhuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Loginpage.class);
                startActivity(intent);
            }
        });
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void http() {
        Map map = new HashMap();
//        xutils.post(getResources().getString(R.string.Login), map, new Xutils.XCallBack() {
//            @Override
//            public void onResponse(String result) {
//                //  Log.i("iiiiiiiiiiiii",result);
//            }
//        });
        xutils.get(getResources().getString(R.string.Users), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {

                algorithm algorithm = new algorithm();
                try {
                    String userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");
                    GsonUtil gsonUtil = new GsonUtil();
                    List<UserBase> list = gsonUtil.Gson(userinfo, UserBase.class);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.m4_4, R.id.m5_4, R.id.m6_4, R.id.m7_4, R.id.m1_4, R.id.m2_4, R.id.m3_4,R.id.m13_4,R.id.m14_4,R.id.myinfo})
    public void onViewClicked(View view) {
        if(view.getId()==R.id.myinfo){
            Intent intent = new Intent(getActivity(), Loginpage.class);
            startActivity(intent);

        }
        Intent intent = new Intent(getActivity(), Loginpage.class);
        startActivity(intent);

    }
}
