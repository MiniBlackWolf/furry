package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.seed.Seed;
import com.example.wolf.seed.myseedbean;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myseedadapter extends BaseQuickAdapter<myseedbean, BaseViewHolder> {
    private Context context;
    private ZLoadingDialog zDialog;

    public Myseedadapter(int layoutResId, @Nullable List<myseedbean> data, Context context, ZLoadingDialog zDialog) {
        super(layoutResId, data);
        this.context = context;
        this.zDialog = zDialog;
    }


    @Override
    protected void convert(BaseViewHolder helper, myseedbean item) {
        if (item.getSeedfarm() != null) {
            for(myseedbean.SeedfarmBean SeedfarmBean:item.getSeedfarm()){
                helper.setText(R.id.myseedname, SeedfarmBean.getSid()+ "");
                helper.setText(R.id.myseedcounts, SeedfarmBean.getGrowDate() + "");
            }
            //  Glide.with(context).load(item.getSeed().getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.myseedimage));
            zDialog.dismiss();
        }
    }
}
