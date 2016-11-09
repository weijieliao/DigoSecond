package com.digo.func_task.fragment;

import com.digo.base.BaseFragment;
import com.digo.base.BasePresenterInfc;
import com.digo.base.BaseView;
import com.digo.func_task.model.TaskModel;
import com.digo.func_task.presenter.TaskFrgPresenter;
import com.example.lwj.digosecond.R;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class TaskFragment extends BaseFragment<TaskFrgPresenter> implements BaseView {
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
