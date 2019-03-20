package com.jinglangtech.cardiy;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jinglangtech.cardiy.location.LocationService;

import java.util.LinkedList;
import java.util.List;


public class App extends Application {

    private LocationService mLocationService;
    /**
     * Application实例
     */
    private static App instance = null;
    private List<Activity> mActivityStack = new LinkedList<>();
    private RequestQueue mRequestQueue = null;

    private static int mMainThreadId = -1;
    private static Thread mMainThread;
    private static Handler mMainThreadHandler;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mRequestQueue = Volley.newRequestQueue(this, null);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        mLocationService = new LocationService(getApplicationContext());
    }

    /**
     * 获取内存中保存的路径
     *
     * @return
     */
    public LocationService getLocationService() {
        return mLocationService;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, null);
        }
        return mRequestQueue;
    }

    public void addActivity(Activity activity) {
        mActivityStack.add(activity);
        //Logger.d("在activity栈中添加(" + activity.getClass().getSimpleName() + ")");
    }

    public void finishActivity(Activity activity) {
        if (mActivityStack.contains(activity)) {
            mActivityStack.remove(activity);
            //Logger.d("从activity栈中移除(" + activity.getClass().getSimpleName() + ")");
        }
    }

    public void finishAllActivity() {
        StringBuilder stringBuilder = new StringBuilder("移除所有的activity:");
        for (Activity activity : mActivityStack) {
            if (activity != null) {
                stringBuilder.append("==").append(activity.getClass().getSimpleName()).append("==");
                activity.finish();
            }
        }
        mActivityStack.clear();
    }

    public void toTopActivity(){
        for (int i = 2; i <= mActivityStack.size(); i++) {
            if (mActivityStack.get(i - 1) != null) {
                mActivityStack.get(i - 1).finish();
            }
        }
        mActivityStack.clear();
    }

    public void finishOtherActivity() {
        for (int i = 1; i < mActivityStack.size(); i++) {
            if (mActivityStack.get(i - 1) != null) {
                mActivityStack.get(i - 1).finish();
            }
        }
        mActivityStack.clear();
    }

    public void finishLastActivity() {
        if (mActivityStack.get(mActivityStack.size() - 2) != null) {
            mActivityStack.get(mActivityStack.size() - 2).finish();
        }
    }

    public Activity getCurretActivity() {
        return mActivityStack.get(mActivityStack.size() - 1);
    }


    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

}
