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
import com.example.wolf.me_order.orderbean;
import com.example.wolf.seed.seedbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myorderadapter2 extends BaseQuickAdapter<orderbean.ItemBean, BaseViewHolder> {
    private Context context;
    private Xutils xutils;

    public Myorderadapter2(int layoutResId, @Nullable List<orderbean.ItemBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        xutils = new Xutils(context);
    }

    @Override
    protected void convert(final BaseViewHolder helper, orderbean.ItemBean item) {
        switch (item.getItemtype()) {
            case "0":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu6);
                helper.setText(R.id.ordername2, "菜鸟环保|套餐券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "1":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu4);
                helper.setText(R.id.ordername2, "菜鸟环保|灌溉券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "2":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu5);
                helper.setText(R.id.ordername2, "菜鸟环保|农家肥券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "3":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu);
                helper.setText(R.id.ordername2, "菜鸟环保|除草券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "4":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu2);
                helper.setText(R.id.ordername2, "菜鸟环保|灭虫券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "5":
                helper.setImageResource(R.id.orderimage2, R.mipmap.cu5);
                helper.setText(R.id.ordername2, "菜鸟环保|有机肥券");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "A":
                helper.setImageResource(R.id.orderimage2, R.mipmap.a1);
                helper.setText(R.id.ordername2, "土地A");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "B":
                helper.setImageResource(R.id.orderimage2, R.mipmap.a2);
                helper.setText(R.id.ordername2, "土地B");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "C":
                helper.setImageResource(R.id.orderimage2, R.mipmap.a3);
                helper.setText(R.id.ordername2, "土地C");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            case "99":
                helper.setImageResource(R.id.orderimage2, R.mipmap.money1);
                helper.setText(R.id.ordername2, "充值");
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
            default:
                Map<String,String> map=new HashMap<>();
                map.put("sid",item.getManyid()+"");
                xutils.get(context.getResources().getString(R.string.getseedinfo), map, new Xutils.XCallBack() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onResponse(String result) {
                        GsonUtil gsonUtil = new GsonUtil();
                        List<seedbean.SeedBean> SeedBean = gsonUtil.Gson(result, seedbean.SeedBean.class);
                        helper.setText(R.id.ordername2,SeedBean.get(0).getSeedname());
                        Glide.with(context).load(SeedBean.get(0).getFileurl()).placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.orderimage2));

                    }
                });
                helper.setText(R.id.orderinfo, item.getDescription() + "");
                helper.setText(R.id.ordertime, item.getShijian() + "");
                helper.setText(R.id.ordermoney2, item.getUnitprice() + "");
                break;
        }

    }
}
