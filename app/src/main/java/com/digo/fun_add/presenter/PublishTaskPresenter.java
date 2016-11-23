package com.digo.fun_add.presenter;

import android.util.Log;

import com.digo.base.BasePresenter;
import com.digo.fun_add.model.PublishTaskModel;
import com.digo.fun_add.view_infc.PublishTaskView;
import com.digo.utils.LogUtil;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * "发布任务"presenter
 *
 * @author chenh
 */

public class PublishTaskPresenter extends BasePresenter<PublishTaskView,
        PublishTaskModel> {

    /**
     * 构造器
     *
     * @param mainView  view
     * @param mainModel model
     */
    public PublishTaskPresenter(PublishTaskView mainView, PublishTaskModel mainModel) {
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
