package com.example.wolf.burse;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.UserInfo;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

@ContentView(R.layout.myburse_2)
public class Myburse2Fragment extends Fragment {
    @ViewInject(R.id.point)
    private TextView point;
    @ViewInject(R.id.getpoint)
    private TextView getpoint;
    Xutils xutils = new Xutils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(Myburse2Fragment.this, inflater, container);
        Map<String, String> map = new HashMap<>();
        map.put("userName", new Getuserinfo(getActivity()).getusername());
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
                GsonUtil gsonUtil = new GsonUtil();
                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
                point.setText(UserInfo.get(0).getScore() + "分");
            }
        });
        getpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myburselayout,new Myburse2PointFragment());
                fragmentTransaction.commit();
        }

        });

        return view;
    }


}
