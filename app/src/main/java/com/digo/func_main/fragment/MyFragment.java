package com.digo.func_main.fragment;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.func_main.model.MyFrgModel;
import com.digo.func_main.presenter.MyFrgPresenter;
import com.digo.func_main.view_infc.MyFrgView;
import com.example.lwj.digosecond.R;

/**
 * Created by weijieliao on 2016/11/3.
 */

public class MyFragment extends BaseFragment<MyFrgPresenter> implements MyFrgView{

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_my ;
    }

    @Override
    protected BasePresenterInfc getPresenter() {
        return new MyFrgPresenter( this , new MyFrgModel() ) ;
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
