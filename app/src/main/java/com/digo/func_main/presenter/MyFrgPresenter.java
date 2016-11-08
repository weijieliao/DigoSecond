package com.digo.func_main.presenter;

import com.digo.base.BasePresenter;
import com.digo.func_main.model.MyFrgModel;
import com.digo.func_main.view_infc.MyFrgView;

/**
 * Created by weijieliao on 2016/11/3.
 */

public class MyFrgPresenter extends BasePresenter<MyFrgView,MyFrgModel> {

    public MyFrgPresenter(MyFrgView mainView, MyFrgModel mainModel) {
        super(mainView, mainModel);
    }

}
