package com.jinglangtech.cardiy.http;


public interface ServerUrl {
    public static final boolean IS_DEBUG = true;
    public static final String USER = "cheheli";

    public static final String QFB_MOBILE = "phone";
    public static final String QFB_WIFI = "wifi";
//    public static final String SERVER = "http://42.51.40.208:8280";
    public static final String SERVER = "http://47.101.37.210";
    public static final String APP = "/api2";
    public static final String V1_0 = "";
    public static final String V2_0 = "/v2.0";
    public static final String GET_BANNER_LIST = SERVER + APP + V1_0 + "/banner/getlist";

    /**
     *  获取头条列表
     */
    public static final String GET_ARTICLE_LIST = SERVER + APP + V1_0 + "/article/getlist";
    /**
     *  城市列表
     */
    public static final String GET_CITY_LIST = SERVER+ APP + V1_0 + "/city/getlist";

    public static final String GET_LOGIN = SERVER+ APP + V1_0 + "/user/login";
    public static final String GET_SAVE_USER_INFO = SERVER+ APP + V1_0 + "/user/update";
}


