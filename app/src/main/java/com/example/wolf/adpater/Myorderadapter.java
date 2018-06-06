package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.me_order.orderbean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.zip.DataFormatException;

import static java.text.DateFormat.getDateTimeInstance;

public class Myorderadapter  extends BaseQuickAdapter<orderbean,BaseViewHolder>{
    private Context context;

    public Myorderadapter(int layoutResId, @Nullable List<orderbean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, orderbean item) {
        long orderdate = item.getOrderdate();
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        String data = newSimpleDateFormat.format(orderdate);
        switch (item.getType()) {
            case 0:{
                helper.setImageResource(R.id.orderimage,R.mipmap.o7);
                helper.setText(R.id.ordername,"购买土地");
                helper.setText(R.id.ordername2,"我的土地");
                helper.setText(R.id.ordertime,data);


                break;
            }
            case 1:{
                helper.setImageResource(R.id.orderimage,R.mipmap.o3);
                helper.setText(R.id.ordername,item.getName());
                helper.setText(R.id.ordername2,"我的种子");
                helper.setText(R.id.ordertime,data);
                break;
            }
            case 2:{
                helper.setImageResource(R.id.orderimage,R.mipmap.o1);
                helper.setText(R.id.ordername,item.getName());
                helper.setText(R.id.ordername2,"我的券");
                helper.setText(R.id.ordertime,data);
                break;
            }
        }
        switch (item.getStatus()){
            case 0:{
                helper.setText(R.id.ordersta,"进行中");
                break;
            }
            case 1:{
                helper.setText(R.id.ordersta,"配送中");
                break;
            }
            case 2:{
                helper.setText(R.id.ordersta,"已完成");
                break;
            }
            case 3:{
                helper.setText(R.id.ordersta,"已终止");
                break;
            }
        }
    }
}
