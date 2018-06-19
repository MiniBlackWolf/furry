package com.example.wolf.me_order;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.Token;
import com.example.wolf.adpater.Myorderadapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.orderrecyclerview)
    RecyclerView orderrecyclerview;
    Xutils xutils = new Xutils(OrderActivity.this);
    Getuserinfo getuserinfo = new Getuserinfo(OrderActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        ButterKnife.bind(this);
        Map<String, String> map = new HashMap();
        map.put("uid", String.valueOf(getuserinfo.getuid()));
        map.put("token", new Token().getToken(getuserinfo.getuid()));
        xutils.get(getResources().getString(R.string.getUserOrder), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil=new GsonUtil();
                List orderbean = gsonUtil.Gson(result, orderbean.class);
                Myorderadapter myorderadapter = new Myorderadapter(R.layout.orderitem, orderbean, OrderActivity.this);
                myorderadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                myorderadapter.isFirstOnly(false);
                orderrecyclerview.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                orderrecyclerview.setAdapter(myorderadapter);

            }
        });

    }

    @OnClick(R.id.odfh)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, 0);
    }
}
