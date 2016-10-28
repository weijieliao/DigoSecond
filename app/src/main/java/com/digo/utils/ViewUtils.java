package com.digo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digo.base.BaseApplication;
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
        return LayoutInflater.from(BaseApplication.getmApplication()).inflate(id,
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
        return LayoutInflater.from(BaseApplication.getmApplication()).inflate(id, parent);
    }

    /**
     * 根据id获取View
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }




}
