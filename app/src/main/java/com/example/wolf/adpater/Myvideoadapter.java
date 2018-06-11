package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.land.userland;

import java.util.List;

public class Myvideoadapter extends BaseQuickAdapter<userland,BaseViewHolder> {
    private Context context;

    public Myvideoadapter(int layoutResId, @Nullable List<userland> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, userland item) {

    }
}
