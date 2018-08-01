package com.example.wolf.chat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.adpater.chatadapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatitemActivity extends Activity {


    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.chatrecyclerview)
    RecyclerView chatrecyclerview;
    @BindView(R.id.chateditText)
    EditText chateditText;
    @BindView(R.id.chatbutton)
    Button chatbutton;
    List<Msg> msg1 = new ArrayList<>();
    chatadapter chatadapter;
    //0接收消息1发送
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);
        ButterKnife.bind(this);
     //   Msg m = new Msg("噼噼啪啪铺", 1);
        Msg m2 = new Msg("菜鸟环保客服竭诚为您服务", 0);
     //   msg1.add(m);
        msg1.add(m2);
         chatadapter = new chatadapter(msg1,ChatitemActivity.this);
        chatrecyclerview.setLayoutManager(new LinearLayoutManager(ChatitemActivity.this));
        chatrecyclerview.setAdapter(chatadapter);


    }


    @OnClick({R.id.chatfh, R.id.chatbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatfh:
                finish();
                overridePendingTransition(0,0);
                break;
            case R.id.chatbutton:
                if(chateditText.getText().toString().isEmpty()){
                    ToastUtils.showToast(ChatitemActivity.this,"请输入文字");
                    break;

                }
                Msg m = new Msg(chateditText.getText().toString(),1);
                chatadapter.addData(m);
                chateditText.setText("");
                chatrecyclerview.smoothScrollToPosition(chatadapter.getItemCount()-1);
                break;
        }
    }
}
