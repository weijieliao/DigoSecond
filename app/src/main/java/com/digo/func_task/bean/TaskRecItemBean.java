package com.digo.func_task.bean;

import java.util.List;

/**
 * Created by weijieliao on 2016/11/15.
 */

public class TaskRecItemBean {

    /**
     * 类型：头部、尾部、元素、空提示
     */
    public static final int ITEM_TYPE_HEAD = 0 ;
    public static final int ITEM_TYPE_TAIL = 1 ;
    public static final int ITEM_TYPE_ITEM = 2 ;
    public static final int ITEM_TYPE_EMPTY_TIP = 3 ;
    public static final int ITEM_TYPE_LOAD_MORE = 4 ;

    private int itemType ;
    private String portraitUrl ;
    private String username ;
    private String publishTime ;
    private String textContent ;
    //图片url列表
    private List<String> picUrlList ;
    //缩略图url列表
    private List<String> smallMapUrlList ;
    private int shareNum ;
    private int commentNum ;
    private int supportNum ;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
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

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public List<String> getSmallMapUrlList() {
        return smallMapUrlList;
    }

    public void setSmallMapUrlList(List<String> smallMapUrlList) {
        this.smallMapUrlList = smallMapUrlList;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }
}
