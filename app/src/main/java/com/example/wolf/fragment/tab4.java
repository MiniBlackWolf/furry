package com.example.wolf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wolf.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.tab4)
public class tab4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(tab4.this, inflater, container);
        return view;
    }
}
