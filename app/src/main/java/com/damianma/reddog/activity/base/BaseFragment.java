package com.damianma.reddog.activity.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;


/**
 * Created by ZT on 2018/1/9.
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(setContentViewLayoutId(), container, false);
        ButterKnife.bind(this,mView);
        initView();
        initData();
        return mView;
    }

    protected abstract int setContentViewLayoutId();

    protected void initView() {}

    protected void initData(){}
    /**
     * 关闭软键盘
     */
    protected void closeSoftware() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getActivity().getCurrentFocus() != null) {
            if (getActivity().getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
