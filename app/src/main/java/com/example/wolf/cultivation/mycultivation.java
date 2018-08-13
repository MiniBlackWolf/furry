package com.example.wolf.cultivation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.adpater.Mycultivationadapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class mycultivation extends AppCompatActivity {

    @BindView(R.id.cufh)
    ImageView mygyfh;
    @BindView(R.id.curecyclerview)
    RecyclerView curecyclerview;
    Xutils xutils = new Xutils(mycultivation.this);
    Getuserinfo getuserinfo = new Getuserinfo(mycultivation.this);
    @BindView(R.id.full)
    ConstraintLayout full;
    int uid;
    List<String> listseed;
    int x;
    @BindView(R.id.smdmy)
    TextView smdmy;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycultivation);
        ButterKnife.bind(mycultivation.this);
        uid = new Getuserinfo(mycultivation.this).getuid();
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        xutils.get(getResources().getString(R.string.getTickets), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.i("iiiiiiiiii", result);
                Pattern compile = Pattern.compile("fail");
                Matcher matcher = compile.matcher(result);
                if (matcher.find()) {
                    return;
                }
                GsonUtil gsonUtil = new GsonUtil();
                final List<usercultivation> usercultivation = gsonUtil.Gson(result, usercultivation.class);
                if (usercultivation == null) {
                    smdmy.setVisibility(View.VISIBLE);
                    return;
                }
                Collections.sort(usercultivation, new Comparator<com.example.wolf.cultivation.usercultivation>() {
                    @Override
                    public int compare(usercultivation t1, usercultivation t2) {
                        if (t1.getTid() > t2.getTid()) {
                            return 1;
                        } else {
                            return -1;

                        }
                    }
                });
                Log.i("iiiiiiii", usercultivation.toString());
                Mycultivationadapter mycultivationadapter = new Mycultivationadapter(R.layout.mycultivationitem, usercultivation, mycultivation.this);
                mycultivationadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mycultivationadapter.isFirstOnly(false);
                mycultivationadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                      TextView cuname= (TextView) adapter.getViewByPosition(curecyclerview,position,R.id.cuname);
                      TextView cucount= (TextView) adapter.getViewByPosition(curecyclerview,position,R.id.cucount);
                        switch (cuname.getText().toString()) {
                            case "菜鸟环保|播种套餐券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("count", Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog mycultivationdialog2 = new mycultivationdialog();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "0");
                                break;
                            }
                            case "菜鸟环保|灌溉券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", 1);
                                bundle.putInt("count", Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog2 mycultivationdialog2 = new mycultivationdialog2();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "1");
                                break;
                            }
                            case "菜鸟环保|农家肥券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", 2);
                                bundle.putInt("count",Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog2 mycultivationdialog2 = new mycultivationdialog2();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "2");
                                break;
                            }
                            case "菜鸟环保|除草券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", 3);
                                bundle.putInt("count", Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog2 mycultivationdialog2 = new mycultivationdialog2();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "3");
                                break;
                            }
                            case "菜鸟环保|灭虫券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", 4);
                                bundle.putInt("count", Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog2 mycultivationdialog2 = new mycultivationdialog2();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "4");
                                break;
                            }
                            case "菜鸟环保|有机肥券": {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", 5);
                                bundle.putInt("count", Integer.parseInt(cucount.getText().toString().substring(0,1)));
                                mycultivationdialog2 mycultivationdialog2 = new mycultivationdialog2();
                                mycultivationdialog2.setArguments(bundle);
                                mycultivationdialog2.show(getSupportFragmentManager(), "5");
                                break;
                            }


                        }
                    }
                });
                curecyclerview.setLayoutManager(new LinearLayoutManager(mycultivation.this));
                curecyclerview.setAdapter(mycultivationadapter);
            }
        });
    }


    @OnClick(R.id.cufh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, 0);
    }
}
