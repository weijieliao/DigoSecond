package com.digo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    private boolean isDisableTopBar = true ;
    //标题栏左边ImageView
    private ImageView mLeftButton;
    //标题栏中间标题
    private TextView mTxtTitle;
    //内容View
    protected View mContentView;
    //presenter
    protected T mPresenter;

    //Android应用启动优化，一种DelayLoad的实现(异步加载)
    private Handler mHandler = new Handler();
    private Runnable mLoadRunnable = new Runnable() {
        @Override
        public void run() {
            //初始化数据
            initData();
            //初始化布局
            initView();
            //初始化事件
            initEvent() ;
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
        dobeforeSetContentView();
        //初始化根布局
        mRoot = new LinearLayout(this);
        mRoot.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRoot.setLayoutParams(lp);
        //如果不需要用到基类中的标题栏，可以另外写
//        if (isUseBaseTopBar()) {
//            //设置标题栏
//            setTopBar();
//        } else {
//            View mOtherTopBar = setOtherTopBar();
//            if (mOtherTopBar != null) {
//                mRoot.addView(mOtherTopBar);
//            }
//        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉系统标题栏
        if( !isDisableTopBar ) {
            //设置顶部标题栏，如果子类重写了getTopBarView()方法，则使用重写的view，否则使用默认的顶部标题栏
            mTopBarView = getTopBarView() ;
            if( mTopBarView != null ){
                //重写
                mRoot.addView( mTopBarView ) ;
            }
            else{
                //使用默认
                setTopBar();
            }
        }

        if (getContentViewResId() != 0) {
            mContentView = View.inflate(this, getContentViewResId(), null);
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
     * 设置是否禁止显示标题栏，在setcontentview之前调用，如果禁止，则自定义的标题栏也不会显示
     * @param bool
     */
    public void setIsDisableTopBar( boolean bool ){
        this.isDisableTopBar = bool ;
    }

    /**
     * 获取顶部标题栏view，如果子类想要使用自己的标题栏，则重写该方法，将自己的view返回
     * @return
     */
    public View getTopBarView(){
        return null ;
    }

    /**
     * 设置默认标题栏
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

//    /**
//     * 设置其他类型的标题栏
//     *
//     * @return
//     */
//    public View setOtherTopBar() {
//        return null;
//    }

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

//    /**
//     * 通过resid获取颜色值，api23及之后获取的方式不同
//     * @param resId
//     * @return
//     */
//    public int getResourceColor( int resId ){
//        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.M ){
//            return getResources().getColor( resId ) ;
//        }
//        else{
//            return getResources().getColor( resId , null ) ;
////            return ContextCompat.getColor( this , resId ) ;
//
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    /**
     * setContentView()之前执行
     */
    public void dobeforeSetContentView(){

    }

    /**
     * setContentView()之后执行
     */
    public void doAfterSetContentView(){

    }

//    public abstract boolean isUseBaseTopBar();

    public abstract int getContentViewResId();

    public abstract BasePresenterInfc getPresenter();

    /**
     * 初始化数据，执行在initView()之前
     */
    public abstract void initData();

    /**
     * 初始化视图，执行在initData()之后
     */
    public abstract void initView();

    /**
     * 初始化事件，执行在initView()之后
     */
    public abstract void initEvent() ;

}
