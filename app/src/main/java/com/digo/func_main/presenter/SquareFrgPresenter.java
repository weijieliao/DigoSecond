package com.digo.func_main.presenter;

import com.digo.base.BasePresenter;
import com.digo.func_main.model.SquareFrgModel;
import com.digo.func_main.view_infc.SquareFrgView;

/**
 * Created by weijieliao on 2016/11/3.
 */

public class SquareFrgPresenter extends BasePresenter<SquareFrgView,SquareFrgModel> {

    public SquareFrgPresenter(SquareFrgView mainView, SquareFrgModel mainModel) {
        super(mainView, mainModel);
    }

}
