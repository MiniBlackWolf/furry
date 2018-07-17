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
import com.example.wolf.land.userseedandland;
import com.example.wolf.seed.seedbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MylandShoveladapter extends BaseQuickAdapter<userseedandland, BaseViewHolder> {
    private Context context;

    public MylandShoveladapter(int layoutResId, @Nullable List<userseedandland> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseedandland item) {
        Xutils xutils=new Xutils(context);
        Map<String,String> map=new HashMap<>();
        map.put("sid", String.valueOf(item.getSid()));
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil=new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.mylanddialogitemname, SeedBean.get(0).getSeedname() + "");
                Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.mylanddialogitemimage));
            }
        });

    }
}
