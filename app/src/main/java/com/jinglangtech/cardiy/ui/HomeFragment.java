package com.jinglangtech.cardiy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jinglangtech.cardiy.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private String mFrom;
    private LinearLayout fuwu;
    private View view;
    private ImageView dptp;
    private LinearLayout dizhi;

    public static HomeFragment newInstance(String from) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initView();
        initData();
        return view;
    }

    private void initData() {

    }

    private void initView() {
        fuwu = view.findViewById(R.id.fuwu);
       dptp = view.findViewById(R.id.dptp);
        dizhi = view.findViewById(R.id.dizhi);

        //服务设施
        fuwu.setOnClickListener(v -> startActivity(new Intent(getContext(), SeverfuwuActivity.class)));
        //店铺图片
       dptp.setOnClickListener(v -> startActivity(new Intent(getContext(), ImageAlbumsActivity.class)));
        //店铺地址
        dizhi.setOnClickListener(v -> startActivity(new Intent(getContext(), BdmapActivity.class)));

    }
}
