package com.example.wolf.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wolf.atlas.Userseedimg;

import java.util.List;

public class Atlasadapter  extends BaseQuickAdapter<Userseedimg,BaseViewHolder>{
  private Context context;

    public Atlasadapter(int layoutResId, @Nullable List<Userseedimg> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Userseedimg item) {

    }
}
