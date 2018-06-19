package com.example.wolf.adpater;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolf.R;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.seed.ProdctBean;

import java.math.BigDecimal;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGridViewAdpterseed extends BaseAdapter {
    private Context context;
    private List<ProdctBean> lists;//数据源
    private BigDecimal moeny = new BigDecimal(0);
    private TextView allmoeny;
    private Button buy;
    private SharedPreferences mySharePerferences;
    private List<TextView> count = new ArrayList();
    private String url;
    private List<String> listsusse = new ArrayList<>();


    public MyGridViewAdpterseed(Context context, List<ProdctBean> lists, Button buy, SharedPreferences mySharePerferences, String url, TextView allmoeny) {
        this.context = context;
        this.lists = lists;
        this.buy = buy;
        this.mySharePerferences = mySharePerferences;
        this.url = url;
        this.allmoeny = allmoeny;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.seeditem, null);
            holder.k1 = convertView.findViewById(R.id.k1);
            holder.k2 = convertView.findViewById(R.id.k2);
            holder.k3 = convertView.findViewById(R.id.k3);
            holder.k4 = convertView.findViewById(R.id.k4);
            holder.k5 = convertView.findViewById(R.id.k5);
            holder.jian = convertView.findViewById(R.id.b1);
            holder.jia = convertView.findViewById(R.id.b2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.k1.setImageResource(lists.get(position).getImage());
        holder.k2.setText(lists.get(position).getName());
        holder.k3.setText("￥" + lists.get(position).getJiage());
        holder.k5.setText("剩余数量" + lists.get(position).getCount());
        count.add(holder.k4);
        jiaorjian(holder, position);
        buy();
        return convertView;
    }

    static class ViewHolder {
        public ImageView k1;
        public TextView k2;
        public TextView k3;
        public TextView k4;
        public TextView k5;
        public Button jian;
        public Button jia;


    }

    private void jiaorjian(final ViewHolder holder, final int pos) {

        holder.jia.setOnClickListener(new View.OnClickListener() {
            int b1;

            @Override
            public void onClick(View v) {
                b1 = Integer.valueOf(holder.k4.getText().toString());
                b1++;
                holder.k4.setText(this.b1 + "");
                BigDecimal b1 = new BigDecimal(lists.get(pos).getJiage());
                moeny = b1.add(moeny);
                allmoeny.setText(moeny.toString());

            }
        });
        holder.jian.setOnClickListener(new View.OnClickListener() {
            int b1;

            @Override
            public void onClick(View v) {
                if (Integer.valueOf(holder.k4.getText().toString()) > 0) {
                    b1 = Integer.valueOf(holder.k4.getText().toString());
                    b1--;
                    holder.k4.setText(b1 + "");
                    BigDecimal b1 = new BigDecimal(lists.get(pos).getJiage());
                    moeny = moeny.subtract(b1);
                    allmoeny.setText(moeny.toString());

                }
            }
        });
    }

    public void buy() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xutils xutils = new Xutils(context);
                Log.i("iiiiiiiiiiiiiii", "iii");

                //用getint获取值
                for (int i = 0; i < lists.size(); i++) {
                    int uid = mySharePerferences.getInt("uid", 0);
                    Map<String, String> map = new HashMap();
                    map.put("uid", String.valueOf(uid));
                    map.put("sid", String.valueOf(lists.get(i).getSid()));
                    map.put("buycount", count.get(i + 1).getText().toString());
                    Log.i("iiiiiiiiiiiii", count.get(i + 1).getText().toString());
                    map.put("token", new Token().getToken(uid));
                    final int finalI = i;
                    xutils.get(url, map, new Xutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            Log.i("iiiiiiiiiiii", result);
                            String su = result.substring(result.lastIndexOf("\"") - 7, result.lastIndexOf("\""));
                            listsusse.add(su);
                            if (listsusse.size() >= finalI) {
                                for (int i = 0; i < listsusse.size(); i++) {
                                    if (listsusse.get(i).equals("success")) {
                                        ToastUtils.showToast(context, "购买成功");

                                    }


                                }


                            }

                        }
                    });
                    if (lists.get(i).getCount() == 0 && Integer.valueOf(count.get(i + 1).getText().toString()) > 0) {
                        Log.i("iiiiiiiii", lists.get(i).getCount() + count.get(i + 1).getText().toString());
                        Toast.makeText(context, "购买失败，请检查网络或种子数量", Toast.LENGTH_LONG).show();
                        break;


                    }

                }

            }


        });

    }
}
