package com.jinglangtech.cardiy.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.user.UserInfoResults;
import com.jinglangtech.cardiy.entity.user.UserInfoModel;
import com.jinglangtech.cardiy.utils.base.BaseActivity;
import com.jinglangtech.cardiy.utils.CommonKeys;
import com.jinglangtech.cardiy.utils.UI;
import com.jinglangtech.cardiy.http.ServerUrl;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.jinglangtech.cardiy.utils.Logger.LOGD;
import static com.jinglangtech.cardiy.utils.Logger.LOGE;

public class UserProfileActivity extends BaseActivity {

    private EditText etNickName;
    private EditText etAddress;
    private EditText etName;
    private UserInfoResults userInfo;
    private CircleImageView ivUserAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                userInfo = bundle.getParcelable(CommonKeys.USER_OBJ);
            }
        }
    }

    private void initView() {
        ivUserAvatar = findViewById(R.id.user_info_userface);
        etNickName = findViewById(R.id.user_info_nick_text);
        etName = findViewById(R.id.user_info_name_text);
        etAddress = findViewById(R.id.user_info_address_text);
        if (userInfo != null) {
            Picasso.get().load(userInfo.getAvatar()).placeholder(R.mipmap.default_avatar).into(ivUserAvatar);
            etNickName.setText(userInfo.getNickname());
            etName.setText(userInfo.getName());
//            etAddress.setText(userInfo.getSex());
        }
    }

    public void saveUserInfo(View view) {
        String nickName = etNickName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)  || TextUtils.isEmpty(nickName)) {
            UI.showMsg(this, "资料不能为空");
            return;
        }
        HashMap<String, String> commonRequestParmas = getCommonRequestParmas();
        commonRequestParmas.put(CommonKeys.ADDRESS, address);
        commonRequestParmas.put(CommonKeys.NICK_NAME, nickName);
        commonRequestParmas.put(CommonKeys.NAME, name);
        commonRequestParmas.put(CommonKeys.SEX, "0");
        commonRequestParmas.put(CommonKeys.AVATAR, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3110135022,2684976613&fm=27&gp=0.jpg");
        getDataFromServer("保存资料中...", Request.Method.POST, ServerUrl.GET_SAVE_USER_INFO, commonRequestParmas, UserInfoModel.class, userInfoModel -> {
            if (userInfoModel != null && userInfoModel.getError() == 0) {
                UI.showMsg(this, userInfoModel.getMessage());
                finish();
            }
        }, error -> {
            LOGE("save user info error");
            hideProgress();
        });

    }
}
