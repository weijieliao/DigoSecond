package com.digo.utils;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Gson工具类
 * Created by weijieliao on 2016/11/1.
 */

public class GsonUtil {

    public static Gson mGson = new Gson();

    /**
     * 解析Json数据为实体类
     * @param object
     * @param s
     * @param <E>
     * @return
     */
    public static <E extends Object> E toEntity(JSONObject object, Class<?> s){
        return  (E)mGson.fromJson(object.toString(),s);
    }

}
