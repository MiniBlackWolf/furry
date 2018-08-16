package com.example.wolf.adpater;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.R;
import com.example.wolf.StartActivity;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.cultivation.genyun;
import com.example.wolf.pointshop.PointshopActivity;
import com.example.wolf.pointshop.pointbean;
import com.example.wolf.pointshop.pointbuybean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pointadapter extends BaseQuickAdapter<pointbean, BaseViewHolder> {
    private Context context;
    private Button okpointshop;
    private List<pointbuybean.ItemBean> ItemBeans = new ArrayList<>();
    private Set<TextView> counts = new LinkedHashSet<>();
    private Set<pointbean> pointbeans = new LinkedHashSet<>();
    private Set<TextView> moneys = new LinkedHashSet<>();
    private List<TextView> listcounts = new ArrayList<>();
    private List<pointbean> listpointbeans = new ArrayList<>();
    private List<TextView> listmoneys = new ArrayList<>();
    private double ALlmoney;
    private Integer Allcount=0;

    public pointadapter(int layoutResId, @Nullable List<pointbean> data, Context context, Button okpointshop) {
        super(layoutResId, data);
        this.context = context;
        this.okpointshop = okpointshop;
    }

    @Override
    protected void convert(BaseViewHolder helper, pointbean item) {
        Glide.with(context).load(item.getSimgui()).centerCrop().placeholder(R.mipmap.loading2).into((ImageView) helper.getView(R.id.pointimg));
        helper.setText(R.id.pointname, item.getSname() + "");
        helper.setText(R.id.pointjies, item.getScontent() + "");
        helper.setText(R.id.pointmoney, item.getSprice() + "");
        helper.addOnClickListener(R.id.pointjian);
        helper.addOnClickListener(R.id.pointjia);
        counts.add((TextView) helper.getView(R.id.pointcount));
        pointbeans.add(item);
        moneys.add((TextView) helper.getView(R.id.pointmoney));
        okpointshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("购买提醒");
                alertDialog.setMessage("您确定要购买吗？");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        listcounts.addAll(counts);
                        listpointbeans.addAll(pointbeans);
                        listmoneys.addAll(moneys);
                        for (int i = 0; i < listpointbeans.size(); i++) {
                            if (Integer.valueOf(listcounts.get(i).getText().toString()) != 0) {
                                pointbuybean.ItemBean itemBean = new pointbuybean.ItemBean();
                                itemBean.setSid(listpointbeans.get(i).getId());
                                itemBean.setSprice(Double.valueOf(listmoneys.get(i).getText().toString()));
                                itemBean.setSnums(Integer.valueOf(listcounts.get(i).getText().toString()));
                                itemBean.setSname(listpointbeans.get(i).getSname());
                                itemBean.setStype(listpointbeans.get(i).getSid());
                                itemBean.setSland(listpointbeans.get(i).getStype());
                                ItemBeans.add(itemBean);
                                ALlmoney += Double.valueOf(listmoneys.get(i).getText().toString())*Integer.valueOf(listcounts.get(i).getText().toString());
                                Allcount += Integer.valueOf(listcounts.get(i).getText().toString());
                            }
                            else {
                                ToastUtils.showToast(context,"请选择购买物品!");
                                return;
                            }

                        }
                        pointbuybean pointbuybean = new pointbuybean();
                        pointbuybean.setUserid(new Getuserinfo(context).getuid());
                        pointbuybean.setTotle(ALlmoney);
                        pointbuybean.setNums(Allcount);
                        pointbuybean.setItem(ItemBeans);
                        Gson gson = new Gson();
                        String s = gson.toJson(pointbuybean);
                        RequestParams requestParams = new RequestParams(context.getResources().getString(R.string.butscore));
                        requestParams.setBodyContent(s);
                        requestParams.setAsJsonContent(true);
                        x.http().post(requestParams, new Callback.CommonCallback<String>() {

                            @Override
                            public void onSuccess(String result) {
                                Pattern success1 = Pattern.compile("success");
                                Matcher matcher = success1.matcher(result);
                                if (matcher.find()) {
                                    ToastUtils.showToast(context, "购买成功!");
                                    Intent intent=new Intent(context,PointshopActivity.class);
                                    context.startActivity(intent);

                                }
                                Pattern success = Pattern.compile("积分不足");
                                Matcher matchers = success.matcher(result);
                                if(matchers.find()){
                                    ToastUtils.showToast(context, "购买失败!，积分不足！");
                                    Intent intent=new Intent(context,PointshopActivity.class);
                                    context.startActivity(intent);
                                }

                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                ToastUtils.showToast(context, "购买失败!，请检查网络！");
                                Intent intent=new Intent(context,PointshopActivity.class);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();

            }
        });
    }
}
