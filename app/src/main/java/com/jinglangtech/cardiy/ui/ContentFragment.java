package com.jinglangtech.cardiy.ui;


import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinglangtech.cardiy.R;

import androidx.fragment.app.Fragment;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    private View view;
    private TextView tvContent;

    public ContentFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.contentfragment,container,false);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        String string = getArguments().getString(KEY);
        tvContent.setText(string);
        tvContent.setTextColor(Color.BLUE);
        tvContent.setTextSize(30);
        return view;
    }

    /**
     * fragment静态传值
     */
    public static ContentFragment newInstance(String str){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,str);
        fragment.setArguments(bundle);

        return fragment;
    }

}
