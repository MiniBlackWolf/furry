package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.me_order.orderbean;

import java.util.List;

import static java.text.DateFormat.getDateTimeInstance;

public class Myorderadapter extends BaseQuickAdapter<orderbean, BaseViewHolder> {
    private Context context;


    public Myorderadapter(int layoutResId, @Nullable List<orderbean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, orderbean item) {

        switch (item.getGoodstype()) {
            case 1:
                helper.setText(R.id.ordername, "耕耘券");
                helper.setImageResource(R.id.orderimage, R.mipmap.o1);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
             //   orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;
            case 2:
                helper.setText(R.id.ordername, "种子");
                helper.setImageResource(R.id.orderimage, R.mipmap.o3);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
            //    orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;
            case 3:
                helper.setText(R.id.ordername, "选地");
                helper.setImageResource(R.id.orderimage, R.mipmap.o7);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
            //    orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;
            case 4:
                helper.setText(R.id.ordername, "锄种");
                helper.setImageResource(R.id.orderimage, R.mipmap.o10);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
            //    orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;
            case 5:
                helper.setText(R.id.ordername, "充值");
                helper.setImageResource(R.id.orderimage, R.mipmap.o11);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
            //    orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;
            case 6:
                helper.setText(R.id.ordername, "采摘");
                helper.setImageResource(R.id.orderimage, R.mipmap.o12);
                helper.setText(R.id.ordermoney2, item.getPrice() + "");
                helper.setText(R.id.ordercount, item.getZong() + "");
             //   orderrecyclerview.setAdapter(myorderadapter2);
                setadpadter(helper, item);
                break;


        }
    }

    private void setadpadter(BaseViewHolder helper, orderbean item) {
        Myorderadapter2 myorderadapter2 = new Myorderadapter2(R.layout.orderitem2, item.getItem(), context);
        myorderadapter2.openLoadAnimation(SLIDEIN_LEFT);
        myorderadapter2.isFirstOnly(false);
        RecyclerView orderrecyclerview = (RecyclerView) helper.getView(R.id.orderrecyclerview);
        orderrecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));
        orderrecyclerview.setAdapter(myorderadapter2);
    }
}
