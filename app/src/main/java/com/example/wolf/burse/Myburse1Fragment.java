package com.example.wolf.burse;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.example.wolf.userbean.UserInfo;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ContentView(R.layout.myburse_1)
public class Myburse1Fragment extends Fragment {

    Xutils xutils = new Xutils();
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @BindView(R.id.moneyss)
    TextView moneyss;
    @BindView(R.id.pay)
    Button pay;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.i("iiiiii", "resultInfo:" + resultInfo);
                    Log.i("iiiiii", "resultStatus:" + resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getActivity(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getActivity(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).
                                show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //    EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        View mRootView = inflater.inflate(R.layout.myburse_1, container, false);
        ButterKnife.bind(this, mRootView);
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
                Log.i("iiiiiiii", UserInfo.get(0).getMoney() + "");
                moneyss.setText("￥"+UserInfo.get(0).getMoney());
            }
        });
        return mRootView;
    }

    @OnClick({R.id.pay})
    public void onViewClicked(View view) {

        Getuserinfo getuserinfo = new Getuserinfo(getActivity());
        //支付方法
        Xutils xutils = new Xutils();
        Map<String, String> map = new HashMap();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        map.put("money", "0.01");
        map.put("subject", "皮皮虾");//商品名
        map.put("token", new Token().getToken(getuserinfo.getuid()));
        //获取订单信息
        xutils.get(getResources().getString(R.string.getOrderInfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                final orderinfo orderinfo = gson.fromJson(result, orderinfo.class);
                Log.i("iiiiiiiiii", orderinfo.getStatus());
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(getActivity());
                        Map<String, String> result = alipay.payV2(orderinfo.getStatus(), true);
                        Log.i("msp", result.toString());
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();


            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}

