package com.example.wolf.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.adpater.chatadapter;
import com.example.wolf.delivery.delivery;
import com.example.wolf.fragment.tab3;
import com.google.gson.Gson;
import com.zhangke.websocket.AbsWebSocketActivity;
import com.zhangke.websocket.ErrorResponse;
import com.zhangke.websocket.Response;
import com.zhangke.websocket.WebSocketService;
import com.zhangke.websocket.WebSocketServiceConnectManager;
import com.zhangke.websocket.WebSocketSetting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatitemActivity extends AbsWebSocketActivity {


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
    private WebSocketServiceConnectManager mConnectManager;
    Response me;
    //0接收消息1发送
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);
        ButterKnife.bind(this);
        WebSocketSetting.setConnectUrl("ws://192.168.0.101:8080/Backstage/ws.do");//必选
        //   WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);
        startService(new Intent(ChatitemActivity.this, WebSocketService.class));
        mConnectManager = new WebSocketServiceConnectManager(ChatitemActivity.this, ChatitemActivity.this);
        mConnectManager.onCreate();
        //   Msg m = new Msg("噼噼啪啪铺", 1);
        Msg m2 = new Msg("菜鸟环保客服竭诚为您服务", 0);
        //   msg1.add(m);
        msg1.add(m2);
        chatadapter = new chatadapter(msg1, ChatitemActivity.this);
        chatrecyclerview.setLayoutManager(new LinearLayoutManager(ChatitemActivity.this));
        chatrecyclerview.setAdapter(chatadapter);


    }


    @OnClick({R.id.chatfh, R.id.chatbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatfh:
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.chatbutton:
                if (chateditText.getText().toString().isEmpty()) {
                    ToastUtils.showToast(ChatitemActivity.this, "请输入文字");
                    break;
                }
                Msg m = new Msg(chateditText.getText().toString(), 1);
                chatadapter.addData(m);
                chatrecyclerview.smoothScrollToPosition(chatadapter.getItemCount() - 1);
                Gson gson = new Gson();
                Message message = new Message();
                message.setFrom("1");
                message.setFromName("cw");
                message.setTo("root");
                message.setText(chateditText.getText().toString());
                message.setDate(new Date(System.currentTimeMillis()/1000));
                sendText(gson.toJson(message));
                chateditText.setText("");
                break;
        }
    }

    @Override
    public void sendText(String text) {
        mConnectManager.sendText(text);
    }

    @Override
    public void onMessageResponse(Response response) {
        if(response.equals(me)){
            return;
        }
        me=response;
        Gson gson=new Gson();
        Message message=gson.fromJson(response.getResponseText(),Message.class);
        Msg m = new Msg(message.getText(), 0);
        chatadapter.addData(m);
        chatrecyclerview.smoothScrollToPosition(chatadapter.getItemCount() - 1);

    }

    @Override
    public void onSendMessageError(ErrorResponse error) {
        switch (error.getErrorCode()) {
            case 1:
                error.setDescription("网络错误");
                break;
            case 2:
                error.setDescription("网络错误");
                break;
            case 3:
                error.setDescription("网络错误");
                break;
            case 11:
                error.setDescription("数据格式异常");
                Log.e(LOGTAG, "数据格式异常", error.getCause());
                break;
        }


    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}
