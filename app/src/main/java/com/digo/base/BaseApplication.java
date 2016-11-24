package com.digo.base;

import android.app.Application;
import android.graphics.Color;

import com.digo.httpmanager.HttpManager;
import com.digo.utils.FrescoImageLoader;
import com.digo.utils.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import okhttp3.OkHttpClient;

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
<<<<<<< HEAD
        HttpManager.initOkHttpClient(new OkHttpClient.Builder());
        //初始化相册
        initGalleryFinal();
    }

    /**
     * 初始化
     */
    private void initGalleryFinal() {
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0x21, 0xbc, 0xa7))
                .setFabNornalColor(Color.rgb(0x21, 0xbc, 0xa7))
                .setFabPressedColor(Color.rgb(0x00, 0xcc, 0x99))
                .setCheckSelectedColor(Color.rgb(0x21, 0xbc, 0xa7))
                .setCropControlColor(Color.rgb(0x21, 0xbc, 0xa7))
                .build();

        //配置功能
        FunctionConfig function = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(false)
                .setEnableCrop(true)
                .setEnableRotate(false)
                .setCropSquare(true)
                .setEnablePreview(false)
                .build();

        //配置ImageLoader
        ImageLoader imageLoader = new FrescoImageLoader(this);

        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, theme)
                .setFunctionConfig(function).build();

        GalleryFinal.init(coreConfig);
=======
        //初始化fresco
        Fresco.initialize(this);
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535

    }

    public static BaseApplication getApplication(){
        return mApplication;
    }


}
