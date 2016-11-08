package com.digo.func_main.fragment;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.func_main.model.SquareFrgModel;
import com.digo.func_main.presenter.SquareFrgPresenter;
import com.digo.func_main.view_infc.SquareFrgView;
import com.example.lwj.digosecond.R;

/**
 * Created by weijieliao on 2016/11/3.
 */

public class SquareFragment extends BaseFragment<SquareFrgPresenter> implements SquareFrgView{

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

    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean isLazyLoad() {
        return false ;
    }
}
