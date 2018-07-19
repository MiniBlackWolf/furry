package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.land.Farminfo;

import java.util.List;

public class xuandiadapter extends BaseQuickAdapter<Farminfo, BaseViewHolder> {
    private Context context;

    public xuandiadapter(int layoutResId, @Nullable List<Farminfo> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, Farminfo item) {
        if (item.getType().equals("a")) {
            helper.setText(R.id.jiage_1, "¥" + item.getPrice());
            helper.setImageResource(R.id.imageland,R.mipmap.a1);
        } else if (item.getType().equals("b")) {
            helper.setText(R.id.jiage_2, "¥" + item.getPrice());
            helper.setImageResource(R.id.imageland,R.mipmap.a2);
        } else if (item.getType().equals("c")) {
            helper.setText(R.id.jiage_3, "¥" + item.getPrice());
            helper.setImageResource(R.id.imageland,R.mipmap.a3);
        }


    }
}
