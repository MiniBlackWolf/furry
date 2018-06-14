package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.seed.userseed;

import java.util.List;

public class mycultivationadapterseed extends BaseQuickAdapter<userseed, BaseViewHolder> {
    private Context context;

    public mycultivationadapterseed(int layoutResId, @Nullable List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, userseed item) {
        helper.setText(R.id.mycultivationitem2button, item.getSid()+"");
    }
}
