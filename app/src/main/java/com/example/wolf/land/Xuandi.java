package com.example.wolf.land;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.adpater.xuandiadapter;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.view.annotation.ContentView;

import java.util.HashMap;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.xuandi)
public class Xuandi extends AppCompatActivity {
    Xutils xutils = new Xutils(Xuandi.this);
    @BindView(R.id.xuandifanhui)
    ImageView xuandifanhui;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.kuaishu)
    TextView kuaishu;
    @BindView(R.id.zhongjian)
    TextView zhongjian;
    @BindView(R.id.xuandirecyclerview)
    RecyclerView xuandirecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuandi);
        ButterKnife.bind(Xuandi.this);
        ZloadingDiaLogkt zloadingDiaLogkt = new ZloadingDiaLogkt(Xuandi.this);
        final ZLoadingDialog ZLoadingDialog = zloadingDiaLogkt.show();
        xutils.get(getResources().getString(R.string.getlandinfo), new HashMap<String, String>(), new Xutils.XCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil = new GsonUtil();
                List<Farminfo> Farminfo = gsonUtil.Gson(result, Farminfo.class);
                 xuandiadapter xuandiadapter = new xuandiadapter(R.layout.xuandiitem, Farminfo, Xuandi.this,buy);
                xuandiadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                xuandiadapter.isFirstOnly(false);
                xuandirecyclerview.setLayoutManager(new LinearLayoutManager(Xuandi.this));
                xuandirecyclerview.setAdapter(xuandiadapter);
                xuandiadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @SuppressWarnings("ConstantConditions")
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        TextView shuliang = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang);
                        TextView shuliang2 = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang2);
                        TextView shuliang3 = (TextView) adapter.getViewByPosition(xuandirecyclerview, position, R.id.shuliang3);
                        switch (view.getId()){

                            case R.id.jia:
                                String s = shuliang.getText().toString();
                                Integer sl1 = Integer.valueOf(s);
                                sl1++;
                                shuliang.setText(sl1+"");
                                break;
                            case R.id.jia2:
                                String s2 = shuliang2.getText().toString();
                                Integer sl2 = Integer.valueOf(s2);
                                sl2++;
                                shuliang2.setText(sl2+"");
                                break;
                            case R.id.jia3:
                                String s3 = shuliang3.getText().toString();
                                Integer sl3 = Integer.valueOf(s3);
                                sl3++;
                                shuliang3.setText(sl3+"");
                                break;
                            case R.id.jian:
                                String ss = shuliang.getText().toString();
                                Integer ssl = Integer.valueOf(ss);
                                ssl--;
                                if(ssl<=0){
                                    ssl=0;
                                }
                                shuliang.setText(ssl+"");
                                break;
                            case R.id.jian2:
                                String ss2 = shuliang2.getText().toString();
                                Integer ssl2 = Integer.valueOf(ss2);
                                ssl2--;
                                if(ssl2<=0){
                                    ssl2=0;
                                }
                                shuliang2.setText(ssl2+"");
                                break;
                            case R.id.jian3:
                                String ss3 = shuliang2.getText().toString();
                                Integer ssl3 = Integer.valueOf(ss3);
                                ssl3--;
                                if(ssl3<=0){
                                    ssl3=0;
                                }
                                shuliang3.setText(ssl3+"");
                                break;

                        }
                    }
                });

                ZLoadingDialog.dismiss();
            }
        });




        xuandifanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Xuandi.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
