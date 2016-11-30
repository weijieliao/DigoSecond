package com.digo.fun_add.activity;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.digo.adapter.PublishTaskUploadPhotoAdapter;
import com.digo.base.BaseActivity;
import com.digo.base.BasePresenterInfc;
import com.digo.fun_add.model.PublishTaskModel;
import com.digo.fun_add.presenter.PublishTaskPresenter;
import com.digo.fun_add.view_infc.PublishTaskView;
import com.digo.utils.ToastUtils;
import com.digo.utils.timerpickerutils.TimePickerView;
import com.digo.widget.DialogActionBottom;
import com.example.lwj.digosecond.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class PublishTaskActivity extends BaseActivity<PublishTaskPresenter> implements
        PublishTaskView {

    //"任务名称"EditText
    @Bind(R.id.et_task_name)
    EditText mEtTaskName;
    //"开始时间"RelativeLayout
    @Bind(R.id.ry_start_time)
    RelativeLayout mRyStartTime;
    //"结束时间"EditText
    @Bind(R.id.ry_end_time)
    RelativeLayout mRyEndTime;
    //"添加图片"ImageView
    @Bind(R.id.iv_add_photo)
    ImageView mImgAddPhoto;
    //RecycleView
    @Bind(R.id.recycleview_publish_task)
    RecyclerView mRecycleView;
    //"添加图片"标签TextView
    @Bind(R.id.tv_publish_task_add_photo)
    TextView mAddPhotoTextView;
    //定义"时间"选择器
    private TimePickerView mTimePickerView;
    //定义"添加图片"底部弹出框
    private DialogActionBottom mAddPhotoDialogActionBottom;
    //创建集合，用于保存"添加图片"底部弹出框中的item
    private ArrayList<String> mDialogList = new ArrayList<>();
    //定义集合，用于保存选中的图片
    private List<PhotoInfo> mPhotoList = new ArrayList<>();
    //定义适配器对象
    private PublishTaskUploadPhotoAdapter mAdapter;
    //定义"操作图片"底部弹出框
    private DialogActionBottom mOperatePhotoDialogActionBottom;
    //创建集合，用于保存"操作图片"底部弹出框中的item
    private ArrayList<String> mOpDialogList = new ArrayList<>();
    //定义标识符，用于保存"操作图片"的时候点击的图片position
    private int flag_click_photo = -1;

    @Override
    public void dobeforeSetContentView() {
        super.dobeforeSetContentView();
        setIsDisableTopBar(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_publish_task;
    }

    @Override
    public PublishTaskPresenter getPresenter() {
        return new PublishTaskPresenter(this, new PublishTaskModel());
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //设置标题
        super.setTitle("发布任务");
        //调用方法，初始化"开始时间"选择器
        initStartTimePickerView();
        //调用方法，初始化"添加图片"底部弹出框
        initDialogActionBottom();
        //调用方法，初始化RecycleView
        initRecycleView();
        //调用方法，初始化"操作图片"底部弹出框
        initOperateDialogActionBottom();
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.ry_start_time, R.id.ry_end_time, R.id.iv_add_photo})
    public void onClick(View view) {
        //创建InputMethodManager对象
        InputMethodManager imm = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        switch (view.getId()) {
            case R.id.ry_start_time:
                imm.hideSoftInputFromWindow(mEtTaskName.getWindowToken(), 0);
                mTimePickerView.show();
                break;
            case R.id.ry_end_time:
                imm.hideSoftInputFromWindow(mEtTaskName.getWindowToken(), 0);
                mTimePickerView.show();
                break;
            case R.id.iv_add_photo:
                mAddPhotoDialogActionBottom.show();
                break;
        }
    }

    /**
     * 初始化"开始时间"选择器
     */
    private void initStartTimePickerView() {
        //获取TimePickerView实例
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.ALL);
        mTimePickerView.setTime(new Date());
        mTimePickerView.setCyclic(false);
        mTimePickerView.setCancelable(true);
        mTimePickerView.setSubmitTextSize(16);
        mTimePickerView.setCancelTextSize(16);
        //设置按钮的颜色
//        mStartTimePickerView.setCancelTextColor(R.color.base_mian_color);
//        mStartTimePickerView.setSubmitTextColor(R.color.base_mian_color);
        mTimePickerView.setOnTimeSelectListener(new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                Log.e("TAG", "选择的时间是：" + getTime(date));
            }
        });
    }

    /**
     * 初始化"操作图片"底部弹出框
     */
    private void initOperateDialogActionBottom() {
        //创建实例
        mOperatePhotoDialogActionBottom = new DialogActionBottom(this);
        //设置item字体的颜色
        mOperatePhotoDialogActionBottom.setItemTextColor(android.R.color.background_dark);
        //设置底部弹出框数据
        mOpDialogList.add("预览");
        mOpDialogList.add("删除");
        mOperatePhotoDialogActionBottom.setDataList(mOpDialogList);
        //监听
        mOperatePhotoDialogActionBottom.setOnItemListener(new DialogActionBottom.OnItemListener() {
            @Override
            public void onItemClick(Button button, int position) {
                //取消
                if (position==DialogActionBottom.CANCLE){
                    Log.e("TAG","取消");
                }else if (position==0){  //预览
                    Log.e("TAG","预览");
                    //TODO
                }else if (position==1){  //删除
                    Log.e("TAG","删除");
                    if (flag_click_photo!=-1){
                        mPhotoList.remove(flag_click_photo);
                        mAdapter.setNewData(mPhotoList);
                    }
                    mOperatePhotoDialogActionBottom.dismiss();
                }
            }
        });

    }

    /**
     * 初始化"添加图片"底部弹出框
     */
    private void initDialogActionBottom() {
        //创建实例
        mAddPhotoDialogActionBottom = new DialogActionBottom(this);
        //设置item字体的颜色
        mAddPhotoDialogActionBottom.setItemTextColor(android.R.color.background_dark);
        //设置底部弹出框数据
        setListData();
        //监听底部弹出框
        mAddPhotoDialogActionBottom.setOnItemListener(new DialogActionBottom
                .OnItemListener() {

            @Override
            public void onItemClick(Button button, int position) {
                //取消按钮
                if (position == DialogActionBottom.CANCLE) {
                    //隐藏底部弹出框
                    mAddPhotoDialogActionBottom.dismiss();
                } else if (position == 0) {
                    if (mPhotoList.size() > 9) {
                        ToastUtils.showShort("已达到最大数量");
                    } else {
                        mPresenter.selectPhotoFromCamera();
                        mAddPhotoDialogActionBottom.dismiss();
                    }
                } else if (position == 1) {
                    if (mPhotoList.size() > 9) {
                        ToastUtils.showShort("已达到最大数量");
                    } else {
                        mPresenter.selectPhotoFromAlbum(10 - mPhotoList.size());
                        mAddPhotoDialogActionBottom.dismiss();
                    }
                }
            }
        });
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        //设置布局管理器
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        //创建适配器实例
        mAdapter = new PublishTaskUploadPhotoAdapter(this, mPhotoList);
        //设置适配器
        mRecycleView.setAdapter(mAdapter);

        //监听RecycleView的item
        mAdapter.addOnClickListener(new PublishTaskUploadPhotoAdapter.OnClickListenerRecycleView() {
            @Override
            public void onClick(List<PhotoInfo> list, int position) {
                if (list.size() <= 9 && position == list.size() - 1) {
                    mAddPhotoDialogActionBottom.show();
                } else {
                    flag_click_photo = position;
                    mOperatePhotoDialogActionBottom.show();
                }
            }
        });

    }

    @Override
    public void setSelectPhoto(List<PhotoInfo> list) {
        //如果list的值大于0，就证明已经添加了图片，所以需要先将"＋"号drawable清除，下面再添加，保证该图片在最后
        if (mPhotoList.size() > 0) {
            mPhotoList.remove(mPhotoList.size() - 1);
        }
        //循环将选中的图片添加到集合中
        for (PhotoInfo info : list) {
            mPhotoList.add(info);
        }
        //判断集合是否为空
        if (mPhotoList != null) {
            //隐藏默认状态下的drawable
            mAddPhotoTextView.setVisibility(View.GONE);
            mImgAddPhoto.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);

            //如果图片的数量小于9，那么就需要在集合中加上"＋"号的图片，以便于继续添加
            if (mPhotoList.size() < 10) {
                PhotoInfo info = new PhotoInfo();
                info.setPhotoPath("1");
                mPhotoList.add(info);
                mAdapter.setNewData(mPhotoList);
            } else {
                mAdapter.setNewData(mPhotoList);
            }
        }
    }

    /**
     * 添加底部弹出框中的数据
     */
    private void setListData() {
        mDialogList.add("拍照");
        mDialogList.add("从手机相册中选择");
        mAddPhotoDialogActionBottom.setDataList(mDialogList);
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}
