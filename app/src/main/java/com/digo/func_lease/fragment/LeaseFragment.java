package com.digo.func_lease.fragment;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.base.BaseView;
import com.digo.func_lease.model.LeaseModel;
import com.digo.func_lease.presenter.LeaseFrgPresenter;
import com.example.lwj.digosecond.R;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class LeaseFragment extends BaseFragment<LeaseFrgPresenter> implements BaseView {
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
