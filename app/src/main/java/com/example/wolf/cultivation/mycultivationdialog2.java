package com.example.wolf.cultivation;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.land.userland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mycultivationdialog2 extends DialogFragment {
    private TextView mycultivationpopcount2;
    private TextView mycultivationpopname;
    private ImageView mycultivationpopjia2;
    private ImageView mycultivationpopjian2;
    private ImageView mycultivationpopimage;
    private Spinner spinnerland2;
    private Button okseedandland2;
    private ImageView arrows2;
    private TextView mycultivationcunt;
    int add;
    int Fcount;
    Xutils xutils = new Xutils(getActivity());
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycultivationpopitem2, container, false);
        init(view);
        addspinner();
        setcucount();
        changed();
        oklandset();
        return view;
    }

    private void changed() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        switch (type) {
            case 1: {
                mycultivationpopname.setText("菜鸟环保|灌溉券");
                mycultivationpopimage.setImageResource(R.mipmap.cu4);
                break;
            }
            case 2: {
                mycultivationpopname.setText("菜鸟环保|农家肥券");
                mycultivationpopimage.setImageResource(R.mipmap.cu5);
                break;
            }
            case 3: {
                mycultivationpopname.setText("菜鸟环保|除草券");
                mycultivationpopimage.setImageResource(R.mipmap.cu3);
                break;
            }
            case 4: {
                mycultivationpopname.setText("菜鸟环保|灭虫券");
                mycultivationpopimage.setImageResource(R.mipmap.cu2);
                break;
            }
            case 5: {
                mycultivationpopname.setText("菜鸟环保|有机肥券");
                mycultivationpopimage.setImageResource(R.mipmap.cu5);
                break;
            }

        }

    }

    private void oklandset() {
        okseedandland2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerland2.getSelectedItem().equals("没有选择土地")) {
                     ToastUtils.showToast(getActivity(),"请选择土地!");
                    return;

                }
                Map<String, String> map = new HashMap<>();
                map.put("count", mycultivationpopcount2.getText().toString());
                map.put("uid", String.valueOf(new Getuserinfo(getActivity()).getuid()));
                map.put("tid", String.valueOf(getTag()));
                map.put("fid",spinnerland2.getSelectedItem().toString().substring(0, spinnerland2.getSelectedItem().toString().indexOf("-")));
          //      map.put("token", new Token().getToken(new Getuserinfo(getActivity()).getuid()));
                xutils.get(getResources().getString(R.string.useTicket), map, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        if (result.equals("success")) {
                            ToastUtils.showToast(getActivity(), "使用成功！");
                            dialog.dismiss();
                        } else {

                            ToastUtils.showToast(getActivity(), "使用失败！");
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

    }

    private void addspinner() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(getActivity()).getuid()));
        map.put("token", new Token().getToken(new Getuserinfo(getActivity()).getuid()));
        xutils.get(getResources().getString(R.string.getUserFrame), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {

                //    Log.i("iiiiiiiii",result);
                GsonUtil gsonUtil1 = new GsonUtil();
                List<userland> userland = gsonUtil1.Gson(result, userland.class);
                List<String> listland = new ArrayList<>();
                listland.add("没有选择土地");
                for (int i = 0; i < userland.size(); i++) {
                    String landinfo = userland.get(i).getFid() + "-剩余" + userland.get(i).getRemainm() + "㎡";
                    listland.add(landinfo);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinneritem, listland);
                spinnerland2.setAdapter(adapter);
                ObjectAnimator.ofFloat(arrows2, "rotation", 0f, 180f).setDuration(1000).start();


            }
        });
    }

    private void setcucount() {
        mycultivationpopjia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                int counts = bundle.getInt("count");
                if (add >= counts) {
                    ToastUtils.showToast(getActivity(), "券不足请购买");
                } else {
                    add += 1;
                    mycultivationpopcount2.setText(add + "");
                    mycultivationcunt.setText("共" + add + "件");
                    int count = Integer.valueOf(String.valueOf(mycultivationpopcount2.getText()));
                    Fcount = count * 5;

                }
            }
        });
        mycultivationpopjian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add <= 0) {
                    mycultivationpopcount2.setText("0");
                    add = 0;
                } else {
                    add -= 1;
                    mycultivationpopcount2.setText(add + "");
                    mycultivationcunt.setText("共" + add + "件");
                    int count = Integer.valueOf(String.valueOf(mycultivationpopcount2.getText()));
                    Fcount = count * 5;
                }
            }
        });
    }

    private void init(View view) {
        arrows2 = view.findViewById(R.id.arrows2);
        mycultivationpopcount2 = view.findViewById(R.id.mycultivationpopcount2);
        mycultivationpopjia2 = view.findViewById(R.id.mycultivationpopjia2);
        mycultivationpopjian2 = view.findViewById(R.id.mycultivationpopjian2);
        mycultivationpopname = view.findViewById(R.id.mycultivationpopname);
        mycultivationpopimage = view.findViewById(R.id.mycultivationpopimage);
        spinnerland2 = view.findViewById(R.id.spinnerland2);
        okseedandland2 = view.findViewById(R.id.okseedandland2);
        mycultivationcunt = view.findViewById(R.id.mycultivationcunt);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.mycultivationpopitem);
        dialog.setCanceledOnTouchOutside(true);

        // 设置弹出框布局参数，宽度铺满全屏，底部。
        Window window = dialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.MyDialog);
        return dialog;
    }


}
