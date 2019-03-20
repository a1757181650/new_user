package com.jinglangtech.cardiy.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.login.LoginModel;
import com.jinglangtech.cardiy.entity.login.Results;
import com.jinglangtech.cardiy.entity.login.SendCodeModel;
import com.jinglangtech.cardiy.http.AppRetrofit;
import com.jinglangtech.cardiy.subscribers.ObserverOnNextListener;
import com.jinglangtech.cardiy.subscribers.ProgressObserver;
import com.jinglangtech.cardiy.utils.base.BaseActivity;
import com.jinglangtech.cardiy.utils.Utils;
import com.jinglangtech.cardiy.utils.GlobalParmas;
import com.jinglangtech.cardiy.http.ServerUrl;

import java.util.HashMap;

import static com.jinglangtech.cardiy.utils.Logger.LOGD;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private int i = 60;
    private boolean mThreadRun = false;
    private EditText etPhone;
    private Button btnSendVerifyCode;
    private AppRetrofit appRetrofit;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                btnSendVerifyCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                btnSendVerifyCode.setText("获取验证码");
                btnSendVerifyCode.setClickable(true);
                i = 60;
            }
        }
    };
    private ObserverOnNextListener<SendCodeModel> nextSubListener = new ObserverOnNextListener<SendCodeModel>() {
        @Override
        public void onNext(SendCodeModel sendCodeModel) {
            if (sendCodeModel != null) {
                Toast.makeText(mContext, sendCodeModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Button btnLogin;
    private String phoneNum;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initNet();
        initView();
        initData();

    }

    private void initData() {

    }

    private void initNet() {
        appRetrofit = AppRetrofit.getInstance();
    }

    private void initView() {
        etPhone = findViewById(R.id.edt_name);
        btnSendVerifyCode = findViewById(R.id.btn_verificationc_ode);
        btnLogin = findViewById(R.id.btn_login);
        etPwd = findViewById(R.id.edt_password);
        btnSendVerifyCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        phoneNum = etPhone.getText().toString();
        String pwdNum = etPwd.getText().toString();
        switch (v.getId()) {
            case R.id.btn_verificationc_ode:
                if (!Utils.isMobileNO(phoneNum)) {
                    //Toast.makeText(this, "手机号输入有误，请重新输入", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(this, "手机号输入有误，请重新输入", Toast.LENGTH_SHORT);
                    //屏幕居中显示，x轴和y轴偏移量都是0
                    toast.setGravity(Gravity.CENTER, -30, -30);
                    toast.show();
                    return;
                } else {
                    ProgressObserver<SendCodeModel> headNewsModelProgressObserver = new ProgressObserver<>(nextSubListener, mContext, "验证码发送中...");
                    appRetrofit.sendVerifyCode(headNewsModelProgressObserver, phoneNum);
                    break;
            }
            case R.id.btn_login:
                if (phoneNum == null || pwdNum == null || phoneNum.equals("") || pwdNum.equals("")) {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.content_null), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    HashMap<String, String> stringStringMap = new HashMap<>();
                    stringStringMap.put("phone", phoneNum);
                    stringStringMap.put("password", pwdNum);
                    getDataFromServer("登录中...", Request.Method.POST, ServerUrl.GET_LOGIN, stringStringMap, LoginModel.class, response -> {
                        if (response != null && response.getError() == 0) {
                            GlobalParmas.setLogin(mContext, true);
                            Results results = response.getResults();
                            GlobalParmas.setPhoneNum(mContext, results.getPhone());
                            GlobalParmas.setUserId(mContext, results.getUserid());
                            GlobalParmas.setUserGuid(mContext, results.getUserGuid());
                            finish();
                        }
                    }, error -> {
                        LOGD("login response error");
                        hideProgress();
                    });
                    break;
                }

        }
        btnSendVerifyCode.setClickable(false);
        btnSendVerifyCode.setText("重新发送(" + i + ")");
        mThreadRun = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    if (mThreadRun) {
                        handler.sendEmptyMessage(-9);
                        if (i <= 0) {
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (mThreadRun) {
                    handler.sendEmptyMessage(-8);
                }
            }
        }).start();
    }

        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
                if (isShouldHideInput(v, ev)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        //处理Editext的光标隐藏、显示逻辑
                        v.clearFocus();
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
            return onTouchEvent(ev);
        }

        public  boolean isShouldHideInput(View v, MotionEvent event) {
            if (v != null && (v instanceof EditText)) {
                int[] leftTop = { 0, 0 };
                //获取输入框当前的location位置
                v.getLocationInWindow(leftTop);
                int left = leftTop[0];
                int top = leftTop[1];
                int bottom = top + v.getHeight();
                int right = left + v.getWidth();
                if (event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom) {
                    // 点击的是输入框区域，保留点击EditText的事件
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }



}
