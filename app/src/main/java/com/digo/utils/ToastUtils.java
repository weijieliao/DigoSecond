package com.digo.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digo.base.BaseApplication;
import com.example.lwj.digosecond.R;

/**
 * Toast的工具类
 *
 * @author chenh
 */

public class ToastUtils {

    //定义ToastUtils实例
    private static ToastUtils mInstance;
    //定义Toast对象
    private static Toast mToast;
    //定义上下文对象
    private static Context mContext;
    //定义布局
    private static View toastLayout;
    //定义ImageView
    private static ImageView imageView;
    //定义TextView
    private static TextView tv;

    /**
     * 初始化
     */
    private static void initToast() {
        //获取上下文对象
        mContext = BaseApplication.getApplication();
        //获取Toast对象
        mToast = new Toast(mContext);
        //将布局转换成View
        toastLayout = LayoutInflater.from(mContext).inflate(R.layout.widget_toast, null);
        imageView = (ImageView) toastLayout.findViewById(R.id.toast_image);
        tv = (TextView) toastLayout.findViewById(R.id.toast_textview);
        //设置Toast的位置
        mToast.setGravity(Gravity.CENTER, 0, (int) TypedValue.applyDimension(TypedValue
                .COMPLEX_UNIT_DIP, 15, mContext.getResources().getDisplayMetrics()));
        mToast.setView(toastLayout);
    }

    /**
     * 显示长时间的Toast(显示文字)
     *
     * @param str
     */
    public static void showLong(String str) {
        if (mToast == null) {
            //初始化
            initToast();
        }
        //设置持续时间
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 利用id显示
     *
     * @param id
     */
    public static void showLong(@StringRes int id) {
        if (mToast == null) {
            initToast();
        }
        //设置持续时间
        mToast.setDuration(Toast.LENGTH_LONG);
        tv.setText(mContext.getResources().getString(id));
        mToast.show();
    }

    /**
     * 短时间显示
     *
     * @param str
     */
    public static void showShort(String str) {
        if (mToast == null) {
            initToast();
        }
        //设置持续时间
        mToast.setDuration(Toast.LENGTH_SHORT);
        tv.setText(str);
        mToast.show();
    }

    public static void showShort(@StringRes int id) {
        if (mToast == null) {
            initToast();
        }
        //设置持续时间
        mToast.setDuration(Toast.LENGTH_SHORT);
        tv.setText(mContext.getResources().getString(id));
        mToast.show();
    }
}
