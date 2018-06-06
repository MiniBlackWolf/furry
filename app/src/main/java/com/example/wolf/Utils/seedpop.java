package com.example.wolf.Utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wolf.R;
import com.example.wolf.seed.ProdctBean;

public class seedpop extends PopupWindow {
    private LayoutInflater inflater;
    private ImageView imageView;
    private ProdctBean prodctBean;

    public seedpop(LayoutInflater inflater, ImageView imageView, ProdctBean prodctBean) {
        this.inflater = inflater;
        this.imageView = imageView;
        this.prodctBean = prodctBean;
    }

    public void setPopupWindow() {
        final View view = inflater.inflate(R.layout.seedpopitem, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(1000);
        popupWindow.setHeight(1500);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x000000f);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        TextView name = view.findViewById(R.id.popname);
        TextView moeny =view.findViewById(R.id.popmoeny);
        TextView description= view.findViewById(R.id.popdescription);
        name.setText(prodctBean.getName()+"");
        moeny.setText(prodctBean.getJiage()+"元");
        description.setText(prodctBean.getDescription()+"");
        popupWindow.showAsDropDown(imageView,100,-1000);

    }
}
