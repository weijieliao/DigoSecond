package com.digo.base;

/**
 * 基类Presenter
 * @author chenh
 */
public class BasePresenter<V extends BaseView,M extends BaseModel> implements BasePresenterInfc{

    protected V mainView;
    protected M mainModel;

    public BasePresenter(V mainView, M mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mainModel.destroy();
        mainView = null;
    }
}
