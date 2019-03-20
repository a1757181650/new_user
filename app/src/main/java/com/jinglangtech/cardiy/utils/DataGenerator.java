package com.jinglangtech.cardiy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.ui.AttentionFragment;
import com.jinglangtech.cardiy.ui.HomeFragment;
import com.jinglangtech.cardiy.ui.ProfileFragment;
import com.jinglangtech.cardiy.ui.article.DiscoveryFragment;

import androidx.fragment.app.Fragment;

public class DataGenerator {

    public static final int[] mTabRes = new int[]{R.mipmap.home_tab,R.mipmap.head_icon,R.mipmap.service_chat,R.mipmap.mine};
    public static final int[] mTabResPressed = new int[]{R.mipmap.fangzi,R.mipmap.toutiao,R.mipmap.kufu,R.mipmap.geren};
    public static final String []mTabTitle = new String[]{"首页","头条","客服","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DiscoveryFragment.newInstance(from);
        fragments[2] = AttentionFragment.newInstance(from);
        fragments[3] = ProfileFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context,int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
