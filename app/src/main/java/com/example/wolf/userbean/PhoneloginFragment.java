package com.example.wolf.userbean;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.OnClick;


@ContentView(R.layout.phonelogin)
public class PhoneloginFragment extends Fragment {
    @ViewInject(R.id.plyfasongyzm)
    private Button plyfasongyzm;
    @ViewInject(R.id.phonelp)
    private Button phonelp;
    @ViewInject(R.id.phonenm)
    private EditText phonenm;
    @ViewInject(R.id.yanzhengma)
    private EditText yanzhengma;
    Xutils xutils = new Xutils(getActivity());
    private String code = "";
    Random random = new Random();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(PhoneloginFragment.this, inflater, null);
        phonenm.setHintTextColor(getResources().getColor(R.color.hui2));
        yanzhengma.setHintTextColor(getResources().getColor(R.color.hui2));
        plyfasongyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phonenm.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "请输入手机号", Toast.LENGTH_SHORT).show();

                } else {
                    if (plyfasongyzm.getText().toString().equals("发送验证码")) {
                        code="";
                        userphonelogin();
                    }
                }
            }
        });
        phonelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yanzhengma.getText().toString().equals(code)) {
                    login();

                } else {

                    Toast.makeText(getActivity(), "验证码不正确", Toast.LENGTH_SHORT).show();
                }
            }

            private void login() {
                Map map = new HashMap();
                map.put("userName", phonenm.getText().toString());
                xutils.get(getResources().getString(R.string.Users), map, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        String userinfo = null;
                        try {
                            userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String i = userinfo.substring(userinfo.indexOf("\"", 9) + 1, userinfo.lastIndexOf("\""));
                        if (i.equals("fail")) {
                            Toast.makeText(getActivity(),"用户不存在请先注册", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            GsonUtil gsonUtil=new GsonUtil();
                            List<UserBase> UserBase = gsonUtil.Gson(userinfo, UserBase.class);
                            SharedPreferences mySharePreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
                            //实例化SharedPreferences.Editor对象
                            SharedPreferences.Editor editor = mySharePreferences.edit();
                            //用putString的方法保存数据
                            editor.putInt("uid", UserBase.get(0).getUid());
                            editor.putString("userName", UserBase.get(0).getUsername());
                            editor.putString("password", UserBase.get(0).getPassword());
                            editor.apply();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("success", "success");
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        return view;
    }


    private void userphonelogin() {
        Map map = new HashMap();
        map.put("userName", phonenm.getText().toString());
        xutils.get(getResources().getString(R.string.Users), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String userinfo = null;
                try {
                    userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.i("iiiiiiiiiiiii",userinfo);
                String i = userinfo.substring(userinfo.indexOf("\"", 9) + 1, userinfo.lastIndexOf("\""));
                if (i.equals("fail")) {
                    Toast.makeText(getActivity(),"用户不存在请先注册", Toast.LENGTH_SHORT).show();
                }
                else{

                    phoneverify();

                }
            }
        });
    }
    private void phoneverify() {
        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        Log.i("iiiiiiiiiii", code);
        Map map = new HashMap();
        map.put("phone", phonenm.getText().toString());
        map.put("checkCode", code);
        xutils.get(getResources().getString(R.string.getCloopen), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                Log.i("iiiiiiiiiii", i);
                if (i.equals("success")) {
                    Toast.makeText(getActivity(), "验证码已发送到" + phonenm.getText().toString() + "的手机,注意查收", Toast.LENGTH_SHORT).show();
                    plyfasongyzm.setBackground(getResources().getDrawable(R.color.hui2));
                    plyfasongyzm.setTextColor(getResources().getColor(R.color.blue));
                    CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            plyfasongyzm.setText(millisUntilFinished/1000 + "秒后可以再次发送");
                        }

                        @Override
                        public void onFinish() {
                            plyfasongyzm.setBackground(getResources().getDrawable(R.drawable.zhuchestyle));
                            plyfasongyzm.setTextColor(Color.WHITE);
                            plyfasongyzm.setText("发送验证码");
                        }
                    }.start();

                }

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
