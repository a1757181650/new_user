package com.jinglangtech.cardiy;

import com.jinglangtech.cardiy.entity.CityModel;
import com.jinglangtech.cardiy.entity.HeadNewsModel;
import com.jinglangtech.cardiy.entity.login.LoginModel;
import com.jinglangtech.cardiy.entity.login.SendCodeModel;
import com.jinglangtech.cardiy.entity.news.NewsContentResultModel;
import com.jinglangtech.cardiy.entity.user.UserInfoModel;
import com.jinglangtech.cardiy.entity.user.UserUpdateModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {

    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")

    /**
     * 获取城市列表
     */
    @FormUrlEncoded
    @POST("api2/city/getlist")
    Observable<CityModel> getCities(@Field("levelType") String levelType);

    /**
     * 获取头条列表
     */
    @FormUrlEncoded
    @POST("api2/article/getlist")
    Observable<HeadNewsModel> getHeadNews(@Field("pageNum") String pageNum, @Field("pageSize") String pageSize);


    /**
     * 发送验证码
     */
    @FormUrlEncoded
    @POST("api2/verify/send")
    Observable<SendCodeModel> sendPhoneVerifyCode(@Field("phone") String phone);

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("api2/user/login")
    Observable<LoginModel> login(@Field("phone") String phone, @Field("password") String password);

    /**
     * 个人资料
     */
    @FormUrlEncoded
    @POST("api2/user/getinfo")
    Observable<UserInfoModel> getUserInfo(@Field("userid") String userId, @Field("userguid") String userGuid);



    /**
     * 更新用户资料
     */
    @FormUrlEncoded
    @POST("api2/user/update")
    Observable<UserUpdateModel> updateUserInfo(@FieldMap Map<String, String> params);

    /**
     * 更新用户资料
     */
    @FormUrlEncoded
    @POST("api2/article/getinfo")
    Observable<NewsContentResultModel> showNewsContent(@FieldMap Map<String, String> params);
}
