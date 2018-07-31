package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.chat.Msg;

import java.util.List;

public class chatadapter extends BaseMultiItemQuickAdapter<Msg, BaseViewHolder> {
    private Context context;


    public chatadapter(@Nullable List<Msg> data,Context context) {
        super(data);
        this.context=context;
        addItemType(Msg.TYPE_RECEIVED, R.layout.chatitem2);
        addItemType(Msg.TYPE_SENT, R.layout.chatitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, Msg item) {

        switch (helper.getItemViewType()) {
            case Msg.TYPE_RECEIVED:
                helper.setText(R.id.chatmsg,item.getContent()+"");
                break;
            case Msg.TYPE_SENT:
                helper.setText(R.id.chatname,new Getuserinfo(context).getusername());
                helper.setText(R.id.chatmsg,item.getContent()+"");
                break;
        }

}

//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == Msg.getTypeReceived()) {
//            View view =LayoutInflater.inflate(R.layout.chatitem, parent, false);
//            return new BaseViewHolder(view);
//        } else {
//            View view = LayoutInflater.inflate(R.layout.chatitem2, parent, false);
//            return new BaseViewHolder(view);
//        }
//    }
}
