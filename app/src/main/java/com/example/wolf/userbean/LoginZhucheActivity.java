package com.example.wolf.userbean;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginZhucheActivity extends Activity {

    @BindView(R.id.yanzhengma2)
    EditText yanzhengma2;
    @BindView(R.id.phonenm2)
    EditText phonenm2;
    @BindView(R.id.zhuchefh)
    ImageView zhuchefh;
    @BindView(R.id.fasongyanzm)
    Button fasongyanzm;
    private String code = "";
    Random random = new Random();
    Xutils xutils = new Xutils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_zhuche);
        ButterKnife.bind(this);
        yanzhengma2.setHintTextColor(getResources().getColor(R.color.hui2));
        phonenm2.setHintTextColor(getResources().getColor(R.color.hui2));

    }

    private void phoneverify() {
        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        Log.i("iiiiiiiiiii", code);
        Map map = new HashMap();
        map.put("phone", phonenm2.getText().toString());
        map.put("checkCode", code);
        xutils.get(getResources().getString(R.string.getCloopen), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                Log.i("iiiiiiiiiii", i);
                if (i.equals("success")) {
                    Toast.makeText(LoginZhucheActivity.this, "验证码已发送到" + phonenm2.getText().toString() + "的手机,注意查收", Toast.LENGTH_SHORT).show();
                    fasongyanzm.setBackground(getResources().getDrawable(R.color.hui2));
                    fasongyanzm.setTextColor(getResources().getColor(R.color.blue));
                    CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            fasongyanzm.setText(millisUntilFinished/1000 + "秒后可以再次发送");
                        }

                        @Override
                        public void onFinish() {
                            fasongyanzm.setBackground(getResources().getDrawable(R.drawable.zhuchestyle));
                            fasongyanzm.setTextColor(Color.WHITE);
                            fasongyanzm.setText("发送验证码");
                        }
                    }.start();

                }

            }
        });
    }

    @OnClick({R.id.zhuchefh, R.id.xiayibu, R.id.fasongyanzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuchefh:
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.xiayibu:
                if (yanzhengma2.getText().toString().equals(code)) {

                    Intent intent = new Intent(LoginZhucheActivity.this, LoginZhuche2Activity.class);
                    intent.putExtra("phonenm",phonenm2.getText().toString());
                    Log.i("iiiiiiiiiiii",phonenm2.getText().toString());
                    startActivity(intent);
                } else {

                    Toast.makeText(LoginZhucheActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fasongyanzm:
                if (phonenm2.getText().toString().isEmpty()) {
                    Toast.makeText(LoginZhucheActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();

                } else {
                    if (fasongyanzm.getText().toString().equals("发送验证码")) {
                        code="";
                        phoneverify();
                    }


                }
                break;

        }
    }


}
