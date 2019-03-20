package com.jinglangtech.cardiy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.adapter.CityListAdapter;
import com.jinglangtech.cardiy.entity.CityModel;
import com.jinglangtech.cardiy.entity.CityResult;
import com.jinglangtech.cardiy.subscribers.ObserverOnNextListener;

import java.util.HashMap;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.jinglangtech.cardiy.utils.Logger.LOGD;

public class CityListActivity extends BaseActivity {

    private CityListAdapter cityListAdapter;
    private RecyclerView recyclerView;

    private ObserverOnNextListener<CityModel> nextSubscriber = new ObserverOnNextListener<CityModel>() {
        @Override
        public void onNext(CityModel cityModel) {
            List<CityResult> results = cityModel.getResults();
            cityListAdapter = new CityListAdapter(CityListActivity.this, results);
            recyclerView.setAdapter(cityListAdapter);
            cityListAdapter.setOnItemClickListener(position -> startActivity(new Intent(CityListActivity.this, CityshopActivity.class)));
        }
    };

    private ImageView mBackImg;
    private TextView mToolbarTitle;
    private TextView mToolbarRightText;
    private ImageView mToolbarRightImg;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initView();
        initData();

    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cityListAdapter = new CityListAdapter(this, null);
        recyclerView.setAdapter(cityListAdapter);
//        ProgressObserver<CityModel> transObser = new ProgressObserver<>(nextSubscriber, this, "查询中...");
//        AppRetrofit.getInstance().getCities(transObser, "2");
        mToolbarTitle.setText("选择城市");
        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("levelType", "2");
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        mBackImg = (ImageView) findViewById(R.id.back_img);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarRightText = (TextView) findViewById(R.id.toolbar_right_text);
        mToolbarRightImg = (ImageView) findViewById(R.id.toolbar_right_img);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);

    }
}
