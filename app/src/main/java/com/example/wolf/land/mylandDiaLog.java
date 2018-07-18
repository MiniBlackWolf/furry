package com.example.wolf.land;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.adpater.MylandShoveladapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mylandDiaLog extends DialogFragment {
    Xutils xutils = new Xutils(getActivity());
    RecyclerView Shovelview;
    Button Shovelbutton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(),R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.mylanddialoglayout);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.MyDialog);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylanddialoglayout, container, false);
         Shovelview = view.findViewById(R.id.Shovelview);
        Shovelbutton  =view.findViewById(R.id.Shovelbutton);
        Map<String,String> map=new HashMap<>();
        map.put("uid", String.valueOf(new Getuserinfo(getActivity()).getuid()));
        map.put("fid",getTag());
        xutils.get(getResources().getString(R.string.shovelview), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                GsonUtil gsonUtil=new GsonUtil();
                List<userseedandland> userseedandland = gsonUtil.Gson(result, userseedandland.class);
                MylandShoveladapter mylandShoveladapter=new MylandShoveladapter(R.layout.mylanddialogitem,userseedandland,getActivity(),Shovelbutton);
                mylandShoveladapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                mylandShoveladapter.isFirstOnly(false);
                mylandShoveladapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ImageView chanzi = view.findViewById(R.id.chanzi);
                        int visibility = chanzi.getVisibility();
                        if (visibility == View.GONE) {
                            chanzi.setVisibility(View.VISIBLE);
                        } else {
                            chanzi.setVisibility(View.GONE);

                        }
                    }
                });
                Shovelview.setLayoutManager(new GridLayoutManager(getActivity(),3));
                Shovelview.setAdapter(mylandShoveladapter);
                mylandShoveladapter.bindToRecyclerView(Shovelview);
                mylandShoveladapter.setEmptyView(R.layout.loading);


         //       Log.i("iiiiiiiiiiii",result);
            }
        });
        return view;
    }
}
