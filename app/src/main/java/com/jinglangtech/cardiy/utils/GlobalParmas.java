package com.jinglangtech.cardiy.utils;


import android.content.Context;
import android.content.Intent;
import java.io.Serializable;

public class GlobalParmas {

    /**
     * 登录状态
     * @param context
     * @param isLogin
     */
    public static void setLogin(Context context, boolean isLogin) {
        SPUtil.put(context, Constants.LOGIN, isLogin);
    }

    public static boolean isLogin(Context context) {
        return (boolean) SPUtil.get(context, Constants.LOGIN, false);
    }


    public static void setPhoneNum(Context context, String phoneNum) {
        SPUtil.put(context, Constants.PHONE_NUM, phoneNum);
    }

    public static String getPhoneNum(Context context) {
        return (String) SPUtil.get(context, Constants.PHONE_NUM, "");
    }

    public static void setUserId(Context context, int phoneNum) {
        SPUtil.put(context, Constants.USER_ID, phoneNum);
    }

    public static Integer getUserId(Context context) {
        return (Integer) SPUtil.get(context, Constants.USER_ID, 0);
    }

    public static void setUserGuid(Context context, int phoneNum) {
        SPUtil.put(context, Constants.USER_GUID, phoneNum);
    }

    public static Integer getUserGuid(Context context) {
        return (Integer) SPUtil.get(context, Constants.USER_GUID, 0);
    }
}
