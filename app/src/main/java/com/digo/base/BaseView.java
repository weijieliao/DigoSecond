package com.digo.base;

import android.content.Intent;

/**
 * 基础View层接口
 * @author chenh
 */
public interface BaseView {

    void nextActivity(Class<?> targetClass);

    void nextActivity(Intent intent);

    void finishActivity();
}
