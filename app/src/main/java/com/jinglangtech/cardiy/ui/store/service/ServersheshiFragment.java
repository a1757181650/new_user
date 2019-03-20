package com.jinglangtech.cardiy.ui.store.service;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinglangtech.cardiy.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServersheshiFragment extends Fragment {


    private View view;
    private RecyclerView recyclerview;

    public ServersheshiFragment() {
        // Required empty public constructor
    }

    public static ServersheshiFragment newInstance(String s) {

        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_serversheshi, null);
        recyclerview= view.findViewById(R.id.recyclerview);

        return view;
    }

}
