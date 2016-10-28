package com.digo.httpmanager;

/**
 * 网络监听回调接口
 * @author chenh
 */

public interface RequstCallbackInfc<T> {

    /**
     * 请求之前调用
     */
    void beforeRequst();

    /**
     * 请求错误回调
     * @param msg
     */
    void requestError(String msg);

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求成功调用
     * @param data
     */
    void requestSuccess(T data);
}
