package com.example.wolf.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

public class ZloadingDiaLogkt  {
    private Context context;

    public ZloadingDiaLogkt(Context context) {
        this.context = context;
    }

    public ZLoadingDialog show() {
        ZLoadingDialog dialog = new ZLoadingDialog(context);

        dialog.setLoadingBuilder(Z_TYPE.STAR_LOADING)//设置类型
                .setCanceledOnTouchOutside(false)
                .setLoadingColor(Color.parseColor("#FF6A92F1"))//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.parseColor("#FF6A92F1"))  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#00FFFFFF")) // 设置背景色，默认白色
                .show();

        return dialog;
    }
}
