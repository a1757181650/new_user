package com.jinglangtech.cardiy.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jinglangtech.cardiy.Api;
import com.jinglangtech.cardiy.ApiKey;
import com.jinglangtech.cardiy.entity.CityModel;
import com.jinglangtech.cardiy.entity.HeadNewsModel;
import com.jinglangtech.cardiy.entity.login.LoginModel;
import com.jinglangtech.cardiy.entity.login.SendCodeModel;
import com.jinglangtech.cardiy.entity.news.NewsContentResultModel;
import com.jinglangtech.cardiy.entity.user.UserInfoModel;
import com.jinglangtech.cardiy.entity.user.UserUpdateModel;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {

    final Api apiService;

    // @formatter:off
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();
    // @formatter:on

    private AppRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (ApiKey.IS_DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.connectTimeout(12, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ApiKey.url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit HLHttp = builder.build();
        apiService = HLHttp.create(Api.class);
    }

    private static class SingletonHolder{
        private static final AppRetrofit INSTANCE = new AppRetrofit();
    }

    public static AppRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param subscriber 观察者
     * @param levelType 请求数据
     */
    public void getCities(Observer<CityModel> subscriber, String levelType) {
        Observable<CityModel> loginObservable = apiService.getCities(levelType);
        toSubscribe(loginObservable, subscriber);
    }

    /**
     * @param subscriber 观察者
     */
    public void getHeadNews(Observer<HeadNewsModel> subscriber, String pageNum, String pageSize) {
        Observable<HeadNewsModel> loginObservable = apiService.getHeadNews(pageNum, pageSize);
        toSubscribe(loginObservable, subscriber);
    }

    /**
     * @param subscriber 发送验证码
     */
    public void sendVerifyCode(Observer<SendCodeModel> subscriber, String phone) {
        Observable<SendCodeModel> loginObservable = apiService.sendPhoneVerifyCode(phone);
        toSubscribe(loginObservable, subscriber);
    }

    /**
     * @param subscriber 登陆
     */
    public void login(Observer<LoginModel> subscriber, String phone, String pwd) {
        Observable<LoginModel> loginObservable = apiService.login(phone, pwd);
        toSubscribe(loginObservable, subscriber);
    }

    /**
     * @param subscriber 获取用户信息
     */
    public void getUserInfo(Observer<UserInfoModel> subscriber, String userid, String userguid) {
        Observable<UserInfoModel> loginObservable = apiService.getUserInfo(userid, userguid);
        toSubscribe(loginObservable, subscriber);
    }

    /**
     * @param subscriber 获取用户信息
     */
    public void updateUserInfo(Observer<UserUpdateModel> subscriber, Map<String, String> stringSingleMap) {
        Observable<UserUpdateModel> loginObservable = apiService.updateUserInfo(stringSingleMap);
        toSubscribe(loginObservable, subscriber);
    }


    /**
     * @param subscriber 获取用户信息
     */
    public void showNewsContent(Observer<NewsContentResultModel> subscriber, Map<String, String> stringSingleMap) {
        Observable<NewsContentResultModel> loginObservable = apiService.showNewsContent(stringSingleMap);
        toSubscribe(loginObservable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> oe, Observer<T> or){
        oe.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(or);
    }
}
