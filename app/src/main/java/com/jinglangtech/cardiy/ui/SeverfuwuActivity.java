package com.jinglangtech.cardiy.ui;


import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.adapter.MyViewPageAdapter;
import com.jinglangtech.cardiy.utils.StatusBarUtil;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SeverfuwuActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager vp_view;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_severfuwu);
        tabLayout = findViewById(R.id.tabLayout);
        vp_view = findViewById(R.id.vp_view);
       


        vp_view = findViewById(R.id.vp_view);

        StatusBarUtil.transportStatus(this);

        ArrayList<String> titleDatas=new ArrayList<>();
        titleDatas.add("服务设施");
        titleDatas.add("技术力量");
        titleDatas.add("历史荣誉");
        titleDatas.add("店铺简介");
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new ServersheshiFragment());
        fragmentList.add(new ServerliliangFragment());
        fragmentList.add(new ServerrongyuFragment());
        fragmentList.add(new ServerdianpuFragment());
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getSupportFragmentManager(), titleDatas,        fragmentList);
        vp_view.setAdapter(myViewPageAdapter);
        tabLayout.setupWithViewPager(vp_view);
        tabLayout.setTabsFromPagerAdapter(myViewPageAdapter);


    }
    }