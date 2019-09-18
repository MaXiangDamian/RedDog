package com.damianma.reddog;


import android.app.Application;

import com.damianma.reddog.Util.ServerUtil;
import com.damianma.reddog.configuration.MySharedPreferences;

/*
* @author DamiannMa
* 基础Application类，可以再次继承
* */
public class RedDogApplication extends Application {

    private static final String TAG = "RedDogApplication";

    private static RedDogApplication instance;
    public static RedDogApplication getInstance() {
        return instance;
    }
    public RedDogApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MySharedPreferences.init(instance);
        ServerUtil.init(instance);
    }
}
