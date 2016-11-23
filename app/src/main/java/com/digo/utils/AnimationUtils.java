package com.digo.utils;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 动画工具类
 *
 * @author chenh
 */

public class AnimationUtils {

    /**
     * 设置透明度动画
     *
     * @param view     操作的View
     * @param start    开始值
     * @param end      结束值
     * @param duration 动画持续时间
     * @return
     */
    public static ObjectAnimator setAlpha(View view, float start, float end, long
            duration) {
        //创建ObjectAnimator对象
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", start, end);
        //设置动画时间
        alphaAnimator.setDuration(duration);
        return alphaAnimator;
    }

    /**
     * 设置移动动画
     *
     * @param view
     * @param start
     * @param end
     * @param duration
     * @return
     */
    public static ObjectAnimator setTranslate(View view, float start, float end, long
            duration) {
        //创建ObjectAnimator对象
        ObjectAnimator tranlationYAnimator = ObjectAnimator.ofFloat(view,
                "translationY", start, end);
        //设置动画时间
        tranlationYAnimator.setDuration(duration);
        return tranlationYAnimator;
    }

    /**
     * 设置旋转动画
     *
     * @param view
     * @param start
     * @param end
     * @param duration
     * @return
     */
    public static ObjectAnimator setRotation(View view, float start, float end, long
            duration) {
        //创建ObjectAnimator对象
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, "rotation",
                start, end);
        //设置动画时间
        rotationAnimator.setDuration(duration);
        return rotationAnimator;
    }
}
