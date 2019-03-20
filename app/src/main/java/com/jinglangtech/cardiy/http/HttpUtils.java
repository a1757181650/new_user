package com.jinglangtech.cardiy.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.jinglangtech.cardiy.App;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018-9-10.
 */

public class HttpUtils {

//    public static <T> void getDataFromServer(Context context, int method, String url, Class<T> clazz, Response.Listener<T> listener,
//                                             Response.ErrorListener errorListener) {
//        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener);
//        Log.i("Http","url:" + url);
//        executeRequest(context,qBaoJsonRequest);
//    }

//    public static <T> void getDataFromServer(Context context, int method, String url, HashMap<String, String> params,
//                                             Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
//        if(params != null && method == Request.Method.GET){
//            boolean isFirst = true;
//            String p = "";
//            for(Map.Entry<String, String> entry:params.entrySet()){
//                if(isFirst){
//                    p += "?" + entry.getKey() + "=" + entry.getValue();
//                }else {
//                    p += "&" + entry.getKey() + "=" + entry.getValue();
//                }
//                isFirst = false;
//            }
//            url += p;
//        }
//
//        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener);
//        if (params != null) {
//            qBaoJsonRequest.addParams(params);
//        }
//
//        Log.i("http",url);
//        executeRequest(context,qBaoJsonRequest);
//    }


    private static void executeRequest(Context context, Request<?> request) {
        request.setTag(context);
        App.getInstance().getRequestQueue().add(request);
    }
}
