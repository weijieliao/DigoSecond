package com.digo.func_topic.fragment;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.base.BaseView;
import com.digo.func_topic.model.TopicModel;
import com.digo.func_topic.presenter.TopicFrgPresenter;
import com.example.lwj.digosecond.R;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class TopicFragment extends BaseFragment<TopicFrgPresenter> implements BaseView {

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_topic ;
    }

    @Override
    protected BasePresenterInfc getPresenter() {
        return new TopicFrgPresenter( this , new TopicModel() ) ;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean isLazyLoad() {
        return false;
    }
}
