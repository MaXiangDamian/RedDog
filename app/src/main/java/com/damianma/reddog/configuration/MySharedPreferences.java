package com.damianma.reddog.configuration;

import android.content.Context;
import android.content.SharedPreferences;



public class MySharedPreferences {
    public static SharedPreferences.Editor edit;
    private static SharedPreferences sp;
    public static void init(Context context){
        sp = context.getSharedPreferences("reddog", Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    /*
    * 获取token
    * */
    public static void setToken(String token){
        edit.putString("token", token).commit();
    }
    public static String getToken(){
        return sp.getString("token",null);
    }

    /**
     * 登陆状态
     */
    public static void setLogin(boolean isLogin){
        edit.putBoolean("islogin", isLogin).commit();
    }
    public static boolean isLogin(){
        return sp.getBoolean("islogin", false);
    }


}
