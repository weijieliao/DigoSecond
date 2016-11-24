package com.digo.func_main.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.func_lease.fragment.LeaseFragment;
import com.digo.func_main.model.SquareFrgModel;
import com.digo.func_main.presenter.SquareFrgPresenter;
import com.digo.func_main.view_infc.SquareFrgView;
import com.digo.func_task.fragment.TaskFragment;
import com.digo.func_topic.fragment.TopicFragment;
import com.example.lwj.digosecond.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;

/**
 * Created by weijieliao on 2016/11/3.
 */

public class SquareFragment extends BaseFragment<SquareFrgPresenter> implements SquareFrgView,
        ViewPager.OnPageChangeListener, View.OnClickListener {

    private FragmentPagerAdapter frgAdapter ;
    private TaskFragment taskFragment = new TaskFragment() ;
    private TopicFragment topicFragment = new TopicFragment() ;
    private LeaseFragment leaseFragment = new LeaseFragment() ;
    private List<Fragment> frgList = new ArrayList<>() ;
    private List<TextView> tvList = new ArrayList<>() ;
    private List<ImageView> ivList = new ArrayList<>() ;
    private int currFrgIndex = 0 ;
    private int navTextColorNormal ;
    private int navTextColorSelected ;
    private int[] tabIds = { R.id.ll_task , R.id.ll_topic , R.id.ll_lease } ;

    @Bind( R.id.vp_content )
    ViewPager vpContent ;
    @Bind( R.id.ll_task )
    LinearLayout llTask ;
    @Bind( R.id.ll_topic )
    LinearLayout llTopic ;
    @Bind( R.id.ll_lease )
    LinearLayout llLease ;
    @Bind( R.id.tv_task )
    TextView tvTask ;
    @Bind( R.id.tv_topic )
    TextView tvTopic ;
    @Bind( R.id.tv_lease )
    TextView tvLease ;
    @Bind( R.id.iv_task )
    ImageView ivTask ;
    @Bind( R.id.iv_topic )
    ImageView ivTopic ;
    @Bind( R.id.iv_lease )
    ImageView ivLease ;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_square ;
    }

    @Override
    protected BasePresenterInfc getPresenter() {
        return new SquareFrgPresenter( this , new SquareFrgModel() ) ;
    }

    @Override
    public void initData() {

        navTextColorNormal = getResources().getColor( R.color.black ) ;
        navTextColorSelected = getResources().getColor( R.color.orange ) ;

    }

    @Override
    public void initView() {
        initTopNav() ;
    }

    @Override
    public void initEvent() {

        llTask.setOnClickListener( this ) ;
        llTopic.setOnClickListener( this ) ;
        llLease.setOnClickListener( this ) ;

    }

    @Override
    public boolean isLazyLoad() {
        return false ;
    }

    /**
     * 初始化顶部导航栏
     */
    private void initTopNav() {

        frgList.add( taskFragment ) ;
        frgList.add( topicFragment ) ;
        frgList.add( leaseFragment ) ;
        tvList.add( tvTask ) ;
        tvList.add( tvTopic ) ;
        tvList.add( tvLease ) ;
        ivList.add( ivTask ) ;
        ivList.add( ivTopic ) ;
        ivList.add( ivLease ) ;
        frgAdapter = new FragmentPagerAdapter( getChildFragmentManager() ) {
            @Override
            public Fragment getItem(int position) {
                return frgList.get( position ) ;
            }
            @Override
            public int getCount() {
                return frgList.size() ;
            }
        } ;
        //设置缓存个数
        vpContent.setOffscreenPageLimit( 2 ) ;
        vpContent.setAdapter( frgAdapter ) ;
        vpContent.addOnPageChangeListener( this ) ;

        //选择标签
        selectTab( currFrgIndex ) ;

    }

    /**
     * 选择标签
     * @param index
     */
    private void selectTab( int index ) {

        Iterator<TextView> tvIte = tvList.iterator() ;
        Iterator<ImageView> ivIte = ivList.iterator() ;
        int i = 0 ;
        while( tvIte.hasNext() ){
            TextView tv = tvIte.next() ;
            ImageView iv = ivIte.next() ;
            int textColor ;
            int visibility ;
            if( i == index ){
                //选中
                textColor = navTextColorSelected ;
                visibility = View.VISIBLE ;
            }
            else{
                textColor = navTextColorNormal ;
                visibility = View.GONE ;
            }
            tv.setTextColor( textColor ) ;
            iv.setVisibility( visibility ) ;
            i++ ;
        }
        currFrgIndex = index ;
        vpContent.setCurrentItem( currFrgIndex ) ;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectTab( position ) ;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        int length = tabIds.length ;
        int vid = v.getId() ;
        for( int i = 0 ; i < length ; i++ ){
            if( vid == tabIds[i] ){
                selectTab( i ) ;
                break ;
            }
        }

    }
}
