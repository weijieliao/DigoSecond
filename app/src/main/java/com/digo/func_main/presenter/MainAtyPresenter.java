package com.digo.func_main.presenter;

import android.util.Log;

import com.digo.base.BasePresenter;
import com.digo.func_main.model.MainAtyModel;
import com.digo.func_main.view_infc.MainAtyView;
import com.digo.httpmanager.HttpManager;
import com.digo.httpmanager.HttpService;
import com.digo.httpmanager.JsonConverterFactory;
import com.digo.httpmanager.RequstCallback;
import com.digo.json_bean.TestBean;
import com.digo.utils.LogUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weijieliao on 2016/11/2.
 */

public class MainAtyPresenter extends BasePresenter<MainAtyView, MainAtyModel> {

    public MainAtyPresenter(MainAtyView mainView, MainAtyModel mainModel) {
        super(mainView, mainModel);
    }

    public void getTest(String random, String signare) {

        mainModel.getTest(new RequstCallback<JSONObject>() {
            @Override
            public void requestSuccess(JSONObject object) {
                LogUtil.logE("TAG", "返回的结果为：" + object.toString());
            }

            @Override
            public void requestError(String msg) {
                LogUtil.logE("TAG", "错误信息：" + msg);
            }
        }, random, signare);


    }

}
