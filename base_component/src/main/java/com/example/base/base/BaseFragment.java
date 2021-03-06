package com.example.base.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * Author: Funny
 * Time: 2018/9/5
 * Description: This is BaseViewFragment，BaseFragment
 */
public abstract class BaseFragment extends BaseImmersionFragment {
    protected Context mContext;
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(mView == null){
            mView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        }else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if(parent != null){
                parent.removeView(mView);
            }
        }

        initView(mView);
        initData();
        initEvent();
        return mView;
    }

    protected abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initEvent() {
    }

    /**
     * 查找控件
     *
     * @param viewId
     */
    public final <T extends View> T findViewById(int viewId) {
        if (null != mView) {
            return mView.findViewById(viewId);
        }
        return null;
    }
}
