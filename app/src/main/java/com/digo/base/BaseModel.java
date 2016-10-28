package com.digo.base;

import android.util.SparseArray;

import com.digo.httpmanager.RequstCallback;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 基础model抽象类
 *
 * @author chenh
 */

public abstract class BaseModel {

    //缓存当前观察者，便于注销注册
    private SparseArray callArray = new SparseArray();

    protected void addSubcription1(int tag, RequstCallback<JSONObject> callback,
                                  Observable<JSONObject> ob) {
        if (callback != null && ob != null) {
            ob.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .timeout(20, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribe(callback);
        }
        if (callArray.get(tag, null) != null) {
            //注销
            ((RequstCallback<JSONObject>) callArray.get(tag)).unsubscribe();
            callArray.delete(tag);
        }
        callArray.put(tag, callback);
    }

    protected void addSubcription2(int tag, RequstCallback<JSONObject> callback,
                                   Observable<JSONObject> ob) {
        if (callback != null && ob != null) {
            ob.timeout(20, TimeUnit.SECONDS).subscribe(callback);
        }

        if (callArray.get(tag, null) != null) {
            //注销
            ((RequstCallback<JSONObject>) callArray.get(tag)).unsubscribe();
            callArray.delete(tag);
        }
        callArray.put(tag, callback);
    }

    /**
     * destroy的时候，全部注销，防止内存泄漏
     */
    public void destroy() {
        for (int i = 0; i < callArray.size(); i++) {
            ((RequstCallback<JSONObject>) callArray.valueAt(i)).unsubscribe();
        }
        callArray.clear();
        callArray = null;
    }
}
