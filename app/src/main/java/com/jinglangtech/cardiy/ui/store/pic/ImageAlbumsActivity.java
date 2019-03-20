package com.jinglangtech.cardiy.ui.store.pic;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.adapter.MyViewPageAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ImageAlbumsActivity extends AppCompatActivity {

    private ImageView mBackImg;
    private TextView mToolbarTitle;
    private TextView mToolbarRightText;
    private ImageView mToolbarRightImg;
    /**
     * map_go
     */
    private TextView mHeadGo;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mVpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_albums);
        initView();
        initData();
    }

    private void initView() {


        mBackImg = (ImageView) findViewById(R.id.back_img);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarRightText = (TextView) findViewById(R.id.toolbar_right_text);
        mToolbarRightImg = (ImageView) findViewById(R.id.toolbar_right_img);
        mHeadGo = (TextView) findViewById(R.id.head_go);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mVpView = (ViewPager) findViewById(R.id.vp_view);
    }

    private void initData() {
        ArrayList<String> titleDatas = new ArrayList<>();
        titleDatas.add("全部");
        titleDatas.add("展厅");
        titleDatas.add("客休");
        titleDatas.add("车间");
        titleDatas.add("外景");

        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Whole_pictureFragment());
        fragmentList.add(new Livingroom_pictureFragment());
        fragmentList.add(new Outdoorscene_pictureFragment());
        fragmentList.add(new Gueststop_pictureFragment());

        fragmentList.add(new Workshop_pictureFragment());

        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getSupportFragmentManager(), titleDatas, fragmentList);
        mVpView.setAdapter(myViewPageAdapter);
        mTabLayout.setupWithViewPager(mVpView);
        mTabLayout.setTabsFromPagerAdapter(myViewPageAdapter);

    }

}
