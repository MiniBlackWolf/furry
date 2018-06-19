package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mycultivationadapterseed extends BaseQuickAdapter<userseed, BaseViewHolder> {
    private Context context;


    public mycultivationadapterseed(int layoutResId, @Nullable List data, Context context) {
        super(layoutResId, data);
        this.context = context;

    }


    @Override
    protected void convert(final BaseViewHolder helper, final userseed item) {
        Xutils xutils=new Xutils(context);
        Map<String, String> map = new HashMap<>();
        map.put("sid", String.valueOf(item.getSid()));
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.mycultivationitem2button, SeedBean.get(0).getSeedname()+"-剩余"+item.getBuycount()+"㎡");
                Log.i("iiiiiiiiiii",SeedBean.get(0).getSeedname());
            }
        });
        helper.addOnClickListener(R.id.mycultivationitem2jia);
        helper.addOnClickListener(R.id.mycultivationitem2jian);
    }
}
