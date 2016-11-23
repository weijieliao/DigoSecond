package com.digo.httpmanager;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Http请求接口管理(所有使用Retrofit请求的接口都写在这里)
 *
 * @author chenh
 */
public interface HttpService {

    @FormUrlEncoded
    @POST("inhere/school/list")
    Observable<JSONObject> getTest(@Field("random") String random, @Field("signare")
            String signare);
}
