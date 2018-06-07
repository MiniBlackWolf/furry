package com.example.wolf.userbean;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.login_zhnaghao)
public class LoginZhnaghaoFragment extends Fragment {
    @ViewInject(R.id.username2)
    private EditText username;
    @ViewInject(R.id.passworld2)
    private EditText passworld;
    @ViewInject(R.id.denglul2)
    private Button denglu;
    Xutils xutils = new Xutils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(LoginZhnaghaoFragment.this, inflater, null);
        username.setHintTextColor(getResources().getColor(R.color.hui2));
        passworld.setHintTextColor(getResources().getColor(R.color.hui2));
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernames = username.getText().toString();
                final String passworlds = passworld.getText().toString();
                if (usernames.equals("") || passworlds.equals("")) {
                    Toast.makeText(getActivity(), "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                } else {
                    Map map = new HashMap();
                    map.put("userName", usernames);
                    map.put("password", passworlds);
                    xutils.post(getResources().getString(R.string.Login), map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                            Log.i("iiiiiii",i+" ");
                            if (i.equals("success")) {
                                Map<String, String> idmap = new HashMap();
                                idmap.put("userName", usernames);
                                xutils.get(getResources().getString(R.string.Userinfo), idmap, new Xutils.XCallBack() {
                                    @Override
                                    public void onResponse(String result) {
                                        String userinfo = null;
                                        try {
                                            userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");

                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        Log.i("iiiiiiiiiiiiiiiiiiiii", userinfo);
                                        GsonUtil gsonUtil = new GsonUtil();
                                        List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);

                                        //实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
                                        SharedPreferences mySharePreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
                                        //实例化SharedPreferences.Editor对象
                                        SharedPreferences.Editor editor = mySharePreferences.edit();
                                        //用putString的方法保存数据
                                        editor.putInt("uid", UserInfo.get(0).getUid());
                                        editor.putString("userName", usernames);
                                        editor.putString("password", passworlds);

                                        //提交数据
                                        editor.apply();
                                        //提示成功
                                        Toast.makeText(getActivity(), "数据成功写入" + UserInfo.get(0).getUid(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        intent.putExtra("success", "success");
                                        startActivity(intent);
                                    }
                                });


                            }
                            if (i.equals("fail")) {
                                Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
