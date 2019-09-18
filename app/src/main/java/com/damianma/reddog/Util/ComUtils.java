package com.damianma.reddog.Util;


import android.content.Context;
import android.widget.Toast;

/*
* 基础通用工具类
* */
public class ComUtils {

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static void showShortToast(String message){
        Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();

    }
}
