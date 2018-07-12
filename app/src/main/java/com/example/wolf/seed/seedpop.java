package com.example.wolf.seed;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolf.R;
import com.example.wolf.seed.ProdctBean;

public class seedpop extends PopupWindow {
    private LayoutInflater inflater;
    private ImageView imageView;
    private ProdctBean prodctBean;
    private Context context;

    public seedpop(LayoutInflater inflater, ImageView imageView, ProdctBean prodctBean,Context context) {
        this.inflater = inflater;
        this.imageView = imageView;
        this.prodctBean = prodctBean;
        this.context=context;
    }

    public void setPopupWindow() {
        final View view = inflater.inflate(R.layout.seedpopitem, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(700);
        popupWindow.setHeight(1200);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x000000f);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        TextView name = view.findViewById(R.id.popname);
        TextView moeny =view.findViewById(R.id.popmoeny);
        TextView description= view.findViewById(R.id.popdescription);
        ImageView popimage=view.findViewById(R.id.popimage);
        name.setText(prodctBean.getName()+"");
        moeny.setText(prodctBean.getJiage()+"元");
        description.setText(prodctBean.getDescription()+"");
       Glide.with(context).load(prodctBean.getImage()).placeholder(R.mipmap.loading2).into(popimage);
        popupWindow.showAsDropDown(imageView,100,-1000);

    }
}
