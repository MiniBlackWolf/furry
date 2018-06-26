package com.example.wolf.adpater;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.cultivation.mycultivationdialog;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mycultivationadapterseed extends BaseQuickAdapter<userseed, BaseViewHolder> {
    private Context context;
    private Button okseedandland;
    private Spinner spinnerland;
    private int mycultivationpopcount;
    Xutils xutils = new Xutils(context);
    List<TextView> list = new ArrayList<>();
    List<userseed> list2 = new ArrayList<>();
    private Dialog dialog;

    public mycultivationadapterseed(int layoutResId, @Nullable List<userseed> data, Context context, Button okseedandland, Spinner spinnerland, int mycultivationpopcount,Dialog dialog) {
        super(layoutResId, data);
        this.context = context;
        this.okseedandland = okseedandland;
        this.spinnerland = spinnerland;
        this.mycultivationpopcount = mycultivationpopcount;
        this.dialog=dialog;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseed item) {
        Xutils xutils = new Xutils(context);
        Map<String, String> map = new HashMap<>();
        map.put("sid", String.valueOf(item.getSid()));
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.mycultivationitem2button, SeedBean.get(0).getSeedname() + "-剩余" + item.getBuycount() + "㎡");

            }
        });
        list.add((TextView) helper.getView(R.id.mycultivationitem2count));
        list2.add(item);
        helper.addOnClickListener(R.id.mycultivationitem2jia);
        helper.addOnClickListener(R.id.mycultivationitem2jian);
        okseedandland();

    }

    private void okseedandland() {

        okseedandland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.valueOf(list.get(i).getText().toString())<=0){
                        break;
                    }
                    int sid = list2.get(i).getSid();
                    Map<String, String> map = new HashMap<>();
                    map.put("uid", String.valueOf(new Getuserinfo(context).getuid()));
                    map.put("fid", spinnerland.getSelectedItem().toString().substring(0, spinnerland.getSelectedItem().toString().indexOf("-")));
                    map.put("sid", String.valueOf(sid));
                    map.put("count",list.get(i).getText().toString());
                    map.put("voucher", String.valueOf(mycultivationpopcount));
                    map.put("token", new Token().getToken(new Getuserinfo(context).getuid()));
                    xutils.get(context.getResources().getString(R.string.sowing), map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            String i = result.substring(result.indexOf("\"", 9) + 1, result.lastIndexOf("\""));
                            if (i.equals("success")) {
                                ToastUtils.showToast(context,"播种成功！");
                                dialog.dismiss();
                            }
                            else{

                                ToastUtils.showToast(context,"播种失败！");
                                dialog.dismiss();
                            }

                        }
                    });
                }

            }
        });
    }
}
