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
import com.example.wolf.userbean.Userorder;
import com.example.wolf.userbean.Userorderitem;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class mycultivationadapterseed extends BaseQuickAdapter<userseed, BaseViewHolder> {
    private Context context;
    private Button okseedandland;
    private Spinner spinnerland;
    private TextView mycultivationpopcount;
    Xutils xutils = new Xutils(context);
    List<TextView> list = new ArrayList<>();
    List<userseed> list2 = new ArrayList<>();
    private Dialog dialog;
    List<Userorder> Userorders=new ArrayList<>();
    List<Userorderitem> Userorderitems=new ArrayList<>();

    public mycultivationadapterseed(int layoutResId, @Nullable List<userseed> data, Context context, Button okseedandland, Spinner spinnerland, TextView mycultivationpopcount,Dialog dialog) {
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
                helper.setText(R.id.mycultivationitem2button, SeedBean.get(0).getSeedname() + "-剩余" + item.getBuyCount() + "㎡");

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
                mycultivationpopcount.getText().toString();
                if(Integer.valueOf(mycultivationpopcount.getText().toString())==0){
                    ToastUtils.showToast(context,"请使用券");
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if(Integer.valueOf(list.get(i).getText().toString())<=0){
                        continue;
                    }
                    Userorderitem userorderitem=new Userorderitem();
                    userorderitem.setIid(list2.get(i).getSid().toString());
                    userorderitem.setCount(Integer.valueOf(list.get(i).getText().toString()));
                    userorderitem.setFid(null);
                    Userorderitems.add(userorderitem);
                }
                Userorder userorder=new Userorder();
                userorder.setUserorderitem(Userorderitems);
                userorder.setUid(new Getuserinfo(context).getuid());
                userorder.setName("使用播种券");
                userorder.setType(1);
                userorder.setStatus(0);
                userorder.setOrderDate(String.valueOf(System.currentTimeMillis()/1000));
                userorder.setDescription(spinnerland.getSelectedItem().toString().substring(0, spinnerland.getSelectedItem().toString().indexOf("-")));
                Gson gson=new Gson();
                String s = gson.toJson(userorder);
                RequestParams requestParams=new RequestParams(context.getResources().getString(R.string.sowing));
                requestParams.setBodyContent(s);
                requestParams.setAsJsonContent(true);
                requestParams.addParameter("voucher",mycultivationpopcount.getText().toString());
                x.http().post(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("success")) {
                            ToastUtils.showToast(context,"播种成功！");
                            dialog.dismiss();
                        }
                        else{

                            ToastUtils.showToast(context,"播种失败！");
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

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
