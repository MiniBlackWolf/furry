package com.example.wolf.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.wolf.Utils.GlideCircleTransform;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.burse.MyburseActivity;
import com.example.wolf.invite.InviteActivity;
import com.example.wolf.strategy.StrategyActivity;
import com.example.wolf.userbean.MyinfoActivity;
import com.example.wolf.userbean.UserInfo;
import com.example.wolf.userbean.usersfarm;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.tab5_2)
public class tab5_2 extends Fragment {
    @ViewInject(R.id.zhuxiao)
    private Button zhuxiao;
    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.m1_3)
    private ImageView m1;
    @ViewInject(R.id.m2_3)
    private ImageView m2;
    @ViewInject(R.id.m3_3)
    private ImageView m3;
    @ViewInject(R.id.m4_4)
    private LinearLayout m4_4;
    @ViewInject(R.id.m14_4)
    private LinearLayout m14_4;
    @ViewInject(R.id.m7_4)
    private LinearLayout m7_4;
    @ViewInject(R.id.m5_4)
    private LinearLayout m5_4;
    @ViewInject(R.id.m6_4)
    private LinearLayout m6_4;
    @ViewInject(R.id.m13_4)
    private LinearLayout m13_4;
    Xutils xutils = new Xutils(getActivity());
    private AlertDialog alertDialog;

    //    @ViewInject(R.id.myinfo22)
//    private ImageView myinfo2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(tab5_2.this, inflater, container);
        final RequestManager RequestManager = Glide.with(getActivity());
        final ImageView myinfo2 = view.findViewById(R.id.myinfo22);
        //注销方法
        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mySharePerferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
                mySharePerferences.edit().clear().apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("zhuxiao", "zhuxiao");
                startActivity(intent);

            }
        });
        SharedPreferences mySharePerferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        String userName = mySharePerferences.getString("userName", "");
        Map map = new HashMap();
        map.put("userName", userName);
        xutils.get(getResources().getString(R.string.Userinfo), map, new Xutils.XCallBack() {
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
                name.setText(UserInfo.get(0).getName());
                Log.i("iiiiiiiiiii", UserInfo.get(0).getHeadimgurl() + " ");
                RequestManager
                        .load(UserInfo.get(0).getHeadimgurl())
                        .placeholder(R.mipmap.load)
                        .transform(new GlideCircleTransform(getActivity()))
                        .into(myinfo2);
            }
        });
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), usersfarm.class);
                startActivity(intent);
            }
        });
        //钱包
        m4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyburseActivity.class);
                startActivity(intent);
            }
        });
        //邀请好友
        m14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InviteActivity.class);
                startActivity(intent);
            }
        });
        //种植攻略
        m7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StrategyActivity.class);
                startActivity(intent);
            }
        });
        //修改我的信息
        myinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyinfoActivity.class);
                startActivity(intent);
            }
        });
        //光伏理财
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putoff();
            }
        });
        //旅游产品
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putoff();
            }
        });
        //我的物流
        m5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putoff();
            }
        });
        //积分商城
        m6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putoff();
            }
        });
        //联系客服
        m13_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putoff();
            }
        });
//        Map<String, String> map2 = new HashMap<>();
//        map.put("userName", new Getuserinfo(getActivity()).getusername());
//        xutils.get(getResources().getString(R.string.Userinfo), map2, new Xutils.XCallBack() {
//            @Override
//            public void onResponse(String result) {
//                String userinfo = null;
//                try {
//                    userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                GsonUtil gsonUtil = new GsonUtil();
//                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
//                Glide.with(getActivity()).load(UserInfo.get(0).getHeadimgurl()).placeholder(R.mipmap.load).transform(new GlideCircleTransform(getActivity())).into(myinfo2);
//            }
//        });
        return view;
    }

    private void putoff() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("警告！");
        builder.setMessage("此功能正在建设中");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }
}
