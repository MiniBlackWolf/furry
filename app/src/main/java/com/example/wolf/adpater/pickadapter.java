package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.land.userseedandland;
import com.example.wolf.seed.seedbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class pickadapter extends BaseQuickAdapter<userseedandland, BaseViewHolder> {
    private Context context;
    private Xutils xutils;
    private Button pickbutton;
    private Set<userseedandland> userseedandlandsSet=new LinkedHashSet<>();
    private List<userseedandland> userseedandlandsList=new ArrayList<>();
    private Set<ImageView> hands=new LinkedHashSet<>();
    private List<ImageView> handsList=new ArrayList<>();


    public pickadapter(int layoutResId, @Nullable List<userseedandland> data, Context context, Button pickbutton) {
        super(layoutResId, data);
        this.context = context;
        this.pickbutton = pickbutton;
        xutils = new Xutils(context);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final userseedandland item) {
        userseedandlandsSet.add(item);
        hands.add((ImageView) helper.getView(R.id.pickhand));
        Map<String, String> map = new HashMap<>();
        map.put("sid", item.getSid() + "");
        xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                helper.setText(R.id.pickname, SeedBean.get(0).getSeedname());
                if(helper.getView(R.id.pickday)!=null){
                    long l = System.currentTimeMillis() / 1000;
                    long time = (l - item.getSwoingdate()) / 60 / 60 / 24;
                    helper.setText(R.id.pickday,item.getGrowDate() - time+"");
                }
                Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.pickimage));
            }
        });

        pickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handsList.addAll(hands);
                userseedandlandsList.addAll(userseedandlandsSet);
                for(int i=0;i<handsList.size();i++){
                    if(handsList.get(i).getVisibility()==View.VISIBLE){
                        Map<String,String> map=new HashMap<>();
                        map.put("userid", String.valueOf(new Getuserinfo(context).getuid()));
                        map.put("sid",userseedandlandsList.get(i).getSid()+"");
                        map.put("fid",userseedandlandsList.get(i).getFid());
                        xutils.get(context.getResources().getString(R.string.harvest), map, new Xutils.XCallBack() {
                            @Override
                            public void onResponse(String result) {
                                if(result.equals("success")){
                                    ToastUtils.showToast(context,"采摘成功!");
                                }else {
                                    ToastUtils.showToast(context,"采摘失败!");
                                }
                            }
                        });


                    }

                }

            }
        });

    }
}
