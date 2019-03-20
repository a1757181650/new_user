package com.jinglangtech.cardiy.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.jinglangtech.cardiy.App;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.http.AppRetrofit;
import com.jinglangtech.cardiy.revicer.NetworkConnectChangedReceiver;
import com.jinglangtech.cardiy.utils.CommonKeys;
import com.jinglangtech.cardiy.utils.GlobalParmas;
import com.jinglangtech.cardiy.http.ATTJsonRequest;
import com.jinglangtech.cardiy.widget.LoadingProgressDialog;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private NetworkConnectChangedReceiver networkConnectChangedReceiver;

    protected Context mContext;
    protected AppRetrofit appRetrofit;
    private LoadingProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
        mContext = this;
        appRetrofit = AppRetrofit.getInstance();
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        networkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(networkConnectChangedReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkConnectChangedReceiver);
    }

    protected HashMap<String, String> getCommonRequestParmas() {
        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put(CommonKeys.USER_ID, String.valueOf(GlobalParmas.getUserId(this)));
        stringStringMap.put(CommonKeys.USER_GUID, String.valueOf(GlobalParmas.getUserGuid(this)));
        return stringStringMap;
    }


    /**
     * 显示进度条
     *
     * @param msg 进度条文字
     * @return
     */
    protected LoadingProgressDialog showDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingProgressDialog(mContext, R.style.LoadingProgressTheme);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        return mProgressDialog;
    }


    /**
     * 隐藏进度条
     */
    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    public <T> void getDataFromServer(int method, String url, Class<T> clazz, Response.Listener<T> listener,
                                      Response.ErrorListener errorListener) {
        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener, mProgressDialog);
        Log.i("Http","url:" + url);
        executeRequest(qBaoJsonRequest);
    }

    public <T> void getDataFromServer(String mag, int method, String url, HashMap<String, String> params,
                                      Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        showDialog(mag);
        if(params != null && method == Request.Method.GET){
            boolean isFirst = true;
            String p = "";
            for(Map.Entry<String, String> entry:params.entrySet()){
                if(isFirst){
                    p += "?" + entry.getKey() + "=" + entry.getValue();
                }else {
                    p += "&" + entry.getKey() + "=" + entry.getValue();
                }
                isFirst = false;
            }
            url += p;
        }
        Log.i("Http","url:" + url);
        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener, mProgressDialog);
        if (params != null) {
            qBaoJsonRequest.addParams(params);
        }
        executeRequest(qBaoJsonRequest);
    }

    protected void executeRequest(Request<?> request) {
        request.setTag(this);
        App.getInstance().getRequestQueue().add(request);
    }
}
