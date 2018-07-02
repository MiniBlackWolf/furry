package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.cultivation.usercultivation;

import java.util.List;

public class Mycultivationadapter extends BaseQuickAdapter<usercultivation, BaseViewHolder> {
    private Context context;

    public Mycultivationadapter(int layoutResId, @Nullable List<usercultivation> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, usercultivation item) {
        switch (item.getTid()) {
            case 0: {
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x3);
                helper.setText(R.id.cuname,"菜鸟环保|播种套餐券");
                helper.setText(R.id.cujianshao,"播种套餐券包括蔬菜播种时必经的3个流程：耕地，播种和首次施肥");
                helper.setText(R.id.cucount,item.getCount()+"张");
                break;
            }
            case 1: {
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x4);
                helper.setText(R.id.cuname,"菜鸟环保|灌溉券");
                helper.setText(R.id.cujianshao,"保证作物正常生长，供给作物充分的水分，补给作物水分的技术措施");
                helper.setText(R.id.cucount,item.getCount()+"张");
                break;
            }
            case 2: {
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x5);
                helper.setText(R.id.cuname,"菜鸟环保|农家肥券");
                helper.setText(R.id.cujianshao,"将肥料施于土壤中，保障作物充分的养分，提高作物品质");
                helper.setText(R.id.cucount,item.getCount()+"张");
                break;
            }
            case 3: {
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x6);
                helper.setText(R.id.cuname,"菜鸟环保|除草券");
                helper.setText(R.id.cujianshao,"及时将生长在作物田间的杂草通过人工防除，为作物提供良好的生长环境");
                helper.setText(R.id.cucount,item.getCount()+"张");
                break;
            }
            case 4: {
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x7);
                helper.setText(R.id.cuname,"菜鸟环保|灭虫券");
                helper.setText(R.id.cujianshao,"蔬菜在生长期间遭到虫害，灭虫可以减少蔬菜收到的虫害，保证其生长");
                helper.setText(R.id.cucount,item.getCount()+"张");
                break;
            }
            case 5:{
                helper.setImageResource(R.id.cultivationimage,R.mipmap.x3);
                helper.setText(R.id.cuname,"菜鸟环保|有机肥券");
                helper.setText(R.id.cujianshao,"蔬菜在生长期间遭到虫害，灭虫可以减少蔬菜收到的虫害，保证其生长");
                helper.setText(R.id.cucount,item.getCount()+"张");


            }
        }

    }
}
