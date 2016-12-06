package com.digo.fun_add.presenter;

import com.digo.base.BasePresenter;
import com.digo.fun_add.model.PublishTopicModel;
import com.digo.fun_add.view_infc.PublishTopicView;
import com.digo.utils.LogUtil;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * "发布话题"presenter
 * @author chenh
 */

public class PublishTopicPresenter extends BasePresenter<PublishTopicView,PublishTopicModel>{

    public PublishTopicPresenter(PublishTopicView mainView, PublishTopicModel mainModel) {
        super(mainView, mainModel);
    }

    /**
     * 从拍照中选择图片
     */
    public void selectPhotoFromCamera() {
        GalleryFinal.openCamera(1, callback);
    }

    /**
     * 从手机相册中选择图片
     * @param photoNum
     */
    public void selectPhotoFromAlbum(int photoNum) {
        GalleryFinal.openGalleryMuti(2, photoNum, callback);
    }

    /**
     * 选中图片的回调
     */
    GalleryFinal.OnHanlderResultCallback callback = new GalleryFinal
            .OnHanlderResultCallback() {

        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            mainView.setSelectPhoto(resultList);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            LogUtil.logI("PublishTaskPresenter", errorMsg);
        }
    };
}
