package com.damianma.reddog.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.damianma.reddog.R;
import com.damianma.reddog.Util.ScreenUtil;
import com.jaeger.library.StatusBarUtil;


import butterknife.ButterKnife;

/**
 * activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.resetDensity(this);
        setContentView(setContentViewId());
        bind();
        setTopBarFontColor();
        statueBar();
        initView();
        initListener();
        initData();
    }

    protected void statueBar() {
        StatusBarUtil.setColor(this,getResources().getColor(setStatueBarColor()),0);
    }

    /**
     * 子类可重写此方法实现状态栏自定义
     */
    protected int setStatueBarColor(){
        return R.color.color_white;
    }
    /**
     * 子类实现此方法
     */
    protected abstract int setContentViewId();
    /**
     * 数据加载
     */
    protected void initData(){}
    /**
     * 子类可重写此方法初始化控件
     */
    protected void initView(){}
    /**
     * 注解库绑定控件和事件
     */
    private void bind(){
        ButterKnife.bind(this);
    }
    private  void initListener(){
        View backIv = findViewById(R.id.back_colse_view);
        if(backIv!=null)backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBackClick(v);
            }
        });
    }
    /**
     * 子类可重写此方法实现back返回事件的自定义
     * @param view
     */
    protected void doBackClick(View view){
        if(view.getId() == R.id.back_colse_view){
            finish();
            closeSoftware();
        }
    }
    /**
     * 打开activity的切换动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        overridePendingTransition(R.anim.translate_right_to_between,R.anim.translate_between_to_left);
    }

    /**
     * activity销毁时的切换动画
     */
    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.translate_left_to_between,R.anim.translate_between_to_right);
    }

    /**
     * 关闭软键盘
     */
    public void closeSoftware() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 设置android 6.x以上系统 系统状态栏字体颜色
     */
    protected void setTopBarFontColor(){
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(setStatusBarFontColorIsBlack()){
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }
    protected boolean setStatusBarFontColorIsBlack(){
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: 2018/9/15 是否沿用这个自动升级？
//        if(MySharedPreferences.isUpdateApp()){
//            return;
//        }
        //取消请求
//        OkGo.getInstance().cancelTag(this);
    }

    /**
     * 在某种情况下需要Activity的视图初始完毕Application中DisplayMetrics相关参数才能起效果，例如toast.
     * @param
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScreenUtil.resetDensity(this.getApplicationContext());
    }
}
