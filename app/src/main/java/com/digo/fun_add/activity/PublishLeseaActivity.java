package com.digo.fun_add.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.digo.base.BaseActivity;
import com.digo.base.BasePresenterInfc;
import com.example.lwj.digosecond.R;

public class PublishLeseaActivity extends BaseActivity {

    @Override
    public void dobeforeSetContentView() {
        super.dobeforeSetContentView();
        setIsDisableTopBar(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_publish_lesea;
    }

    @Override
    public BasePresenterInfc getPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        super.setTitle("发布租借");
    }

    @Override
    public void initEvent() {

    }
}
