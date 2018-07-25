package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.pointshop.pointbean;

import java.util.List;

public class pointadapter extends BaseQuickAdapter<pointbean,BaseViewHolder>{
    private Context context;

    public pointadapter(int layoutResId, @Nullable List<pointbean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, pointbean item) {
        Glide.with(context).load(item.getSimgui()).centerCrop().placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.pointimg));
        helper.setText(R.id.pointname,item.getSname()+"");
        helper.setText(R.id.pointjies,item.getScontent()+"");
        helper.setText(R.id.pointmoney,item.getSprice()+"");
        helper.addOnClickListener(R.id.pointjian);
        helper.addOnClickListener(R.id.pointjia);
    }
}
