package com.digo.base;

import android.app.Application;

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
    }

    public static BaseApplication getmApplication(){
        return mApplication;
    }


}
