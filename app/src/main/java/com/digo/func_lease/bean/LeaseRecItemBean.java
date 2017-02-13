package com.digo.func_lease.bean;

/**
 * Created by weijieliao on 2017/2/7.
 */

public class LeaseRecItemBean {

    /**
     * 类型：头部、尾部、元素、空提示
     */
    public static final int ITEM_TYPE_HEAD = 0 ;
    public static final int ITEM_TYPE_TAIL = 1 ;
    public static final int ITEM_TYPE_ITEM = 2 ;
    public static final int ITEM_TYPE_EMPTY_TIP = 3 ;
    public static final int ITEM_TYPE_LOAD_MORE = 4 ;

    private int itemType ;
    private String picUrl ;
    private String name ;
    private String introduction ;
    private String rent ;
    private String portraitUrl ;
    private String username ;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
