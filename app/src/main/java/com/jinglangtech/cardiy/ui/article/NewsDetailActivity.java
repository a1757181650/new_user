package com.jinglangtech.cardiy.ui.article;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.news.NewsContentResultModel;
import com.jinglangtech.cardiy.subscribers.ObserverOnNextListener;
import com.jinglangtech.cardiy.subscribers.ProgressObserver;
import com.jinglangtech.cardiy.utils.base.BaseActivity;
import com.jinglangtech.cardiy.utils.CommonKeys;

import java.util.Map;

import androidx.annotation.Nullable;


public class NewsDetailActivity extends BaseActivity {

    private WebView webView;
    private ObserverOnNextListener<NewsContentResultModel> nextSub = o -> {
        if (o != null) {
            String content = o.getContent();
            webView.loadData(content, "text/html", "UTF-8");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        initView();
        initData();
    }

    private void initView() {
        webView = findViewById(R.id.webview);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent !=  null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int newsId = bundle.getInt(CommonKeys.NEWS_ID);
                Map<String, String> commonRequestParmas = getCommonRequestParmas();
                commonRequestParmas.put(CommonKeys.ID, String.valueOf(newsId));
                ProgressObserver<NewsContentResultModel> userUpdateModelProgressObserver = new ProgressObserver<>(nextSub, this, "请求内容中...");
                appRetrofit.showNewsContent(userUpdateModelProgressObserver, commonRequestParmas);
            }

        }
    }
}
