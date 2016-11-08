package com.digo.utils;

/**
 * Created by weijieliao on 2016/11/8.
 */

public class DSNameValuePair {

    private String key ;
    private Object value ;

    public DSNameValuePair( String key , Object value ){
        this.key = key ;
        this.value = value ;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
