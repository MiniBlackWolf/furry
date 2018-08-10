package com.example.wolf.chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Constraints;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ToastUtils;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.adpater.chatadapter;
import com.google.gson.Gson;
import com.zhangke.websocket.AbsWebSocketActivity;
import com.zhangke.websocket.ErrorResponse;
import com.zhangke.websocket.Response;
import com.zhangke.websocket.WebSocketService;
import com.zhangke.websocket.WebSocketServiceConnectManager;
import com.zhangke.websocket.WebSocketSetting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    List<Customer> s;
    //0接收消息1发送
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);
        ButterKnife.bind(this);
        WebSocketSetting.setConnectUrl(getResources().getString(R.string.ws));//必选
        //   WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);
        startService(new Intent(ChatitemActivity.this, WebSocketService.class));
        mConnectManager = new WebSocketServiceConnectManager(ChatitemActivity.this, ChatitemActivity.this);
        mConnectManager.onCreate();
        //   Msg m = new Msg("噼噼啪啪铺", 1);
        Msg m2 = new Msg("菜鸟农场客服竭诚为您服务", 0);
        //   msg1.add(m);
        msg1.add(m2);
        chatadapter = new chatadapter(msg1, ChatitemActivity.this);
        chatrecyclerview.setLayoutManager(new LinearLayoutManager(ChatitemActivity.this));
        chatrecyclerview.setAdapter(chatadapter);
        Xutils xutils = new Xutils(ChatitemActivity.this);
        xutils.get(getResources().getString(R.string.online), new HashMap<String, String>(), new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil=new GsonUtil();
                s=gsonUtil.Gson(result,Customer.class);
                Log.i("iiiiii",result);
                Random random=new Random();
                if(s.size()==0){
                    i=0;
                    return;
                }
                 i = random.nextInt(s.size());
            }
        });

    }
private class Customer{
        private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

    @OnClick({R.id.chatfh, R.id.chatbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatfh:
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.chatbutton:
                if(s.isEmpty()){
                    ToastUtils.showToast(ChatitemActivity.this,"网络错误或没有在线客服!");
                    break;
                }
                if (chateditText.getText().toString().isEmpty()) {
                    ToastUtils.showToast(ChatitemActivity.this, "请输入文字");
                    break;
                }
                Msg m = new Msg(chateditText.getText().toString(), 1);
                chatadapter.addData(m);
                chatrecyclerview.smoothScrollToPosition(chatadapter.getItemCount() - 1);
                Gson gson = new Gson();
                Message message = new Message();
                message.setFrom(String.valueOf(new Getuserinfo(ChatitemActivity.this).getuid()));
                message.setFromName(new Getuserinfo(ChatitemActivity.this).getusername());
                message.setTo(s.get(i).getUsername());
                message.setText(chateditText.getText().toString());
                message.setDate(new Date(System.currentTimeMillis() / 1000));
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
        if (response.equals(me)) {
            return;
        }
        me = response;
        Gson gson = new Gson();
        Message message = gson.fromJson(response.getResponseText(), Message.class);
        Msg m = new Msg(message.getText(), 0);
        chatadapter.addData(m);
        chatrecyclerview.smoothScrollToPosition(chatadapter.getItemCount() - 1);

    }

    @Override
    public void onSendMessageError(ErrorResponse error) {
        switch (error.getErrorCode()) {
            case 1:
                error.setDescription("网络错误");
                ToastUtils.showToast(ChatitemActivity.this, "网络错误");
                break;
            case 2:
                error.setDescription("网络错误");
                ToastUtils.showToast(ChatitemActivity.this, "网络错误");
                break;
            case 3:
                error.setDescription("网络错误");
                ToastUtils.showToast(ChatitemActivity.this, "网络错误");
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
        overridePendingTransition(0, 0);
    }
}
