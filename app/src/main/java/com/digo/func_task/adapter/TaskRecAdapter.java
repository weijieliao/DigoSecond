package com.digo.func_task.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digo.func_task.bean.TaskRecItemBean;
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by weijieliao on 2016/11/15.
 */

public class TaskRecAdapter extends RecyclerView.Adapter<TaskRecAdapter.TaskRecViewHolder> {

    private Context context ;
    private List<TaskRecItemBean> dataList ;

    private int screenWidth ;
//    private int screenHeight ;

    public TaskRecAdapter(Context context,List<TaskRecItemBean> dataList){
        this.context = context ;
        this.dataList = dataList ;
        Point point = new Point() ;
        ( ( Activity )context ).getWindowManager().getDefaultDisplay().getSize( point ) ;
        screenWidth = point.x ;
    }

    @Override
    public TaskRecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutResId = R.layout.item_task ;
        switch( viewType ){
            case TaskRecItemBean.ITEM_TYPE_ITEM :
                layoutResId = R.layout.item_task ;
                break ;
            case TaskRecItemBean.ITEM_TYPE_EMPTY_TIP :
                layoutResId = R.layout.item_task_empty_tip ;
                break ;
            case TaskRecItemBean.ITEM_TYPE_LOAD_MORE :
                layoutResId = R.layout.item_task_load_more ;
                break ;
            default : break ;
        }
        TaskRecViewHolder holder = new TaskRecViewHolder(LayoutInflater.from( context ).inflate(
                layoutResId , parent , false ) , viewType ) ;

        return holder ;
    }

    @Override
    public void onBindViewHolder(TaskRecViewHolder holder, int position) {

        switch( dataList.get( position ).getItemType() ){
            case TaskRecItemBean.ITEM_TYPE_ITEM :
                holder.sdvPortrait.setImageURI( dataList.get( position ).getPortraitUrl() ) ;
                holder.tvUsername.setText( dataList.get( position ).getUsername() ) ;
                holder.tvPublishTime.setText( dataList.get( position ).getPublishTime() ) ;
                holder.tvTextContent.setText( dataList.get( position ).getTextContent() ) ;
                holder.glPictures.removeAllViews() ;
                int picWidth = 0 ;
                List<String> list = dataList.get( position ).getSmallMapUrlList() ;
                if( null != list && list.size() != 0 ){
                    int size = list.size() ;
                    if( size == 1 ){
                        picWidth = (screenWidth-200)*2/3 ;
                    }
                    else{
                        picWidth = (screenWidth-200)/3 ;
                    }
                    for( int i = 0 ; i < size ; i++ ){
                        addPicture( holder.glPictures , list.get( i ) , picWidth ) ;
                    }
                }
                break ;
            case TaskRecItemBean.ITEM_TYPE_EMPTY_TIP :
                holder.tvPulish.setOnClickListener( new OnPulishBtnClickListener() ) ;
                break ;
            case TaskRecItemBean.ITEM_TYPE_LOAD_MORE :
                LogUtil.logI( TaskRecAdapter.this , "****1" ) ;
                break ;
            default : break ;
        }

    }

    private void addPicture( ViewGroup parent , String url , int picWidth ) {

        SimpleDraweeView sdv = ( SimpleDraweeView )LayoutInflater.from( context ).inflate
                ( R.layout.view_fresco_pic , parent , false ) ;
        GridLayout.LayoutParams lp = (GridLayout.LayoutParams) sdv.getLayoutParams() ;
        lp.width = picWidth ;
        lp.setMargins( 10 , 10 , 10 , 10 ) ;
        sdv.setLayoutParams( lp ) ;
        Uri uri = Uri.parse( url ) ;
        sdv.setImageURI( uri ) ;
        parent.addView( sdv ) ;

        sdv.setOnClickListener( new OnPicClickListener() ) ;

    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get( position ).getItemType() ;
    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
    }

    class TaskRecViewHolder extends RecyclerView.ViewHolder{

        //item
        private SimpleDraweeView sdvPortrait ;
        private TextView tvUsername ;
        private TextView tvPublishTime ;
        private TextView tvTextContent ;
        private GridLayout glPictures ;

        //emptyTip
        private TextView tvPulish ;

        public TaskRecViewHolder(View itemView , int viewType ) {
            super(itemView);
            setViewNull() ;
            switch( viewType ){
                case TaskRecItemBean.ITEM_TYPE_ITEM :
                    sdvPortrait = ( SimpleDraweeView )itemView.findViewById( R.id.sdv_portrait ) ;
                    tvUsername = ( TextView )itemView.findViewById( R.id.tv_username ) ;
                    tvPublishTime = ( TextView )itemView.findViewById( R.id.tv_publish_time ) ;
                    tvTextContent = ( TextView )itemView.findViewById( R.id.tv_text_content ) ;
                    glPictures = ( GridLayout )itemView.findViewById( R.id.gl_pictures ) ;
                    break ;
                case TaskRecItemBean.ITEM_TYPE_EMPTY_TIP :
                    tvPulish = ( TextView )itemView.findViewById( R.id.tv_pulish ) ;
                    break ;
                case TaskRecItemBean.ITEM_TYPE_LOAD_MORE :
                    LogUtil.logI( TaskRecAdapter.this , "***2" ) ;
                    break ;
                default : break ;
            }
        }

        private void setViewNull() {

            sdvPortrait = null ;
            tvUsername = null ;
            tvPublishTime = null ;
            tvTextContent = null ;
            glPictures = null ;

        }

    }

    class OnPulishBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LogUtil.logI( TaskRecAdapter.this , "publish button click" ) ;
        }
    }

    class OnPicClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            LogUtil.logI( TaskRecAdapter.this , "onPicClick" ) ;
        }
    }

}
