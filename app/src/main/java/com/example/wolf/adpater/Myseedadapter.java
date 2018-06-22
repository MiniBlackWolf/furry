package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.seed.Seed;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myseedadapter extends BaseQuickAdapter<seedbean, BaseViewHolder> {
    private Context context;

    public Myseedadapter(int layoutResId, @Nullable List<seedbean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final seedbean item) {
        if(item.getSeed()!=null) {
            helper.setText(R.id.myseedcounts, item.getSeed().getCount() + "/㎡");
            helper.setText(R.id.myseedname, item.getSeed().getSeedname() + "");
        }

    }


}
