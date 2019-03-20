package com.jinglangtech.cardiy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.user.UserInfoResults;
import com.jinglangtech.cardiy.entity.user.UserInfoModel;
import com.jinglangtech.cardiy.http.AppRetrofit;
import com.jinglangtech.cardiy.subscribers.ObserverOnNextListener;
import com.jinglangtech.cardiy.subscribers.ProgressObserver;
import com.jinglangtech.cardiy.utils.CommonKeys;
import com.jinglangtech.cardiy.utils.GlobalParmas;
import com.jinglangtech.cardiy.utils.UI;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private String mFrom;
    private TextView tvUserName;
    private CircleImageView ivUserAtavar;
    private UserInfoResults mUserInfo;

    private ObserverOnNextListener<UserInfoModel> nextSubListener = userInfoModel -> {
        if (userInfoModel != null) {
            if (userInfoModel.getError() == 0) {
                UserInfoResults results = userInfoModel.getResults();
                mUserInfo = results;
                Picasso.get().load(results.getAvatar()).placeholder(R.mipmap.default_avatar).into(ivUserAtavar);
                tvUserName.setText(results.getName());
            } else {
                UI.showMsg(getActivity(), userInfoModel.getMessage());
            }
        }
    };

    public static ProfileFragment newInstance(String from){
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from",from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mFrom = getArguments().getString("from");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout rlUserProfile = view.findViewById(R.id.rl_user_profile);
        rlUserProfile.setOnClickListener(this);
        ivUserAtavar = view.findViewById(R.id.iv_user_info);
        tvUserName = view.findViewById(R.id.tv_user_name);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean login = GlobalParmas.isLogin(getActivity());
        if (login) {
            int userGuid = GlobalParmas.getUserGuid(getActivity());
            int userId = GlobalParmas.getUserId(getActivity());
            ProgressObserver<UserInfoModel> headNewsModelProgressObserver = new ProgressObserver<>(nextSubListener, getActivity(), "获取信息中...");
            AppRetrofit.getInstance().getUserInfo(headNewsModelProgressObserver, "" + userId, "" + userGuid);
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_profile:
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(CommonKeys.USER_OBJ, mUserInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
