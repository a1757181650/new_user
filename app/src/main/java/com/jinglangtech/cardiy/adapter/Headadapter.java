package com.jinglangtech.cardiy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class Headadapter extends PagerAdapter{

    private List<Integer> list;
    private Context context;

    public Headadapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(list.get(position));
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



    }

