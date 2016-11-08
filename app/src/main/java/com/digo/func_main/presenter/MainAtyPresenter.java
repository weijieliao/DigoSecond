package com.digo.func_main.presenter;

import com.digo.base.BasePresenter;
import com.digo.func_main.model.MainAtyModel;
import com.digo.func_main.view_infc.MainAtyView;

/**
 * Created by weijieliao on 2016/11/2.
 */

public class MainAtyPresenter extends BasePresenter<MainAtyView,MainAtyModel> {

    public MainAtyPresenter(MainAtyView mainView, MainAtyModel mainModel) {
        super(mainView, mainModel);
    }

}
