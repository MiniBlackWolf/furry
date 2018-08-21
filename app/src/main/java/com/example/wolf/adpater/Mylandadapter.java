package com.example.wolf.adpater;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.land.userland;
import com.example.wolf.seed.Seed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mylandadapter extends BaseQuickAdapter<userland, BaseViewHolder> {
    private Context context;
    private Button xuzu;
    List<String> list = new ArrayList();
    List<ImageView> list2 = new ArrayList();

    public Mylandadapter(int layoutResId, @Nullable List<userland> data, Context context, Button xuzu) {
        super(layoutResId, data);
        this.context = context;
        this.xuzu = xuzu;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final userland item) {


        String fid = item.getFid().substring(0, 1);
        long s = (item.getValidityperiod() - System.currentTimeMillis()) / (1000 * 3600 * 24);
        if (s < 0) {
            item.getFid();
            ImageView imageView = helper.getView(R.id.xuzhu);
            list.add(item.getFid());
            list2.add(imageView);
        }
        if (fid.equals("A")) {

            helper.setText(R.id.mianji, "10/m²");
            helper.setImageResource(R.id.tud, R.mipmap.a_a);
            helper.setText(R.id.fid, item.getFid());
            if (item.getSfid() == null) {
                helper.setText(R.id.sid, "空");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.crop__button_text));
            } else {
                helper.setText(R.id.sid, "查看详情");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.hong));
                helper.addOnClickListener(R.id.sid);
            }
            helper.setText(R.id.time, s < 0 ? "已过期" : (s + "天"));


        }
        if (fid.equals("B")) {
            helper.setText(R.id.mianji, "15/m²");
            helper.setImageResource(R.id.tud, R.mipmap.b_b);
            helper.setText(R.id.fid, item.getFid());
            if (item.getSfid() == null) {
                helper.setText(R.id.sid, "空");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.crop__button_text));
            } else {
                helper.setText(R.id.sid, "查看详情");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.hong));
                helper.addOnClickListener(R.id.sid);
            }
            helper.setText(R.id.time, s < 0 ? "已过期" : (s + "天"));
        }
        if (fid.equals("C")) {
            helper.setText(R.id.mianji, "20/m²");
            helper.setImageResource(R.id.tud, R.mipmap.c_c);
            helper.setText(R.id.fid, item.getFid());
            if (item.getSfid() == null) {
                helper.setText(R.id.sid, "空");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.crop__button_text));
            } else {
                helper.setText(R.id.sid, "查看详情");
                helper.setTextColor(R.id.sid, context.getResources().getColor(R.color.hong));
                helper.addOnClickListener(R.id.sid);
            }
            helper.setText(R.id.time, s < 0 ? "已过期" : (s + "天"));
        }
        helper.addOnClickListener(R.id.video);


        xuzu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xutils xutils = new Xutils(context);
                for (int i = 0; i < list.size(); i++) {
                    int visibility = list2.get(i).getVisibility();
                    if (visibility == View.VISIBLE) {
                        Getuserinfo getuserinfo = new Getuserinfo(context);
                        Map<String, String> map = new HashMap();
                        map.put("uid", String.valueOf(getuserinfo.getuid()));
                        map.put("fid", list.get(i));
                        xutils.get(context.getResources().getString(R.string.xufarm), map, new Xutils.XCallBack() {
                            @Override
                            public void onResponse(String result) {
                                String s = result.substring(result.indexOf(":") + 2, result.lastIndexOf("\""));
                                if (s.equals("success")) {
                                    Toast.makeText(context, "续租成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

    }

}
