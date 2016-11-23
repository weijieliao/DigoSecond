package com.digo.func_main.model;

import com.digo.base.BaseModel;
import com.digo.httpmanager.HttpManager;
import com.digo.httpmanager.RequstCallback;

import org.json.JSONObject;

/**
 * Created by weijieliao on 2016/11/2.
 */

public class MainAtyModel extends BaseModel {

    public void getTest(RequstCallback<JSONObject> callback, String random,String singare) {
        addSubcription1(1, callback, HttpManager.getService().getTest(random,singare));
    }
}
