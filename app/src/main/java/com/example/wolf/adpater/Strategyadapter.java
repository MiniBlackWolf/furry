package com.example.wolf.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.strategy.Strategybean;

import java.util.List;

public class Strategyadapter extends BaseQuickAdapter<Strategybean,BaseViewHolder> {

    public Strategyadapter(int layoutResId, @Nullable List<Strategybean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Strategybean item) {
        helper.setImageResource(R.id.Strategyimg,item.getImages());
        helper.setText(R.id.Strategytext,item.getText()+"");

    }
}
