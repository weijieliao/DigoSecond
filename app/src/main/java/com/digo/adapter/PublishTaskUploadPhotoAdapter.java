package com.digo.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.example.lwj.digosecond.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * 显示"发布任务"需要上传的图片的适配器
 *
 * @author chenh
 */

public class PublishTaskUploadPhotoAdapter extends RecyclerView.Adapter<PublishTaskUploadPhotoAdapter.PublishTaskViewHolder> {

    //定义上下文对象
    private Context mContext;
    //定义集合，用于保存数据
    private List<PhotoInfo> mList;
    //定义接口对象
    private OnClickListenerRecycleView mListener;

    public PublishTaskUploadPhotoAdapter(Context context, List<PhotoInfo> list) {
        mContext = context;
        mList = list;
    }

    /**
     * 暴露方法，获取接口对象实例
     *
     * @param listener
     */
    public void addOnClickListener(OnClickListenerRecycleView listener) {
        mListener = listener;
    }

    @Override
    public PublishTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将布局转换成View
        View view = View.inflate(mContext, R.layout.publish_task_upload_photo_item, null);
        return new PublishTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishTaskViewHolder holder, final int position) {
        Uri uri = null;
        Log.e("TAG","保存图片集合的大小为："+mList.size());
        if (position == mList.size() - 1 && mList.size() < 10) {
            uri = Uri.parse("res://" + mContext.getPackageName() + "/" + R.drawable.options_add_defaut);
            Log.e("TAG", "uri的值为：" + uri.toString());
        } else {
            //创建Uri实例
            uri = Uri.parse("file://" + mList.get(position).getPhotoPath());
        }
        //创建ImageRequest对象
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, mContext.getResources()
                                .getDisplayMetrics()),
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, mContext.getResources()
                                .getDisplayMetrics())))
                .setAutoRotateEnabled(true)
                .build();
        //创建Controller对象
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(holder.mSimpleDraweeView.getController())
                .setImageRequest(imageRequest)
                .build();
        //设置Controller
        holder.mSimpleDraweeView.setController(controller);
        //监听
        holder.mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(mList, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 传入新数据
     *
     * @param list
     */
    public void setNewData(List<PhotoInfo> list) {
        if (list != null) {
            mList = list;
        }
        notifyDataSetChanged();
    }

    /**
     * 创建ViewHolder
     */
    class PublishTaskViewHolder extends RecyclerView.ViewHolder {
        //定义SimpleDraweeView对象
        private SimpleDraweeView mSimpleDraweeView;

        public PublishTaskViewHolder(View itemView) {
            super(itemView);
            //获取SimpleDraweeView实例
            mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.iv_publish_task_upload_photo);
        }
    }

    /**
     * RecycleView的点击事件
     */
    public interface OnClickListenerRecycleView {
        void onClick(List<PhotoInfo> list, int position);
    }
}
