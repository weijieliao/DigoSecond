package com.digo.func_task.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.base.BaseView;
import com.digo.func_task.adapter.TaskRecAdapter;
import com.digo.func_task.bean.TaskRecItemBean;
import com.digo.func_task.model.TaskModel;
import com.digo.func_task.presenter.TaskFrgPresenter;
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class TaskFragment extends BaseFragment<TaskFrgPresenter> implements BaseView,
        SwipeRefreshLayout.OnRefreshListener {

    private List<TaskRecItemBean> dataList = new ArrayList<>();

    @Bind( R.id.cv_content )
    RecyclerView cvContent ;
    @Bind( R.id.srl_refresh )
    SwipeRefreshLayout srlRefresh ;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_task ;
    }

    @Override
    protected BasePresenterInfc getPresenter() {
        return new TaskFrgPresenter( this , new TaskModel() ) ;
    }

    @Override
    public void initData() {

        TaskRecItemBean bean ;

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_EMPTY_TIP ) ;
        dataList.add( bean ) ;

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_ITEM ) ;
        bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setUsername( "萌妹子" ) ;
        bean.setReward( "5.00" ) ;
        bean.setPublishTime( "10分钟前" ) ;
        bean.setTextContent( "周末~ 吃！吃！吃！你们怎么过周末呀\uD83D\uDE01\uD83C\uDF39\uD83C\uDF3A…" ) ;
        List<String> piclist = new ArrayList<>() ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        bean.setPicUrlList( piclist ) ;
        List<String> smallMapUrlList = new ArrayList<>() ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setSmallMapUrlList( smallMapUrlList ) ;
        dataList.add( bean ) ;

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_ITEM ) ;
        bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setUsername( "萌妹子" ) ;
        bean.setReward( "5.00" ) ;
        bean.setPublishTime( "20分钟前" ) ;
        bean.setTextContent( "周末~ 吃！吃！吃！你们怎么过周末呀\uD83D\uDE01\uD83C\uDF39\uD83C\uDF3A…" ) ;
        piclist = new ArrayList<>() ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        bean.setPicUrlList( piclist ) ;
        smallMapUrlList = new ArrayList<>() ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setSmallMapUrlList( smallMapUrlList ) ;
        dataList.add( bean ) ;

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_ITEM ) ;
        bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setUsername( "萌妹子" ) ;
        bean.setReward( "5.00" ) ;
        bean.setPublishTime( "20分钟前" ) ;
        bean.setTextContent( "周末~ 吃！吃！吃！你们怎么过周末呀\uD83D\uDE01\uD83C\uDF39\uD83C\uDF3A…" ) ;
        piclist = new ArrayList<>() ;
        piclist.add( "http://pic.nipic.com/2008-04-18/2008418115449438_2.jpg" ) ;
        bean.setPicUrlList( piclist ) ;
        smallMapUrlList = new ArrayList<>() ;
        smallMapUrlList.add( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setSmallMapUrlList( smallMapUrlList ) ;
        dataList.add( bean ) ;

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_ITEM ) ;
        bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
        bean.setUsername( "萌妹子" ) ;
        bean.setReward( "5.00" ) ;
        bean.setPublishTime( "20分钟前" ) ;
        bean.setTextContent( "周末~ 吃！吃！吃！你们怎么过周末呀\uD83D\uDE01\uD83C\uDF39\uD83C\uDF3A…" ) ;
        piclist = new ArrayList<>() ;
        bean.setPicUrlList( piclist ) ;
        smallMapUrlList = new ArrayList<>() ;
        bean.setSmallMapUrlList( smallMapUrlList ) ;
        dataList.add( bean ) ;

        for( int i = 0 ; i < 10 ; i++ ){
            bean = new TaskRecItemBean() ;
            bean.setItemType( TaskRecItemBean.ITEM_TYPE_ITEM ) ;
            bean.setPortraitUrl( "http://139.224.72.114/inhere/image/picture" ) ;
            bean.setUsername( "萌妹子" ) ;
            bean.setReward( "5.00" ) ;
            bean.setPublishTime( "20分钟前" ) ;
            bean.setTextContent( "周末~ 吃！吃！吃！你们怎么过周末呀\uD83D\uDE01\uD83C\uDF39\uD83C\uDF3A…" ) ;
            piclist = new ArrayList<>() ;
            bean.setPicUrlList( piclist ) ;
            smallMapUrlList = new ArrayList<>() ;
            bean.setSmallMapUrlList( smallMapUrlList ) ;
            dataList.add( bean ) ;
        }

        bean = new TaskRecItemBean() ;
        bean.setItemType( TaskRecItemBean.ITEM_TYPE_LOAD_MORE ) ;
        dataList.add( bean ) ;

    }

    @Override
    public void initView() {
        //recycleview内容
        cvContent.setLayoutManager( new LinearLayoutManager( mContext ) ) ;
        cvContent.setAdapter( new TaskRecAdapter( mContext , dataList ) ) ;
        //下拉刷新
//        srlRefresh.setProgressBackgroundColorSchemeResource( R.color.orange ) ;
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
