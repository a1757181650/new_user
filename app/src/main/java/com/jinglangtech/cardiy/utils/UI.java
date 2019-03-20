package com.jinglangtech.cardiy.utils;


import android.content.Context;
import android.widget.Toast;

public class UI {

    public static void showMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
