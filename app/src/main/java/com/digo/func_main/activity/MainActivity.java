package com.digo.func_main.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
<<<<<<< HEAD
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
=======
import android.util.Log;
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digo.base.BaseActivity;
import com.digo.base.BasePresenterInfc;
import com.digo.fun_add.activity.PublishLeseaActivity;
import com.digo.fun_add.activity.PublishTaskActivity;
import com.digo.fun_add.activity.PublishTopicActivity;
import com.digo.func_main.fragment.MyFragment;
import com.digo.func_main.fragment.SquareFragment;
import com.digo.func_main.model.MainAtyModel;
import com.digo.func_main.presenter.MainAtyPresenter;
import com.digo.func_main.view_infc.MainAtyView;
<<<<<<< HEAD
import com.digo.utils.AESMall;
import com.digo.utils.AnimationUtils;
import com.digo.utils.ViewUtils;
import com.example.lwj.digosecond.R;

import java.io.UnsupportedEncodingException;
=======
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;

import java.lang.reflect.Method;
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by weijieliao on 2016/11/2.
 */

public class MainActivity extends BaseActivity<MainAtyPresenter> implements MainAtyView,
        View.OnClickListener {

    @Bind(R.id.tv_square)
    TextView tvSquare;
    @Bind(R.id.tv_my)
    TextView tvMy;
<<<<<<< HEAD
    @Bind(R.id.ry_add)
    RelativeLayout ryAdd;
    @Bind(R.id.iv_inhere_icon)
    ImageView ivInhereIcon;
    @Bind(R.id.ly_opinion_task)
    LinearLayout lyOpinionTask;
    @Bind(R.id.ly_opinion_topic)
    LinearLayout lyOpinionTopic;
    @Bind(R.id.ly_opinion_loan)
    LinearLayout lyOpinionLoan;
    @Bind(R.id.iv_cancel)
    ImageView ivCancel;
    @Bind(R.id.ly_type_inhere)
    LinearLayout lyTypeInhere;

=======
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535

    private SquareFragment squareFragment = new SquareFragment();
    private MyFragment myFragment = new MyFragment();
    private List<Fragment> frgList = new ArrayList<>();
    private List<TextView> iconList = new ArrayList<>();
    private int[] navDraIdNormal = {R.drawable.tab_discover_nor, R.drawable.tab_my_nor};
<<<<<<< HEAD
    private int[] navDraIdSelected = {R.drawable.tab_discover_press, R.drawable
            .tab_my_press};
=======
    private int[] navDraIdSelected = {R.drawable.tab_discover_press, R.drawable.tab_my_press};
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535
    private int[] iconIds = {R.id.tv_square, R.id.tv_my};
    private int navTextColorNormal;
    private int navTextColorSelected;
    private int currFrgIndex = 0;
<<<<<<< HEAD
    //定义标识，用于判断返回键的特殊情况
    private boolean isOpenAdd = false;
=======
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535

    @Override
    public void dobeforeSetContentView() {
        super.dobeforeSetContentView();
//        setStatusBarIconDark(true);
        //禁止显示标题栏，在setcontentview之前调用
        setIsDisableTopBar(true);
    }

//    @Override
//    public void doAfterSetContentView() {
//        super.doAfterSetContentView();
//        setStatusBarIconDark(true);
//    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenterInfc getPresenter() {
        return new MainAtyPresenter(this, new MainAtyModel());
    }

    @Override
    public void initData() {
<<<<<<< HEAD
=======

>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535
        navTextColorNormal = getResources().getColor(R.color.black);
        navTextColorSelected = getResources().getColor(R.color.orange);

    }

    @Override
    public void initView() {

        initBottomNav();

    }

    @Override
    public void initEvent() {
        Iterator<TextView> ite = iconList.iterator();
        while (ite.hasNext()) {
            TextView tv = ite.next();
            tv.setOnClickListener(this);
        }
    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNav() {

        frgList.add(squareFragment);
        frgList.add(myFragment);
        iconList.add(tvSquare);
        iconList.add(tvMy);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_content, myFragment);
        ft.add(R.id.fl_content, squareFragment);
        ft.commit();

//        frgAdapter = new FragmentPagerAdapter( getSupportFragmentManager() ) {
//            @Override
//            public Fragment getItem(int position) {
//                return frgList.get( position ) ;
//            }
//            @Override
//            public int getCount() {
//                return frgList.size() ;
//            }
//        } ;
//        vpContent.setAdapter( frgAdapter ) ;
//        vpContent.addOnPageChangeListener( this ) ;

        //选择标签
        selectTab(currFrgIndex);
    }

    /**
     * 选择标签
     *
     * @param index
     */
    private void selectTab(int index) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Iterator<TextView> iteTv = iconList.iterator();
        int i = 0;
        while (iteTv.hasNext()) {
//            Drawable drawable = getDrawable( navDraIdNormal[i] ) ;
            Drawable drawable;
            TextView tv = iteTv.next();
            int textColor;
            if (i != index) {
                drawable = getResources().getDrawable(navDraIdNormal[i]);
                textColor = navTextColorNormal;
                ft.hide(frgList.get(i));
            } else {
                drawable = getResources().getDrawable(navDraIdSelected[i]);
                textColor = navTextColorSelected;
                ft.show(frgList.get(i));
            }
            tv.setTextColor(textColor);
<<<<<<< HEAD
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable
                    .getMinimumHeight());
=======
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
>>>>>>> e14f126e8c0fefb6bb9dc17701014ee683116535
            tv.setCompoundDrawables(null, drawable, null, null);
            i++;
        }
        ft.commit();
        currFrgIndex = index;
//        vpContent.setCurrentItem( currFrgIndex ) ;

    }

//    @Override
//    public void onPageScrolled(int position, float positionOffset, int
// positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//        selectTab( position ) ;
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //打开了add界面
        if (isOpenAdd) {
            closeAnimation();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_add, R.id.ry_add, R.id.iv_cancel, R.id.ly_opinion_task, R.id
            .ly_opinion_topic, R.id.ly_opinion_loan})
    public void OnClick(View v) {
        //创建Intent对象
        Intent it = new Intent();
        switch (v.getId()) {
            //"添加"ImageView点击事件
            case R.id.iv_add:
                //打开动画
                openAnimation();
                break;
            //add主界面的点击事件
            case R.id.ry_add:
                //关闭动画
//                closeAnimation();
                break;
            //"取消"ImageView点击事件
            case R.id.iv_cancel:
                closeAnimation();
                break;
            //"任务"LinearLayout点击事件
            case R.id.ly_opinion_task:
                Toast.makeText(this, "任务", Toast.LENGTH_SHORT).show();
                toTaskAty(it);
                onTask();
                break;
            //"话题"LinearLayout点击事件
            case R.id.ly_opinion_topic:
                Toast.makeText(this, "话题", Toast.LENGTH_SHORT).show();
                toTopicAty(it);
                break;
            //"租借"LinearLayout点击事件
            case R.id.ly_opinion_loan:
                Toast.makeText(this, "租借", Toast.LENGTH_SHORT).show();
                toLeseaAty(it);
                break;

        }
    }

    /**
     * 跳转到"发布任务"界面
     *
     * @param it
     */
    private void toTaskAty(Intent it) {
        it.setClass(this, PublishTaskActivity.class);
        nextActivity(it);
    }

    /**
     * 跳转到"发布话题"界面
     *
     * @param it
     */
    private void toTopicAty(Intent it) {
        it.setClass(this, PublishTopicActivity.class);
        nextActivity(it);
    }

    /**
     * 跳转到"发布租借"界面
     *
     * @param it
     */
    private void toLeseaAty(Intent it) {
        it.setClass(this, PublishLeseaActivity.class);
        nextActivity(it);
    }

    private void onTask() {
        String random = "12122323";
        Map<String, Object> map = new HashMap<>();
        map.put("random", random);
        String signare = null;
        try {
            signare = AESMall.signParams(map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mPresenter.getTest(random, signare);

    }

    /**
     * 打开动画
     */
    private void openAnimation() {
        isOpenAdd = true;
        ryAdd.setVisibility(View.VISIBLE);
        //获取屏幕高度
        int height = ViewUtils.getScreenHeight(this);
        //创建动画集合
        AnimatorSet set = new AnimatorSet();
        set.play(AnimationUtils.setTranslate(ivInhereIcon, height, 0, 300))
                .with(AnimationUtils.setTranslate(lyOpinionTask, height, 0,
                        300))
                .with(AnimationUtils.setTranslate(lyOpinionTopic, height, 0,
                        300))
                .with(AnimationUtils.setTranslate(lyOpinionLoan, height, 0,
                        300))
                .with(AnimationUtils.setRotation(ivCancel, 0f, 360f, 300));
        set.start();
    }

    /**
     * 关闭动画
     */
    private void closeAnimation() {
        isOpenAdd = false;
        //获取屏幕高度
        int height = ViewUtils.getScreenHeight(this);
        //创建动画集合
        AnimatorSet set = new AnimatorSet();
        set.play(AnimationUtils.setTranslate(ivInhereIcon, 0, height, 300))
                .with(AnimationUtils.setTranslate(lyOpinionTask, 0, height,
                        300))
                .with(AnimationUtils.setTranslate(lyOpinionTopic, 0, height,
                        300))
                .with(AnimationUtils.setTranslate(lyOpinionLoan, 0, height,
                        300))
                .with(AnimationUtils.setRotation(ivCancel, 360f, 0f, 300));
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ryAdd.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {

//        switch( v.getId() ){
//            case R.id.tv_square :
//                selectTab( 0 ) ;
//                break ;
//            case R.id.tv_my :
//                selectTab( 1 ) ;
//                break ;
//            default : break ;
//        }
        int length = iconIds.length;
        int vid = v.getId();
        for (int i = 0; i < length; i++) {
            if (iconIds[i] == vid) {
                selectTab(i);
                break;
            }
        }

    }

//    private void setStatusBarIconDark(boolean dark) {
//        try {
//            Object win = getWindow();
//            Class<?> cls = win.getClass();
//            Method method = cls.getDeclaredMethod("setStatusBarIconDark", boolean.class);
//            method.invoke(win, dark);
//        } catch (Exception e) {
//            Log.v("ff", "statusBarIconDark,e=" + e);
////            LogUtil.logI( );
//        }
//    }
}
