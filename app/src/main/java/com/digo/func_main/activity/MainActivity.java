package com.digo.func_main.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.digo.base.BaseActivity;
import com.digo.base.BasePresenterInfc;
import com.digo.func_main.fragment.MyFragment;
import com.digo.func_main.fragment.SquareFragment;
import com.digo.func_main.model.MainAtyModel;
import com.digo.func_main.presenter.MainAtyPresenter;
import com.digo.func_main.view_infc.MainAtyView;
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;

/**
 * Created by weijieliao on 2016/11/2.
 */

public class MainActivity extends BaseActivity<MainAtyPresenter> implements MainAtyView,
        View.OnClickListener {

    @Bind(R.id.tv_square)
    TextView tvSquare;
    @Bind(R.id.tv_my)
    TextView tvMy;

    private SquareFragment squareFragment = new SquareFragment();
    private MyFragment myFragment = new MyFragment();
    private List<Fragment> frgList = new ArrayList<>();
    private List<TextView> iconList = new ArrayList<>();
    private int[] navDraIdNormal = {R.drawable.tab_discover_nor, R.drawable.tab_my_nor};
    private int[] navDraIdSelected = {R.drawable.tab_discover_press, R.drawable.tab_my_press};
    private int[] iconIds = {R.id.tv_square, R.id.tv_my};
    private int navTextColorNormal;
    private int navTextColorSelected;
    private int currFrgIndex = 0;

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
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv.setCompoundDrawables(null, drawable, null, null);
            i++;
        }
        ft.commit();
        currFrgIndex = index;
//        vpContent.setCurrentItem( currFrgIndex ) ;

    }

//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
