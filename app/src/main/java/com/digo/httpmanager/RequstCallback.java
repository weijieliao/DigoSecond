package com.digo.httpmanager;

import android.util.Log;

import rx.Subscriber;

/**
 * 网络请求监听回调基类(观察者的封装)
 *
 * @author chenh
 */

public abstract class RequstCallback<T> extends Subscriber<T> implements
        RequstCallbackInfc<T> {

    @Override
    public void beforeRequst() {

    }

    @Override
    public abstract void requestSuccess(T object);

    @Override
    public void requestComplete() {

    }

    @Override
    public abstract void requestError(String msg);

    @Override
    public void onStart() {
        beforeRequst();
    }

    @Override
    public void onCompleted() {
        requestComplete();
    }

    @Override
    public void onError(Throwable e) {

        requestError(e.getMessage());
    }

    @Override
    public void onNext(T object) {
        if (object != null) {
            Log.e("TAG", "load success");
        } else {
            Log.e("TAG", "load fail");
        }
        requestSuccess(object);
    }
}
