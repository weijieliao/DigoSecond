package com.digo.fun_add.view_infc;

import com.digo.base.BaseView;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * "发布话题"View
 * @author chenh
 */

public interface PublishTopicView extends BaseView{

    void setSelectPhoto(List<PhotoInfo> list);
}
