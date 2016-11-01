package com.digo.utils;

import android.util.Log;

/**
 *
 * 日志工具类
 *
 * Created by weijieliao on 2016/10/31.
 */
public class LogUtil {

    private static final String TAG = "mylog" ;
    private static boolean isOutputLog = false ;

    /**
     * 日志开关
     * @param bool
     */
    public static void setIsOutput( boolean bool ){
        isOutputLog = bool ;
    }

    /**
     *  普通日志
     * @param obj
     * @param log
     */
    public static void logI( Object obj , String log ){
        if( isOutputLog ){
            Log.i( TAG , obj.getClass().toString()+"-->"+log ) ;
        }
    }

    /**
     * 错误日志
     * @param obj
     * @param log
     */
    public static void logE( Object obj , String log ){
        if( isOutputLog ){
            Log.e( TAG , obj.getClass().toString()+"-->"+log ) ;
        }
    }

}
