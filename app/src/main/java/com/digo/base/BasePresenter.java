package com.digo.base;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 基类Presenter
 * @author chenh
 */
public class BasePresenter<V extends BaseView,M extends BaseModel> implements BasePresenterInfc{

    protected V mainView;
    protected M mainModel;
    protected Gson mGson;

    public BasePresenter(V mainView, M mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        mGson = new Gson();
    }

    @Override
    public void onCreate() {

    }

    /**
     * 解析Json数据为实力类
     * @param object
     * @param s
     * @param <E>
     * @return
     */
    public <E extends Object> E toEntity(JSONObject object, Class<?>s){
        return  (E)mGson.fromJson(object.toString(),s);
    }

    @Override
    public void onDestroy() {
        mainModel.destroy();
        mainView = null;
    }
}
