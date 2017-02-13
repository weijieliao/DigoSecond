package com.digo.func_lease.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.base.BaseView;
import com.digo.func_lease.adapter.LeaseRecAdapter;
import com.digo.func_lease.bean.LeaseRecItemBean;
import com.digo.func_lease.model.LeaseModel;
import com.digo.func_lease.presenter.LeaseFrgPresenter;
import com.digo.func_task.adapter.TaskRecAdapter;
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class LeaseFragment extends BaseFragment<LeaseFrgPresenter> implements BaseView ,
        SwipeRefreshLayout.OnRefreshListener{

    private List<LeaseRecItemBean> dataList = new ArrayList<>() ;

    @Bind( R.id.cv_content )
    RecyclerView cvContent ;
    @Bind( R.id.srl_refresh )
    SwipeRefreshLayout srlRefresh ;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_lease ;
    }

    @Override
    protected BasePresenterInfc getPresenter() {
        return new LeaseFrgPresenter( this , new LeaseModel() ) ;
    }

    @Override
    public void initData() {

        LeaseRecItemBean bean ;

        bean = new LeaseRecItemBean() ;
        bean.setItemType( LeaseRecItemBean.ITEM_TYPE_EMPTY_TIP ) ;
        dataList.add( bean ) ;

        for( int i = 0 ; i < 30 ; i++ ){
            bean = new LeaseRecItemBean() ;
            bean.setItemType( LeaseRecItemBean.ITEM_TYPE_ITEM ) ;
            bean.setPicUrl( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
            bean.setName( "刘军——《智能时代》" ) ;
            bean.setIntroduction( "罗胖子推荐的书，刚到手" ) ;
            bean.setRent( "5.00" ) ;
            bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
            bean.setUsername( "萌妹子" ) ;
            dataList.add( bean ) ;
        }
        bean = new LeaseRecItemBean() ;
        bean.setItemType( LeaseRecItemBean.ITEM_TYPE_LOAD_MORE ) ;
        dataList.add( bean ) ;

    }

    @Override
    public void initView() {

        //recycleview内容
        cvContent.setLayoutManager( new LinearLayoutManager( mContext ) ) ;
        cvContent.setAdapter( new LeaseRecAdapter( mContext , dataList ) ) ;
        //下拉刷新
        srlRefresh.setColorSchemeResources( R.color.orange ) ;

    }

    @Override
    public void initEvent() {

        srlRefresh.setOnRefreshListener( this ) ;

    }

    @Override
    public boolean isLazyLoad() {
        return false;
    }

    @Override
    public void onRefresh() {

        LogUtil.logI( this , "onRefresh" ) ;

    }
}
