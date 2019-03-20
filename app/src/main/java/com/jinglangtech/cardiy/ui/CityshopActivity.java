package com.jinglangtech.cardiy.ui;

import android.os.Bundle;


import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinglangtech.cardiy.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CityshopActivity extends AppCompatActivity {

    private ImageView mBackImg;
    private TextView mToolbarTitle;
    private TextView mToolbarRightText;
    private ImageView mToolbarRightImg;
    private Toolbar mToolbar;
    private ImageView mIvShop;
    /**
     * 111111
     */
    private TextView mTvTitle;
    /**
     * 222222
     */
    private TextView mShopComments;
    /**
     * 3333
     */
    private TextView mShopAddress;
    /**
     * 444
     */
    private TextView mShopDis;
    private RelativeLayout mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityshop);
        initView();
        initData();
    }

    private void initData() {
        mToolbarTitle.setText("4s店");
    }

    private void initView() {
        mBackImg = (ImageView) findViewById(R.id.back_img);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarRightText = (TextView) findViewById(R.id.toolbar_right_text);
        mToolbarRightImg = (ImageView) findViewById(R.id.toolbar_right_img);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mIvShop = (ImageView) findViewById(R.id.iv_shop);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mShopComments = (TextView) findViewById(R.id.shop_comments);
        mShopAddress = (TextView) findViewById(R.id.shop_address);
        mShopDis = (TextView) findViewById(R.id.shop_dis);
        mAddress = (RelativeLayout) findViewById(R.id.address);
    }
}
