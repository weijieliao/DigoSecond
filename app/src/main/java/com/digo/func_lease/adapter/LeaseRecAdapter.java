package com.digo.func_lease.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digo.func_lease.bean.LeaseRecItemBean;
import com.digo.utils.LogUtil;
import com.example.lwj.digosecond.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by weijieliao on 2017/2/7.
 */

public class LeaseRecAdapter extends RecyclerView.Adapter<LeaseRecAdapter.LeaseRecViewHolder> {

    private Context context ;
    private List<LeaseRecItemBean> dataList ;

    public LeaseRecAdapter( Context context , List<LeaseRecItemBean> dataList) {

        this.context = context ;
        this.dataList = dataList ;

    }

    @Override
    public LeaseRecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutResId = R.layout.item_lease ;
        switch( viewType ){
            case LeaseRecItemBean.ITEM_TYPE_ITEM :{
                layoutResId = R.layout.item_lease ;
                break ;
            }
            case LeaseRecItemBean.ITEM_TYPE_EMPTY_TIP :{
                layoutResId = R.layout.item_lease_empty_tip ;
                break ;
            }
            case LeaseRecItemBean.ITEM_TYPE_LOAD_MORE :{
                layoutResId = R.layout.item_lease_load_more ;
                break ;
            }
        }
        LeaseRecViewHolder holder = new LeaseRecViewHolder( LayoutInflater.from( context )
                .inflate( layoutResId , parent , false ) , viewType ) ;

        return holder ;
    }

    @Override
    public void onBindViewHolder(LeaseRecViewHolder holder, int position) {

        switch( dataList.get( position ).getItemType() ){
            case LeaseRecItemBean.ITEM_TYPE_ITEM :{
                ( ( SimpleDraweeView )holder.getView( R.id.sdv_pic ) ).setImageURI( dataList.get(
                    position ).getPicUrl() ) ;
                ( ( TextView )holder.getView( R.id.tv_name ) ).setText( dataList.get( position )
                        .getName() ) ;
                ( ( TextView )holder.getView( R.id.tv_introduction ) ).setText( dataList.get( position )
                        .getIntroduction() ) ;
                ( ( TextView )holder.getView( R.id.tv_rent_2 ) ).setText( dataList.get( position )
                        .getRent() ) ;
                ( ( SimpleDraweeView )holder.getView( R.id.sdv_portrait ) ).setImageURI(
                        dataList.get( position ).getPortraitUrl() ) ;
                ( ( TextView )holder.getView( R.id.tv_username ) ).setText( dataList.get( position )
                        .getUsername() ) ;
                break ;
            }
            case LeaseRecItemBean.ITEM_TYPE_EMPTY_TIP :{
                ( ( TextView )holder.getView( R.id.tv_pulish ) ).setOnClickListener( new
                        OnPulishBtnClickListener() ) ;
                break ;
            }
            case LeaseRecItemBean.ITEM_TYPE_LOAD_MORE :{
                break ;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);

        return dataList.get( position ).getItemType() ;

    }

    class LeaseRecViewHolder extends RecyclerView.ViewHolder{

        private SparseArray<View> views = new SparseArray<>() ;

        public LeaseRecViewHolder(View itemView , int viewType ) {
            super(itemView);

            switch( viewType ){
                case LeaseRecItemBean.ITEM_TYPE_ITEM :{
                    views.append( R.id.sdv_pic , itemView.findViewById( R.id.sdv_pic ) ) ;
                    views.append( R.id.tv_name , itemView.findViewById( R.id.tv_name ) ) ;
                    views.append( R.id.tv_introduction , itemView.findViewById( R.id.tv_introduction ) ) ;
                    views.append( R.id.tv_rent_2 , itemView.findViewById( R.id.tv_rent_2 ) ) ;
                    views.append( R.id.sdv_portrait , itemView.findViewById( R.id.sdv_portrait ) ) ;
                    views.append( R.id.tv_username , itemView.findViewById( R.id.tv_username ) ) ;
                    break ;
                }
                case LeaseRecItemBean.ITEM_TYPE_EMPTY_TIP :{
                    views.append( R.id.tv_pulish , itemView.findViewById( R.id.tv_pulish ) ) ;
                    break ;
                }
                case LeaseRecItemBean.ITEM_TYPE_LOAD_MORE :{
                    break ;
                }
            }

        }

        public View getView( int id ){
            return views.get( id ) ;
        }

    }

    class OnPulishBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LogUtil.logI( LeaseRecAdapter.this , "publish button click" ) ;
        }
    }

}
