package com.jinglangtech.cardiy.ui.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.adapter.Headadapter;
import com.jinglangtech.cardiy.adapter.ItemNewsAdapter;
import com.jinglangtech.cardiy.entity.news.NewsList;
import com.jinglangtech.cardiy.entity.news.NewsResponseModel;
import com.jinglangtech.cardiy.entity.news.Results;
import com.jinglangtech.cardiy.utils.base.BaseFragment;
import com.jinglangtech.cardiy.utils.CommonKeys;
import com.jinglangtech.cardiy.http.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static com.jinglangtech.cardiy.utils.Logger.LOGD;

public class DiscoveryFragment extends BaseFragment {

    private String mFrom;
    private RecyclerView recyclerView;

    public static DiscoveryFragment newInstance(String from){
        DiscoveryFragment fragment = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from",from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mFrom = getArguments().getString("from");
        }
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new ItemNewsAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ViewPager viewpager = view.findViewById(R.id.viewpager);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.xw);
        list.add(R.mipmap.tu14);
        list.add(R.mipmap.tu12);
        list.add(R.mipmap.tu14);
        list.add(R.mipmap.tu14);
        Headadapter adapter = new Headadapter(getContext(), list);
        viewpager.setAdapter(adapter);
        viewpager.setPageMargin(20);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("pageSize", "1");
        stringStringMap.put("pageNum", "1");
        getDataFromServer("获取数据中...",Request.Method.POST, ServerUrl.GET_ARTICLE_LIST, stringStringMap, NewsResponseModel.class, response -> {
            LOGD("city news list :" + new Gson().toJson(response));
            hideProgress();
            if (response != null && response.getError() == 0) {
                Results results = response.getResults();
                List<NewsList> list = results.getList();
                ItemNewsAdapter itemNewsAdapter = new ItemNewsAdapter(getActivity(), list);
                itemNewsAdapter.setOnItemClickListener(position -> {
                    NewsList headNewsResult = list.get(position);
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    int id = headNewsResult.getId();
                    bundle.putInt(CommonKeys.NEWS_ID, id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                });
                recyclerView.setAdapter(itemNewsAdapter);
            }
        }, error -> {
            hideProgress();
        });
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discovery;
    }
}
