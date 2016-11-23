package com.digo.httpmanager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Http请求管理类
 *
 * @author chenh
 */
public class HttpManager {

    //请求地址
    private final static String baseUrl = "http://139.224.72.114/";

    //定义Retrofit对象
    private static Retrofit mRetrofit;
    //okHttp不证书的client
    private static OkHttpClient okHttpClient;
    //定义HttpService
    private static HttpService httpService;

    /**
     * 初始化OkHttpClient
     * <p>
     *  @param builder
     */
    public static void initOkHttpClient(OkHttpClient.Builder builder) {
        okHttpClient = builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30,
                TimeUnit.SECONDS).build();
        mRetrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        httpService = mRetrofit.create(HttpService.class);
    }


    public static HttpService getService() {
        return httpService;
    }
}
