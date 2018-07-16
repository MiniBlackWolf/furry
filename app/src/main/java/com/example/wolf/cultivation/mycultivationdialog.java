package com.example.wolf.cultivation;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.adpater.mycultivationadapterseed;
import com.example.wolf.land.userland;
import com.example.wolf.seed.userseed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mycultivationdialog extends DialogFragment {
    //  Spinner spinnerseed;
    Spinner spinnerland;
    Button okseedandland;
    RecyclerView mycultivationseed;
    TextView mycultivationtotal;
    TextView mycultivationcunt;
    ImageView mycultivationpopjia;
    ImageView mycultivationpopjian;
    TextView mycultivationpopcount;
    ImageView arrows;
    mycultivationadapterseed mycultivationadapterseed;
    int uid;
    Dialog dialog;
    Xutils xutils = new Xutils(getActivity());
    int add = 0;
    int total;
    int Fcount;
    int add2;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uid = new Getuserinfo(getActivity()).getuid();
        View view = inflater.inflate(R.layout.mycultivationpopitem, container, false);
        init(view);
        setcucount();
        setseed();
        addspinner();
//        okseedandland();

        return view;
    }

    private void setcucount() {
        mycultivationpopjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                int counts = bundle.getInt("count");
                if (add >= counts) {
                    ToastUtils.showToast(getActivity(), "券不足请购买");
                } else {
                    add += 1;
                    mycultivationpopcount.setText(add + "");
                    mycultivationcunt.setText("共" + add + "件");
                    int count = Integer.valueOf(String.valueOf(mycultivationpopcount.getText()));
                    Fcount = count * 5;

                }
            }
        });
        mycultivationpopjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add <= 0) {
                    mycultivationpopcount.setText("0");
                    add = 0;
                } else {
                    add -= 1;
                    mycultivationpopcount.setText(add + "");
                    mycultivationcunt.setText("共" + add + "件");
                    int count = Integer.valueOf(String.valueOf(mycultivationpopcount.getText()));
                    Fcount = count * 5;
                    setseed();
                }
            }
        });
    }


    private void setseed() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(getActivity()).getuid()));
        map.put("token", new Token().getToken(new Getuserinfo(getActivity()).getuid()));
        xutils.get(getActivity().getResources().getString(R.string.getUserRemainSeedWithoutDate), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {

                //  Log.i("iiiiiiiii", result);
                GsonUtil gsonUtil = new GsonUtil();
                List<userseed> userseed = gsonUtil.Gson(result, com.example.wolf.seed.userseed.class);
                mycultivationadapterseed = new mycultivationadapterseed(R.layout.mycultivationitem2, userseed, getActivity(), okseedandland, spinnerland, Integer.valueOf(mycultivationpopcount.getText().toString()), dialog);
                mycultivationadapterseed.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        TextView mycultivationitem2count = (TextView) mycultivationadapterseed.getViewByPosition(mycultivationseed, position, R.id.mycultivationitem2count);
                        Button mycultivationitem2button = (Button) mycultivationadapterseed.getViewByPosition(mycultivationseed, position, R.id.mycultivationitem2button);
                        int count = mycultivationadapterseed.getItem(position).getBuyCount();
                        Log.i("iiiiiiiiiii", position + "");
                        if (mycultivationitem2count != null) {
                            switch (view.getId()) {
                                case R.id.mycultivationitem2jia: {
                                    add2 = Integer.valueOf(mycultivationitem2count.getText().toString());
                                    if (total < Fcount) {
                                        if (add2 >= count) {
                                            Toast.makeText(getActivity(), "种子不足请先购买", Toast.LENGTH_SHORT).show();
                                        } else {
                                            add2 += 1;
                                            total++;
                                            mycultivationtotal.setText("合计：蔬菜" + total + "种" + total + "㎡");
                                            mycultivationitem2count.setText(add2 + "");

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "请选择更多的券", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                }
                                case R.id.mycultivationitem2jian: {
                                    add2 = Integer.valueOf(mycultivationitem2count.getText().toString());
                                    if (add2 <= 0) {
                                        mycultivationitem2count.setText("0");
                                        add2 = 0;
                                    } else {
                                        add2 -= 1;
                                        total--;
                                        mycultivationtotal.setText("合计：蔬菜" + total + "种" + total + "㎡");
                                        mycultivationitem2count.setText(add2 + "");
                                    }
                                    break;
                                }
                            }
                        } else {
                            throw new NullPointerException("mycultivationitem2count是空！！！！");

                        }

                    }
                });
                mycultivationseed.setLayoutManager(linearLayoutManager);
                mycultivationseed.setAdapter(mycultivationadapterseed);
            }
        });
    }


    private void addspinner() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(uid));
        map.put("token", new Token().getToken(uid));
        xutils.get(getResources().getString(R.string.getUserFrame), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {

                //    Log.i("iiiiiiiii",result);
                GsonUtil gsonUtil1 = new GsonUtil();
                List<userland> userland = gsonUtil1.Gson(result, userland.class);
                List<String> listland = new ArrayList<>();
                for (int i = 0; i < userland.size(); i++) {
                    String landinfo = userland.get(i).getFid() + "-剩余" + userland.get(i).getRemainm() + "㎡";
                    listland.add(landinfo);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinneritem, listland);
                spinnerland.setAdapter(adapter);
                ObjectAnimator.ofFloat(arrows, "rotation", 0f, 180f).setDuration(1500).start();


            }
        });


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 不带style的构建的dialog宽度无法铺满屏幕
        //     Dialog dialog = new Dialog(getActivity());
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

    private void init(View view) {
        spinnerland = view.findViewById(R.id.spinnerland2);
        mycultivationseed = view.findViewById(R.id.mycultivationseed);
        mycultivationtotal = view.findViewById(R.id.mycultivationtotal);
        mycultivationcunt = view.findViewById(R.id.mycultivationcunt);
        okseedandland = view.findViewById(R.id.okseedandland2);
        mycultivationpopjia = view.findViewById(R.id.mycultivationpopjia2);
        mycultivationpopjian = view.findViewById(R.id.mycultivationpopjian2);
        mycultivationpopcount = view.findViewById(R.id.mycultivationpopcount);
        arrows = view.findViewById(R.id.arrows);
        linearLayoutManager = new LinearLayoutManager(getActivity());

    }
}
