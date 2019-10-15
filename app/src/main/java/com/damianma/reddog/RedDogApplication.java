package com.damianma.reddog;


import android.app.Application;
import android.os.Handler;

import com.damianma.reddog.Util.ComUtils;
import com.damianma.reddog.Util.ServerUtil;
import com.damianma.reddog.configuration.MySharedPreferences;

/*
* @author DamiannMa
* 基础Application类，可以再次继承
* */
public class RedDogApplication extends Application {

    private static final String TAG = "RedDogApplication";
    private static Handler handler;
    private static int mainThreadId;


    private static RedDogApplication instance;
    public static RedDogApplication getInstance() {
        return instance;
    }
    public RedDogApplication() {
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();//获取当前线程id，主线程
        MySharedPreferences.init(instance);
        ServerUtil.init(instance);
        ComUtils.init(instance);
    }
}
