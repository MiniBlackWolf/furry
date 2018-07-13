package com.example.wolf.seed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.MainActivity;
import com.example.wolf.R;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.ZloadingDiaLogkt;
import com.example.wolf.adpater.Seedadapter;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.seed)
public class seedActivity extends AppCompatActivity {
    @ViewInject(R.id.seedgridview)
    private RecyclerView seedgridview;
    @ViewInject(R.id.points)
    private LinearLayout group;
    @ViewInject(R.id.y1)
    private Button y1;
    @ViewInject(R.id.y2)
    private Button y2;
    @ViewInject(R.id.y3)
    private Button y3;
    @ViewInject(R.id.y4)
    private Button y4;
    @ViewInject(R.id.y5)
    private Button y5;
    @ViewInject(R.id.y6)
    private Button y6;
    @ViewInject(R.id.y7)
    private Button y7;
    @ViewInject(R.id.y8)
    private Button y8;
    @ViewInject(R.id.y9)
    private Button y9;
    @ViewInject(R.id.y10)
    private Button y10;
    @ViewInject(R.id.y11)
    private Button y11;
    @ViewInject(R.id.y12)
    private Button y12;
    @ViewInject(R.id.seedxuandifanhui)
    private ImageView seedxuandifanhui;
    @ViewInject(R.id.seedzhongjian)
    private TextView seedzhongjian;
    @ViewInject(R.id.seedbuy)
    private Button seedbuy;
    @ViewInject(R.id.q2)
    private ImageView q2;
    @ViewInject(R.id.q3)
    private ImageView q3;
    Xutils xutils = new Xutils(seedActivity.this);
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map;
    private ImageView[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 8; //每页显示的最大的数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private Seedadapter seedadapter;
    boolean Visibility=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(seedActivity.this);
        montesetlist();
        seedxuandifanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Visibility) {
                    q2.setVisibility(View.VISIBLE);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(q2, "scaleX", 0f,1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(q2, "scaleY", 0f,1f);
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(q2, "translationX", -200f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX,translationX,scaleY);
                    animatorSet.setDuration(500);
                    animatorSet.start();
                    Visibility=false;
                } else {
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(q2, "scaleX", 1f,0f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(q2, "scaleY", 1f,0f);
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(q2, "translationX", 200f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX,translationX,scaleY);
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation, boolean isReverse) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation, boolean isReverse) {
                            q2.setVisibility(View.GONE);
                        }
                    });
                    animatorSet.setDuration(500);
                    animatorSet.start();
                    Visibility=true;

                }
            }
        });

    }

    private void montesetlist() {
        //根据日期改变种子数据
        List<ProdctBean> listDatas = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        setgridview(month, listDatas);
        List<Button> list = new ArrayList<>();
        list.add(y1);
        list.add(y2);
        list.add(y3);
        list.add(y4);
        list.add(y5);
        list.add(y6);
        list.add(y7);
        list.add(y8);
        list.add(y9);
        list.add(y10);
        list.add(y11);
        list.add(y12);
        list.get(month - 1).setBackground(getResources().getDrawable(R.mipmap.y123));
        list.get(month - 1).setTextColor(Color.parseColor("#ffffff"));
        y1.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y1.setTextColor(Color.parseColor("#666666"));

    }

    //1-12月监听器
    public void doclick(View v) {
        y1.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y2.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y3.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y4.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y5.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y6.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y7.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y8.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y9.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y10.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y11.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y12.setBackground(getResources().getDrawable(R.mipmap.y1234));
        y1.setTextColor(Color.parseColor("#666666"));
        y2.setTextColor(Color.parseColor("#666666"));
        y3.setTextColor(Color.parseColor("#666666"));
        y4.setTextColor(Color.parseColor("#666666"));
        y5.setTextColor(Color.parseColor("#666666"));
        y6.setTextColor(Color.parseColor("#666666"));
        y7.setTextColor(Color.parseColor("#666666"));
        y8.setTextColor(Color.parseColor("#666666"));
        y9.setTextColor(Color.parseColor("#666666"));
        y10.setTextColor(Color.parseColor("#666666"));
        y11.setTextColor(Color.parseColor("#666666"));
        y12.setTextColor(Color.parseColor("#666666"));
        switch (v.getId()) {
            case R.id.y1: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y1.setBackground(getResources().getDrawable(R.mipmap.y123));
                y1.setTextColor(Color.parseColor("#ffffff"));
                setgridview(1, listDatas);
                break;
            }
            case R.id.y2: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y2.setBackground(getResources().getDrawable(R.mipmap.y123));
                y2.setTextColor(Color.parseColor("#ffffff"));
                setgridview(2, listDatas);
                break;
            }
            case R.id.y3: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y3.setBackground(getResources().getDrawable(R.mipmap.y123));
                y3.setTextColor(Color.parseColor("#ffffff"));
                setgridview(3, listDatas);
                break;
            }
            case R.id.y4: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y4.setBackground(getResources().getDrawable(R.mipmap.y123));
                y4.setTextColor(Color.parseColor("#ffffff"));
                setgridview(4, listDatas);
                break;
            }
            case R.id.y5: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y5.setBackground(getResources().getDrawable(R.mipmap.y123));
                y5.setTextColor(Color.parseColor("#ffffff"));
                setgridview(5, listDatas);
                break;
            }
            case R.id.y6: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y6.setBackground(getResources().getDrawable(R.mipmap.y123));
                y6.setTextColor(Color.parseColor("#ffffff"));
                setgridview(6, listDatas);
                break;
            }
            case R.id.y7: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y7.setBackground(getResources().getDrawable(R.mipmap.y123));
                y7.setTextColor(Color.parseColor("#ffffff"));
                setgridview(7, listDatas);
                break;
            }
            case R.id.y8: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y8.setBackground(getResources().getDrawable(R.mipmap.y123));
                y8.setTextColor(Color.parseColor("#ffffff"));
                setgridview(8, listDatas);
                break;
            }
            case R.id.y9: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y9.setBackground(getResources().getDrawable(R.mipmap.y123));
                y9.setTextColor(Color.parseColor("#ffffff"));
                setgridview(9, listDatas);
                break;
            }
            case R.id.y10: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y10.setBackground(getResources().getDrawable(R.mipmap.y123));
                y10.setTextColor(Color.parseColor("#ffffff"));
                setgridview(10, listDatas);
                break;
            }
            case R.id.y11: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y11.setBackground(getResources().getDrawable(R.mipmap.y123));
                y11.setTextColor(Color.parseColor("#ffffff"));
                setgridview(11, listDatas);
                break;
            }
            case R.id.y12: {
                List<ProdctBean> listDatas = new ArrayList<>();
                y12.setBackground(getResources().getDrawable(R.mipmap.y123));
                y12.setTextColor(Color.parseColor("#ffffff"));
                setgridview(12, listDatas);
                break;
            }


        }


    }

    //改变数据方法
    public void setgridview(final int month, final List<ProdctBean> listDatas) {
        final ZloadingDiaLogkt zloadingDiaLogkt = new ZloadingDiaLogkt(seedActivity.this);
        final ZLoadingDialog show = zloadingDiaLogkt.show();
        xutils.get(getResources().getString(R.string.Seedscount), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                //截取总页数
                final String s = result.substring(result.lastIndexOf("}") - 1, result.lastIndexOf("}"));
                //循环获取每页数据《Seed》
                for (int d = 1; d <= Integer.valueOf(s); d++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("currentPage", String.valueOf(d));
                    final int finalD = d;
                    xutils.get(getResources().getString(R.string.Seeds), map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            Log.i("iiiiiiii", result);
                            //解析数据到gson
                            GsonUtil gsonUtil = new GsonUtil();
                            List<Seed> seedlist = gsonUtil.Gson(result, Seed.class);
                            if (seedlist != null) {
                                List<Seed> yue = new ArrayList<>();
                                for (int i = 0; i < seedlist.size(); i++) {
                                    //判断月数，获取当月数据。
                                    int show = ((seedlist.get(i).getSowingend() - seedlist.get(i).getSowingbegin()));

                                    for (int d = 0; d <= show; d++) {
                                        seedlist.get(i).setSowingbegin((byte) (seedlist.get(i).getSowingbegin() + d));
                                        if (seedlist.get(i).getSowingbegin() == month) {
                                            yue.add(seedlist.get(i));


                                        }
                                        GsonUtil gsonUtils = new GsonUtil();
                                        List<Seed> seedlists = gsonUtils.Gson(result, Seed.class);

                                        seedlist.get(i).setSowingbegin((seedlists.get(i).getSowingbegin()));
                                    }

                                }

                                //循环加数据到listDatas《prodctBean》
                                for (int i = 0; i < yue.size(); i++)

                                {
                                    ProdctBean prodctBean = new ProdctBean();
                                    prodctBean.setImage(yue.get(i).getFileurl());
                                    prodctBean.setName(yue.get(i).getSeedname());
                                    prodctBean.setJiage(String.valueOf(yue.get(i).getMoney()));
                                    prodctBean.setCount(yue.get(i).getCount());
                                    prodctBean.setDescription(yue.get(i).getDescription());
                                    prodctBean.setSid(yue.get(i).getSid());
                                    listDatas.add(prodctBean);

                                }
                                // if( Integer.valueOf(s)==finalD) {
                                SharedPreferences mySharePerferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                                Seedadapter seedadapter = new Seedadapter(R.layout.seeditem, listDatas, seedActivity.this, seedzhongjian, seedbuy, show);
                                seedadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                        new seedpop(getLayoutInflater(), (ImageView) findViewById(view.getId()), listDatas.get(position), seedActivity.this).setPopupWindow();
                                    }
                                });
                                Log.i("iiiiiiiiiiii", String.valueOf(listDatas.size()));
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(seedActivity.this, 3);
                                seedgridview.setLayoutManager(gridLayoutManager);
                                seedgridview.setAdapter(seedadapter);
                                seedadapter.bindToRecyclerView(seedgridview);
                                seedadapter.setEmptyView(R.layout.loading);
                                seedadapter.notifyDataSetChanged();


                                //  }
                            }
                        }
                    });


                }
            }
        });
    }

//    private void grid(List<ProdctBean> listDatas) {
//
//
//        initData(listDatas);
//        viewPager.setAdapter(new
//
//                MyViewPagerAdapter(viewPagerList));
//    }


//    private void initData(List<ProdctBean> listDatas) {
//        // TODO Auto-generated method stub
//        //总的页数向上取整
//        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
//        viewPagerList = new ArrayList<View>();
//        for (int i = 0; i < totalPage; i++) {
//            //每个页面都是inflate出一个新实例
//            final GridView gridView = (GridView) View.inflate(this, R.layout.gridviewitem, null);
//            SharedPreferences mySharePerferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
//            //添加item点击监听
//            mGridViewAdpter = new MyGridViewAdpter(this, listDatas, i, mPageSize, getLayoutInflater(), seedzhongjian, seedbuy, getResources().getString(R.string.buyseed), mySharePerferences
//            );
//            gridView.setAdapter(mGridViewAdpter);
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View arg1,
//                                        int position, long arg3) {
//                    // TODO Auto-generated method stub
//                    Object obj = gridView.getItemAtPosition(position);
//                    if (obj != null && obj instanceof ProdctBean) {
//                        System.out.println(obj);
//                        Toast.makeText(seedActivity.this, ((ProdctBean) obj).getName(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//            //每一个GridView作为一个View对象添加到ViewPager集合中
//            viewPagerList.add(gridView);
//        }
//        //设置ViewPager适配器
//        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
//
//        //添加小圆点
//        ivPoints = new ImageView[totalPage];
//        for (int i = 0; i < totalPage; i++) {
//            //循坏加入点点图片组
//            ivPoints[i] = new ImageView(this);
//            if (i == 0) {
//                ivPoints[i].setImageResource(R.mipmap.t1);
//            } else {
//                ivPoints[i].setImageResource(R.mipmap.t2);
//            }
//            ivPoints[i].setPadding(8, 8, 8, 8);
//            group.addView(ivPoints[i]);
//        }
//        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
//        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                // TODO Auto-generated method stub
//                //currentPage = position;
//                for (int i = 0; i < totalPage; i++) {
//                    if (i == position) {
//                        ivPoints[i].setImageResource(R.mipmap.t1);
//                    } else {
//                        ivPoints[i].setImageResource(R.mipmap.t2);
//                    }
//                }
//            }
//        });
//    }


}
