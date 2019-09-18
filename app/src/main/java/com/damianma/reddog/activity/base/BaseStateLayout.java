package com.damianma.reddog.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.damianma.reddog.R;

import butterknife.ButterKnife;

/**
 *
 * 基础多状态布局,管理布局加载状态
 */

public abstract class BaseStateLayout extends FrameLayout {
    protected Context mContext;
    protected View successView,loadingView,errorView,emptyView;
    private TextView errorTv;
    public BaseStateLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        init();
    }
    private void init(){
        successView = LayoutInflater.from(mContext).inflate(layoutId(), this, false);
        loadingView = LayoutInflater.from(mContext).inflate(R.layout.common_loading,this,false);
        errorView = LayoutInflater.from(mContext).inflate(R.layout.common_error,this,false);
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.common_empty,this,false);

        this.addView(successView);
        this.addView(loadingView);
        this.addView(errorView);
        this.addView(emptyView);

        //reload click listener
        errorTv = errorView.findViewById(R.id.common_center_iv);
        errorView.findViewById(R.id.id_common_loading_tv)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showLoading();
                        reload();
                    }
                });
        addRefreshLoadContenLayout(successView);
        //绑定当前布局
        ButterKnife.bind(this);
        //init view
        initSuccessView();
        //show loading
        showLoading();
        //load data
        startLoadData();
    }
    protected void showLoading(){
        successView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
    }
    protected void showError(String msg){
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        errorView.setVisibility(VISIBLE);
        errorTv.setText(msg);
    }
    protected void showError(int responseCode){
        if(responseCode==404){
            showError("未找到服务器(404)");
        }else if(responseCode==500){
            showError("服务器内部错误(500)");
        }else {
            showError("网络连接异常 请连接成功后重试");
        }
    }
    protected void showEmpty(){
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
    }
    protected void showSuccessView(){
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
    }
    /**
     *返回数据加载成功布局id
     */
    protected abstract int layoutId();
    /**
     * 子类可重写此方法实现一些ui初始化工作
     */
    protected void initSuccessView(){}

    /**
     * 上下拉刷新布局 添加其包裹的刷新体布局
     */
    protected void addRefreshLoadContenLayout(View successView){}
    /**
     * 加载数据
     */
    protected abstract void startLoadData();

    /**
     * 点击重试按钮重新加载数据
     */
    protected abstract void reload();

    /**
     * 设置load error 显示文本内容
     */
    protected void setErrorTextContent(String msg){
        showError(msg);
    }

    protected void startActivity(Class atyClass){
        Intent intent = new Intent(mContext,atyClass);
        mContext.startActivity(intent);
    }

}
