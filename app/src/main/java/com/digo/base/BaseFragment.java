package com.digo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 基础Fragment
 *
 * @author chenh
 */
public abstract class BaseFragment<T extends BasePresenterInfc> extends Fragment
        implements BaseView {

    //presenter
    protected T mPresenter;
    protected Context mContext;
    protected View mContentView = null;
    private boolean isFirstLoad;
    private Runnable mLoadRunnable;
    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        mHandler = new Handler();
        mContentView = View.inflate(getContext(),getContentViewResId(),null);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this,mContentView);
        //获取Presenter
        mPresenter = (T) getPresenter();
        mLoadRunnable = new Runnable() {
            @Override
            public void run() {
                initView();
                initData();
                initEvent() ;
                if (mPresenter!=null){
                    //将Fragment的生命周期与Presenter同步
                    mPresenter.onCreate();
                }
            }
        };

        if (!isLazyLoad()){
            mHandler.post(mLoadRunnable);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isLazyLoad()&&isFirstLoad){
            isFirstLoad = false;
            mHandler.post(mLoadRunnable);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void nextActivity(Class<?> targetClass) {
        //默认直接跳转
        startActivity(new Intent(mContext,targetClass));
    }

    @Override
    public void nextActivity(Intent intent) {

        startActivity(intent);
    }

    @Override
    public void finishActivity() {

        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    public abstract int getContentViewResId();

    protected abstract BasePresenterInfc getPresenter();

    public abstract void initData();

    public abstract void initView();

    public abstract void initEvent();

    public abstract boolean isLazyLoad();


}
