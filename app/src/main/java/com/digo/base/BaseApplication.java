package com.digo.base;

import android.app.Application;

import com.digo.utils.LogUtil;

/**
 * Base Application
 * @author chenh
 */
public class BaseApplication extends Application{

    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        init() ;
    }

    private void init() {

        //开启log日志输出
        LogUtil.setIsOutput( true ) ;

    }

    public static BaseApplication getApplication(){
        return mApplication;
    }


}
