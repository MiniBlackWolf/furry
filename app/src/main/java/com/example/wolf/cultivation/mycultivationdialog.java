package com.example.wolf.cultivation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.land.userland;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mycultivationdialog extends DialogFragment {
    Spinner spinnerseed;
    Spinner spinnerland;
    Button okseedandland;
//    Xutils xutils = new Xutils();
    int uid;
    List<String> listseed;
    int x = 0;
    int c = 0;
    List<userseed> userseed;
    Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uid = new Getuserinfo(getActivity()).getuid();
        View view = inflater.inflate(R.layout.mycultivationpopitem, container, false);
        spinnerseed = view.findViewById(R.id.spinnerseed);
        spinnerland = view.findViewById(R.id.spinnerland);
        okseedandland = view.findViewById(R.id.okseedandland);
        addspinner();
        okseedandland();
        return view;
    }

    private void okseedandland() {
        okseedandland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("seedName", String.valueOf(spinnerseed.getSelectedItem()));
                Xutils.getInstance().get(getResources().getString(R.string.getSeedIdByName), map, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        //种子sid
                        String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                        Map<String, String> map = new HashMap<>();
                        map.put("uid", String.valueOf(new Getuserinfo(getActivity()).getuid()));
                        map.put("fid", String.valueOf(spinnerland.getSelectedItem()));
                        map.put("sid", i);
                        map.put("count", "1");
                        map.put("token", new Token().getToken(new Getuserinfo(getActivity()).getuid()));
                        Xutils.getInstance().get(getResources().getString(R.string.sowing), map, new Xutils.XCallBack() {
                            @Override
                            public void onResponse(String result) {
                                String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                                if(i.equals("success")){
                                    Toast.makeText(getActivity(),"播种成功",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                    }
                });

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
                GsonUtil gsonUtil1 = new GsonUtil();
                List<userland> userland = gsonUtil1.Gson(result, userland.class);
                List<String> listland = new ArrayList<>();
                for (int i = 0; i < userland.size(); i++) {
                    listland.add(userland.get(i).getFid());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listland);
                spinnerland.setAdapter(adapter);
            }
        });
        Map<String, String> map2 = new HashMap<>();
        map2.put("uid", String.valueOf(uid));
        map2.put("token", new Token().getToken(uid));
        map2.put("getMethod", "1");
        Xutils.getInstance().get(getResources().getString(R.string.getUserRemainSeed), map2, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil1 = new GsonUtil();
                userseed = gsonUtil1.Gson(result, userseed.class);
                if (userseed.isEmpty()) {
                    Toast.makeText(getActivity(),"请先购买种子",Toast.LENGTH_SHORT).show();
                }
                else{
                    listseed = new ArrayList<>();
                    Map<String, String> map = new HashMap<>();
                    for (x = 0; x < userseed.size(); x++) {
                        map.put("sid", String.valueOf(userseed.get(x).getSid()));
                        Xutils.getInstance().get(getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
                            @Override
                            public void onResponse(String result) {
                                c++;
                                GsonUtil gsonUtil2 = new GsonUtil();
                                List<seedbean.SeedBean> SeedBean = gsonUtil2.Gson(result, seedbean.SeedBean.class);
                                listseed.add(SeedBean.get(0).getSeedname());
                                Log.i("iiiiiiii", x + "");
                                if (c == userseed.size()) {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listseed);
                                    spinnerseed.setAdapter(adapter);

                                }
                            }
                        });
                    }

                }
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 不带style的构建的dialog宽度无法铺满屏幕
        //     Dialog dialog = new Dialog(getActivity());
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.mycultivationpopitem);
        dialog.setCanceledOnTouchOutside(true);

        // 设置弹出框布局参数，宽度铺满全屏，底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.MyDialog);

        return dialog;
    }
}
