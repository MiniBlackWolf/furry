package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Timechang;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.seed.Seed;
import com.example.wolf.seed.myseedbean;
import com.example.wolf.seed.myseedbean2;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;
import com.google.gson.Gson;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myseedadapter extends BaseQuickAdapter<myseedbean2, BaseViewHolder> {
    private Context context;
    private ZLoadingDialog zDialog;

    public Myseedadapter(int layoutResId, @Nullable List<myseedbean2> data, Context context, ZLoadingDialog zDialog) {
        super(layoutResId, data);
        this.context = context;
        this.zDialog = zDialog;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final myseedbean2 item) {
        Xutils xutils=new Xutils(context);
        Map<String,String> map=new HashMap<>();
        map.put("sid",item.getSid()+"");
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                String date = Timechang.getInstance().stampToDate(item.getSwoingdate() + "");
                helper.setText(R.id.myseedcounts, item.getLol()+"");
                helper.setText(R.id.myseeddate,date+"");
                helper.setText(R.id.myseedland,item.getFid()+"");
                helper.setText(R.id.myseedname,SeedBean.get(0).getSeedname()+"");
                Log.i("iiiiiiiiiiii", helper.getAdapterPosition() + "");
                Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.myseedimage));
            }
        });


        zDialog.dismiss();

    }
}
