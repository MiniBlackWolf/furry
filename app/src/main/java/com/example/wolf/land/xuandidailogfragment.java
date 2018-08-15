package com.example.wolf.land;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.OrderUtils;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("SetTextI18n")
public class xuandidailogfragment extends DialogFragment {
    TextView count10;
    TextView count15;
    TextView count20;
    TextView lastcount;
    TextView lastname;
    Button lastbuy;
    Button countjia;
    Button countjian;
    Button countjia2;
    Button countjian2;
    Button countjia3;
    Button countjian3;
    ImageView xuandiinfoimage;
    Xutils xutils = new Xutils(getActivity());
    List<Userfarm> userfarmsA = new ArrayList<>();
    List<Userfarm> userfarmsB = new ArrayList<>();
    List<Userfarm> userfarmsC = new ArrayList<>();
    List<Userfarm> userfarms10 = new ArrayList<>();
    List<Userfarm> userfarms15 = new ArrayList<>();
    List<Userfarm> userfarms20 = new ArrayList<>();
    List<orderbeans.payitem> payitem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xuandiinfoitem, container, false);
        init(view);
        onclock(countjia, countjian, count10);
        onclock(countjia2, countjian2, count15);
        onclock(countjia3, countjian3, count20);
        int c = getArguments().getInt("lastcount");
        lastcount.setText(c + "");
        xutils.get(getResources().getString(R.string.getAllfarm), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<Userfarm> Userfarms = gsonUtil.Gson(result, Userfarm.class);
                for (Userfarm userfarm : Userfarms) {
                    switch (userfarm.getFid().substring(0, 1)) {
                        case "A":
                            userfarmsA.add(userfarm);
                            break;
                        case "B":
                            userfarmsB.add(userfarm);
                            break;
                        case "C":
                            userfarmsC.add(userfarm);
                            break;
                    }
                }
                switch (getTag()) {
                    case "A":
                        xuandiinfoimage.setImageResource(R.mipmap.aa1);
                        buyfarm(lastbuy,"A",userfarmsA);
                        break;
                    case "B":
                        xuandiinfoimage.setImageResource(R.mipmap.aa2);
                        buyfarm(lastbuy,"A",userfarmsB);
                        break;
                    case "C":
                        xuandiinfoimage.setImageResource(R.mipmap.aa3);
                        buyfarm(lastbuy,"A",userfarmsC);
                        break;

                }
            }
        });
        return view;
    }

    private void init(View view) {
        count10 = view.findViewById(R.id.count10);
        count15 = view.findViewById(R.id.count15);
        count20 = view.findViewById(R.id.count20);
        lastcount = view.findViewById(R.id.lastcount);
        lastname = view.findViewById(R.id.lastname);
        lastbuy = view.findViewById(R.id.lastbuy);
        countjia = view.findViewById(R.id.countjia);
        countjian = view.findViewById(R.id.countjian);
        countjia2 = view.findViewById(R.id.countjia2);
        countjian2 = view.findViewById(R.id.countjian2);
        countjia3 = view.findViewById(R.id.countjia3);
        countjian3 = view.findViewById(R.id.countjian3);
        xuandiinfoimage = view.findViewById(R.id.xuandiinfoimage);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        WindowManager windowManager1 = getActivity().getWindow().getWindowManager();
        Display d1 = windowManager1.getDefaultDisplay();
        Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.xuandiinfoitem);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        WindowManager windowManager = window.getWindowManager();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.MyDialog);
        return dialog;
    }

    private void onclock(Button jia, Button jian, final TextView count) {

        jia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String s = count.getText().toString();
                int counts = Integer.valueOf(s);
                counts++;
                count.setText(counts + "");
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String s = count.getText().toString();
                int counts = Integer.valueOf(s);
                counts--;
                if (counts <= 0) {
                    counts = 0;
                }
                count.setText(counts + "");
            }
        });

    }


    private void buyfarm(Button buy, final String tag, final List<Userfarm> userfarms) {

        buy.setOnClickListener(new View.OnClickListener() {
            double Allmoney = 0;

            @Override
            public void onClick(View view) {
                for (Userfarm userfarm : userfarms) {
                    if (userfarm.getGenre() == 1) {
                        userfarms10.add(userfarm);
                    }
                    if (userfarm.getGenre() == 2) {
                        userfarms15.add(userfarm);

                    }
                    if (userfarm.getGenre() == 3) {
                        userfarms20.add(userfarm);
                    }
                }
                if (!userfarms10.isEmpty() && Integer.valueOf(count10.getText().toString()) != 0) {
                    if (userfarms10.size() >= Integer.valueOf(count10.getText().toString())) {
                        for (int i = 0; i < Integer.valueOf(count10.getText().toString()); i++) {
                            orderbeans.payitem a = OrderUtils.addpayitem(userfarms10.get(i).getFid(), tag, 1, 120);
                            Allmoney += 120;
                            payitem.add(a);
                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "10平方米土地不足，购买失败!");
                        return;

                    }
                } else if(Integer.valueOf(count10.getText().toString())!=0){
                    ToastUtils.showToast(getActivity(), "10平方米土地不足，购买失败!");
                    return;

                }
                if (!userfarms15.isEmpty() && Integer.valueOf(count15.getText().toString()) != 0) {
                    if (userfarms15.size() >= Integer.valueOf(count15.getText().toString())) {
                        for (int i = 0; i < Integer.valueOf(count15.getText().toString()); i++) {
                            orderbeans.payitem a = OrderUtils.addpayitem(userfarms15.get(i).getFid(), tag, 1, 180);
                            Allmoney += 180;
                            payitem.add(a);
                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "15平方米土地不足，购买失败!");
                        return;

                    }
                }
                else if(Integer.valueOf(count15.getText().toString())!=0){
                    ToastUtils.showToast(getActivity(), "15平方米土地不足，购买失败!");
                    return;

                }
                if (!userfarms20.isEmpty() && Integer.valueOf(count20.getText().toString()) != 0) {
                    if (userfarms20.size() >= Integer.valueOf(count20.getText().toString())) {
                        for (int i = 0; i < Integer.valueOf(count20.getText().toString()); i++) {
                            orderbeans.payitem a = OrderUtils.addpayitem(userfarms20.get(i).getFid(), tag, 1, 240);
                            Allmoney += 240;
                            payitem.add(a);
                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "20平方米土地不足，购买失败!");
                        return;
                    }
                }
                else if(Integer.valueOf(count20.getText().toString())!=0){
                    ToastUtils.showToast(getActivity(), "20平方米土地不足，购买失败!");
                    return;

                }
                orderbeans orderbeans = OrderUtils.addorder("购买土地", 3, Allmoney, new Getuserinfo(getActivity()).getuid(), System.currentTimeMillis() / 1000, payitem);
                Gson gson = new Gson();
                String s = gson.toJson(orderbeans);
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.buyfarm));
                requestParams.setConnectTimeout(40000);
                requestParams.setAsJsonContent(true);
                requestParams.setBodyContent(s);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("success")) {
                            ToastUtils.showToast(getActivity(), "购买成功");
                            Intent intent=new Intent(getActivity(),Xuandi.class);
                            startActivity(intent);
                            dismiss();
                        } else {
                            ToastUtils.showToast(getActivity(), "购买失败，请检查网络");
                            Intent intent=new Intent(getActivity(),Xuandi.class);
                            startActivity(intent);
                            dismiss();
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ToastUtils.showToast(getActivity(), "购买失败，请检查网络");
                        Intent intent=new Intent(getActivity(),Xuandi.class);
                        startActivity(intent);
                        dismiss();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }
        });

    }
}
