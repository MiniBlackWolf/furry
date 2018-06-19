package com.example.wolf.userbean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wolf.R;
import com.example.wolf.Utils.Xutils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginZhuche2Activity extends AppCompatActivity {


    @BindView(R.id.zhucname)
    EditText zhucname;
    @BindView(R.id.zhucpass)
    EditText zhucpass;
    @BindView(R.id.zhuche2fh)
    ImageView zhuche2fh;
    @BindView(R.id.fin)
    Button fin;
    Xutils xutils = new Xutils(LoginZhuche2Activity.this);
    String phonenm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_zhuche_2);
        ButterKnife.bind(this);
        zhucname.setHintTextColor(getResources().getColor(R.color.hui3));
        zhucpass.setHintTextColor(getResources().getColor(R.color.hui3));
        Intent intent = getIntent();
        phonenm  = intent.getStringExtra("phonenm");
        Log.i("iiiiiiiiiiiiiii",phonenm);
    }

    private void register() {
        Map map=new HashMap();
        map.put("userName",phonenm);
        map.put("password",zhucpass.getText().toString());
        map.put("nickname",zhucname.getText().toString());
        xutils.post(getResources().getString(R.string.register), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {    String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                Log.i("iiiiiiiiiii", i);
                if (i.equals("success")) {
                    Toast.makeText(LoginZhuche2Activity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginZhuche2Activity.this,Loginpage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginZhuche2Activity.this, "当前用户已注册", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @OnClick({R.id.zhuche2fh, R.id.fin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuche2fh:
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.fin:
                if(zhucname.getText().toString().length()<=3&&zhucpass.getText().toString().length()<=6) {
                    if(zhucname.getText().toString().equals("")&&zhucpass.getText().toString().equals("")) {
                        Toast.makeText(LoginZhuche2Activity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (zhucname.getText().toString().length() <=3) {
                            Toast.makeText(LoginZhuche2Activity.this, "昵称长度不够", Toast.LENGTH_SHORT).show();
                        }
                        if (zhucpass.getText().toString().length() <= 6) {
                            Toast.makeText(LoginZhuche2Activity.this, "密码长度不够", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else{
                    register();
                }
                break;
        }
    }
}
