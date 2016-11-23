package com.digo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digo.base.BaseApplication;
import com.digo.func_main.activity.MainActivity;
import com.example.lwj.digosecond.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * View的工具类
 *
 * @author chenh
 */
public class ViewUtils {

    /**
     * 将布局转换成View 上下文对象为Application
     *
     * @param id
     * @return
     */
    public static View inflate(int id) {
        return LayoutInflater.from(BaseApplication.getApplication()).inflate(id,
                null);
    }

    /**
     * 将布局转换成View
     *
     * @param context
     * @param id
     * @return
     */
    public static View inflateWithContext(Context context, int id) {
        return LayoutInflater.from(context).inflate(id, null);
    }

    /**
     * 将布局转换成View
     *
     * @param parent
     * @param id
     * @return
     */
    public static View inflateWithParent(ViewGroup parent, int id) {
        return LayoutInflater.from(BaseApplication.getApplication()).inflate(id, parent);
    }

    /**
     * 根据id获取View
     *
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        //获取屏幕高度
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        //获取屏幕高度
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


}
