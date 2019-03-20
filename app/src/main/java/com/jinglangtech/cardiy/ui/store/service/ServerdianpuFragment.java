package com.jinglangtech.cardiy.ui.store.service;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinglangtech.cardiy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServerdianpuFragment extends Fragment {
    private View view;
    private RecyclerView recyclerview;

    public ServerdianpuFragment() {
        // Required empty public constructor
    }

    public static ServerdianpuFragment newInstance(String s) {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_serverdianpu, null);
        recyclerview= view.findViewById(R.id.recyclerview);
        return view;
    }

}
