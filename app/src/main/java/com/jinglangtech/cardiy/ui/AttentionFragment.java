package com.jinglangtech.cardiy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinglangtech.cardiy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class AttentionFragment extends Fragment {


    private String mFrom;
    private Toolbar title;

    public static AttentionFragment newInstance(String from) {
        AttentionFragment fragment = new AttentionFragment();
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
        View view = inflater.inflate(R.layout.fragment_attentiont, null);

        return view;
    }
}
