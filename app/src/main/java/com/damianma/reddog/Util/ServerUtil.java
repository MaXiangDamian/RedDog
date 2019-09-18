package com.damianma.reddog.Util;

import android.content.Context;
import android.content.SharedPreferences;


/*
* 服务器信息配置
* */
public class ServerUtil {

    /**
     * 建议改成你自己项目的路径
     */
    public static final String APP_SETTING = "SHARE_PREFS_" + "APP_SETTING";
    /**
     * TODO 改为你的存图片的服务器地址
     */
    public static final String IMAGE_BASE_URL = "http://demo.upaiyun.com";
    public static final String KEY_SERVER_ADDRESS_NORMAL = "KEY_SERVER_ADDRESS_NORMAL";
    public static final String KEY_SERVER_ADDRESS_TEST = "KEY_SERVER_ADDRESS_TEST";
    /**
     * TODO 改为你的正式服务器地址
     */
//	public static final String URL_SERVER_ADDRESS_NORMAL_HTTP = "http://iwe.doncheng.cn/apiv1/";//正式服务器
    public static final String URL_SERVER_ADDRESS_NORMAL_HTTP = "http://atmall.youpinlai.net/apiv1/";//正式服务器
    /**
     * TODO 改为你的正式服务器地址
     */
//	public static final String URL_SERVER_ADDRESS_NORMAL_HTTPS = "http://iwe.doncheng.cn/apiv1/";//正式服务器
    public static final String URL_SERVER_ADDRESS_NORMAL_HTTPS = "http://atmall.youpinlai.net/apiv1/";//正式服务器
    /**
     * TODO 改为你的测试服务器地址,如果有的话
     */
//	public static final String URL_SERVER_ADDRESS_TEST = "http://iwe.doncheng.cn/apiv1/";//测试服务器
    public static final String URL_SERVER_ADDRESS_TEST = "http://atmall.youpinlai.net/apiv1/";//正式服务器
    public static boolean isOnTestMode = false;//测试模式
    private static Context context;

    public static void init(Context context_) {
        context = context_;
    }

    /**
     * 获取当前服务器地址
     * isHttps = false
     *
     * @return
     */
    public static String getCurrentServerAddress() {
        return getCurrentServerAddress(false);
    }

    /**
     * 获取当前服务器地址
     *
     * @param isHttps
     * @return
     */
    public static String getCurrentServerAddress(boolean isHttps) {
        return isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : getServerAddress(isOnTestMode);
    }

    /**
     * 获取服务器地址
     * isHttps = false
     *
     * @param isTest
     * @return
     */
    public static String getServerAddress(boolean isTest) {
        return getServerAddress(isTest, false);
    }

    /**
     * 获取服务器地址
     *
     * @param isTest
     * @return
     */
    public static String getServerAddress(boolean isTest, boolean isHttps) {
        SharedPreferences sdf = context.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE);
        if (sdf == null) {
            return null;
        }
        if (isTest) {
            return sdf.getString(KEY_SERVER_ADDRESS_TEST, URL_SERVER_ADDRESS_TEST);
        }
        return sdf.getString(KEY_SERVER_ADDRESS_NORMAL
                , isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : URL_SERVER_ADDRESS_NORMAL_HTTP);
    }
}
