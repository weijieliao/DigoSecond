package com.digo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digo.utils.ViewUtils;
import com.example.lwj.digosecond.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenterInfc> extends
        AppCompatActivity implements BaseView {

    protected Bundle mSaveInstanceState;
    protected Context mContext;
    //根布局
    private LinearLayout mRoot;
    private LinearLayout.LayoutParams lp;
    //标题栏View
    private View mTopBarView;
    //标题栏左边ImageView
    private ImageView mLeftButton;
    //标题栏中间标题
    private TextView mTxtTitle;
    //内容View
    protected View mContentView;
    //presenter
    protected T mPresenter;

    //Android应用启动优化，一种DelayLoadd的实现(异步加载)
    private Handler mHandler = new Handler();
    private Runnable mLoadRunnable = new Runnable() {
        @Override
        public void run() {
            //初始化布局
            initView();
            //初始化数据
            initData();
            if (mPresenter != null) {
                //将Activity的生命周期与Presenter的进行同步
                mPresenter.onCreate();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSaveInstanceState = savedInstanceState;
        mContext = this;
        deforeSetContentView();
        //初始化根布局
        mRoot = new LinearLayout(this);
        mRoot.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRoot.setLayoutParams(lp);
        //如果不需要用到基类中的标题栏，可以另外写
        if (isUseBaseTopBar()) {
            //设置标题栏
            setTopBar();
        } else {
            View mOtherTopBar = setOtherTopBar();
            if (mOtherTopBar != null) {
                mRoot.addView(mOtherTopBar);
            }
        }
        if (setMyContentView() != 0) {
            mContentView = View.inflate(this, setMyContentView(), null);
            mRoot.addView(mContentView, lp);
        }
        //设置布局
        this.setContentView(mRoot);
        //设置布局后想做的操作
        doAfterSetContentView();
        //初始化ButterKnife
        ButterKnife.bind(this);
        //获取presenter实例
        mPresenter = (T) getPresenter();
        //加载数据
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mHandler.post(mLoadRunnable);
            }
        });


    }

    /**
     * 设置标题栏
     */
    private void setTopBar() {
        //将布局转换成View
        mTopBarView = ViewUtils.inflateWithContext(this, R.layout.top_bar);
        //获取左边ImageView
        mLeftButton = ViewUtils.findViewById(mTopBarView, R.id.top_leftbutton);
        //获取中间标题
        mTxtTitle = ViewUtils.findViewById(mTopBarView, R.id.top_title);
        //添加左边ImageView的监听
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLeftButtonListener(mLeftButton);
            }
        });
        mRoot.addView(mTopBarView);

    }

    /**
     * 设置其他类型的标题栏
     *
     * @return
     */
    public View setOtherTopBar() {
        return null;
    }

    /**
     * 左边ImageView的监听，默认状态是返回上一次Activity，可以重写做其他操作
     *
     * @param mLeftButton
     */
    public void onClickLeftButtonListener(ImageView mLeftButton) {
        finish();
    }

    /**
     * 设置中间标题
     *
     * @param text
     */
    public void setTitle(String text) {
        if (text != null) {
            mTxtTitle.setText(text);
        }
    }

    @Override
    public void nextActivity(Class<?> targetClass) {
        //默认直接跳转
        startActivity(new Intent(mContext, targetClass));
    }

    @Override
    public void nextActivity(Intent intent) {

        startActivity(intent);
    }

    @Override
    public void finishActivity() {

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    public abstract void deforeSetContentView();

    public abstract boolean isUseBaseTopBar();

    public abstract int setMyContentView();

    public abstract void doAfterSetContentView();

    public abstract BasePresenterInfc getPresenter();

    public abstract void initView();

    public abstract void initData();

}
