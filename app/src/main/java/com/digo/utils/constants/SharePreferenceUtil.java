package com.digo.utils.constants;

import android.app.Activity;
import android.content.SharedPreferences;

import com.digo.base.BaseApplication;

/**
 * SharePreference工具类
 * Created by weijieliao on 2016/11/2.
 */

public class SharePreferenceUtil {

    private static SharePreferenceUtil instance = null ;
    private static SharedPreferences sharedPreferences = null ;

    public static SharePreferenceUtil getInstance() {


        if ( instance == null ) {

            synchronized( SharePreferenceUtil.class ) {

                if ( instance == null ) {
                    instance = new SharePreferenceUtil() ;
                    sharedPreferences = BaseApplication.getApplication()
                            .getSharedPreferences( CommonConst.spName , Activity
                                    .MODE_PRIVATE);
                }
            }
        }
        return instance;
    }

    public void commit(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    public void commit(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void commit(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public String get(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public boolean get(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public int get(String key, int def) {
        return sharedPreferences.getInt(key, def);
    }

}
