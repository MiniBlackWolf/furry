package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.seed.seedbean;
import com.example.wolf.seed.userseed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myseedadapter2 extends BaseQuickAdapter<userseed,BaseViewHolder>{
    private Context context;

    public myseedadapter2(int layoutResId, @Nullable List<userseed> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseed item) {
        Xutils xutils=new Xutils(context);
        Map<String,String> map=new HashMap<>();
        map.put("sid", String.valueOf(item.getSid()));
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiiii",result);
                GsonUtil gsonUtil=new GsonUtil();
                List<seedbean.SeedBean> gson = gsonUtil.Gson(result, seedbean.SeedBean.class);
                if(gson.get(0).getSeedname()!=null) {
                    Date date=new Date(item.getBuydate());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd日");
                    String dates = simpleDateFormat.format(date);
                    helper.setText(R.id.myseedcounts, item.getBuycount() + "/㎡");
                    helper.setText(R.id.myseedname, gson.get(0).getSeedname() + "");
                    helper.setText(R.id.myseeddate,dates);

                }

            }
        });

    }
}
