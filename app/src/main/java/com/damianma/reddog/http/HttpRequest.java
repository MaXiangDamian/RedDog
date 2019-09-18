package com.damianma.reddog.http;


import com.damianma.reddog.RedDogApplication;
import com.damianma.reddog.Util.ServerUtil;
import com.damianma.reddog.Util.SettingUtil;
import com.damianma.reddog.configuration.MySharedPreferences;
import com.damianma.reddog.model.Parameter;

import java.util.ArrayList;
import java.util.List;


/**
 * @param
 * @author DamianMa
 * @desc 处理http请求结果
 * @creat 2018/9/12 10:00
 **/
public class HttpRequest {

    public static final int HTTP_GET_CATE = 10;



    private static final String TAG = "HttpRequest";
    /**
     * 基础URL，这里服务器设置可切换
     */
    public static String URL_BASE;
    private static RedDogApplication application;

    static {
        application = RedDogApplication.getInstance();
        URL_BASE = ServerUtil.getCurrentServerAddress();
    }

    public static void post(List<Parameter> list, String url, int requestCode, OnHttpResponseListener listener) {
        HttpManager.tokenValue = MySharedPreferences.getToken();
        HttpManager.getInstance().post(list, url, requestCode, listener);
    }

    public static void requestCate(OnHttpResponseListener listener) {
        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("temp", ""));
        post(list, URL_BASE + "catering/index", HTTP_GET_CATE, listener);
    }

}
