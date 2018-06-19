package com.example.wolf.cultivation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
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
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.Utils.jiajian;
import com.example.wolf.Utils.jianli;
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
    mycultivationadapterseed mycultivationadapterseed;
    int uid;
    Dialog dialog;
    Xutils xutils = new Xutils(getActivity());
    int add = 0;
    int total;
    int Fcount;
    List<jiajian> ssss=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uid = new Getuserinfo(getActivity()).getuid();
        View view = inflater.inflate(R.layout.mycultivationpopitem, container, false);

        //    spinnerseed = view.findViewById(R.id.spinnerseed);
        init(view);
        jianli jianli = new jianli(mycultivationpopcount, add);
        mycultivationpopjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add < 0) {
                    mycultivationpopcount.setText("0");
                    add = 0;
                } else {
                    add += 1;
                    mycultivationpopcount.setText(add + "");
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
                }
            }
        });
        setseed();
        addspinner();
        okseedandland();
        return view;
    }


    private void init(View view) {
        spinnerland = view.findViewById(R.id.spinnerland);
        mycultivationseed = view.findViewById(R.id.mycultivationseed);
        mycultivationtotal = view.findViewById(R.id.mycultivationtotal);
        mycultivationcunt = view.findViewById(R.id.mycultivationcunt);
        okseedandland = view.findViewById(R.id.okseedandland);
        mycultivationpopjia = view.findViewById(R.id.mycultivationpopjia);
        mycultivationpopjian = view.findViewById(R.id.mycultivationpopjian);
        mycultivationpopcount = view.findViewById(R.id.mycultivationpopcount);
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
                for (int i = 0; i < userseed.size(); i++) {
                    int sid = userseed.get(i).getSid();
                    // Log.i("iiiiiiiii", sid + "");
                }
                mycultivationadapterseed = new mycultivationadapterseed(R.layout.mycultivationitem2, userseed, getActivity());
                mycultivationadapterseed.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        int add2 = 0;
                        Log.i("iiiii", String.valueOf(position));
                        TextView mycultivationitem2count = (TextView) mycultivationadapterseed.getViewByPosition(mycultivationseed, position, R.id.mycultivationitem2count);
                        Button mycultivationitem2button = (Button) mycultivationadapterseed.getViewByPosition(mycultivationseed, position, R.id.mycultivationitem2button);
                        int count = mycultivationadapterseed.getItem(position).getBuycount();
                        Log.i("iiiiiiiiiii", String.valueOf(mycultivationitem2button.getText()));
                        if (mycultivationitem2count != null) {
                            switch (view.getId()) {
                                case R.id.mycultivationitem2jia: {
                                    if (total < Fcount) {
                                        if (add2 >= count) {
                                            Toast.makeText(getActivity(), "种子不足请先购买", Toast.LENGTH_SHORT).show();
                                            break;
                                        } else {
                                            add2 += 1;
                                            total++;
                                            mycultivationitem2count.setText(add2 + "");
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "请选择更多的券", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                }
                                case R.id.mycultivationitem2jian: {

                                    if (add2 <= 0) {
                                        mycultivationitem2count.setText("0");
                                        add2 = 0;
                                    } else {
                                        add2 -= 1;
                                        total--;
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
                mycultivationseed.setLayoutManager(new LinearLayoutManager(getActivity()));
                mycultivationseed.setAdapter(mycultivationadapterseed);
            }
        });
    }

    private void okseedandland() {
        okseedandland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sid = mycultivationadapterseed.getItem(1).getSid();
                Log.i("iiiii", String.valueOf(sid));

            }
        });
    }

    private void addspinner() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(uid));
        map.put("token", new Token().getToken(uid));
        Xutils.getInstance().get(getResources().getString(R.string.getUserFrame), map, new Xutils.XCallBack() {
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
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = wlp.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.MyDialog);

        return dialog;
    }
}
