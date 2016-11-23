package com.digo.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.ColorRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digo.base.BaseApplication;
import com.example.lwj.digosecond.R;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

/**
 * @author Weavey
 * @date 2015-12-4
 * @explain 底部弹出的对话框 用法： ActionBottomDialog dialog = new
 * ActionBottomDialog(this,dialogDatas); dialog.setTitle("dfaf");
 * dialog.setTitleVisible(false); dialog.setTitleHeight(100);
 * dialog.setTitleColor(getResources().getColor(R.color.text_gray));
 * dialog.setTitleTextSize(20);
 * dialog.setItemTextColor(getResources().getColor
 * (R.color.orange_red)); dialog.setItemTextSize(11); dialog.show();
 * <p>
 * dialog.setOnItemListener(new OnItemListener() {
 * @Override public void onItemClick(Button view,int position) {
 * <p>
 * if(position==DialogActionBottom.CANCLE){ //监听最下面的按钮 (一般为“取消”)
 * <p>
 * dialog.dismiss(); }
 * <p>
 * } });
 */

public class DialogActionBottom {

    private TextView title;
    private Button bottomBtn;
    private LinearLayout linearLayout;

    private Dialog mDialog;
    private View dialogView;
    private Context context;
    private ArrayList<String> datas;
    private int itemTextColor;
    private int itemTextSize;
    private int itemHeight;
    private boolean boolTitle;
    private boolean isShowed;
    public final static int CANCLE = -1000; //最下方“取消”键的标志
    private int selectPosition;//最后一次选择的位置
    private OnItemListener onItemListener;
    //定义屏幕的宽度和高度
    private int screenWidth;
    private int screenHeight;

    public DialogActionBottom(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) BaseApplication.getApplication()
                .getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        screenWidth = point.x;
        screenHeight = point.y;
        this.context = context;
        isShowed = false;
        initDialog();
    }

    /**
     * @explain 初始化dialog界面并添加监听器
     */
    private void initDialog() {

        mDialog = new Dialog(context, R.style.bottomDialogStyle);
        dialogView = View.inflate(context, R.layout.widget_bottom_dialog, null);

        mDialog.setContentView(dialogView); // 一定要在setAttributes(lp)之前
        // setAttributes(lp)才有效

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (screenWidth * 0.92);
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);

        itemTextColor = context.getResources().getColor(R.color.black); // 默认颜色
        //默认item高度为45dp
        itemHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45,
                context.getResources().getDisplayMetrics());
        itemTextSize = 14;
        boolTitle = false; // 默认关闭标题

        title = (TextView) dialogView.findViewById(R.id.action_dialog_title);
        linearLayout = (LinearLayout) dialogView
                .findViewById(R.id.action_dialog_linearlayout);
        bottomBtn = (Button) dialogView.findViewById(R.id.action_dialog_botbtn);
        bottomBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mDialog.dismiss();
                if (onItemListener != null)
                    onItemListener.onItemClick(bottomBtn, CANCLE);

            }
        });

        this.datas = new ArrayList<>();
    }

    private void loadItemDatas() {


        if (boolTitle == true) {

            if (datas.size() != 0) {
                title.setBackgroundResource(R.drawable.selector_widget_actiondialog_top);

            } else {
                title.setBackgroundResource(R.drawable.selector_btn_back_white2gray);
            }
//			title.setBackgroundResource(R.drawable.shape_white_corner8);
        }
        if (datas.size() == 1) {

            Button button = getButton(datas.get(0), 0);
            if (boolTitle)
                button.setBackgroundResource(R.drawable
                        .selector_widget_actiondialog_bottom);
            else
                button.setBackgroundResource(R.drawable.selector_btn_back_white2gray);

            linearLayout.addView(button);

        } else if (datas.size() > 1) {

            for (int i = 0; i < datas.size(); i++) {

                Button button = getButton(datas.get(i), i);
                if (!boolTitle && i == 0) {
                    button.setBackgroundResource(R.drawable
                            .selector_widget_actiondialog_top);
                } else {
                    if (i != datas.size() - 1)
                        button.setBackgroundResource(R.drawable
                                .selector_widget_actiondialog_middle);
                    else
                        button.setBackgroundResource(R.drawable
                                .selector_widget_actiondialog_bottom);
                }
                linearLayout.addView(button);
            }
        }
    }

    private Button getButton(String text, int position) {

        // 动态生成选择按钮
        final Button button = new Button(context);
        button.setText(text);
        button.setTag(position);
        button.setTextColor(itemTextColor);
        button.setTextSize(itemTextSize);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, itemHeight));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (onItemListener != null) {

                    selectPosition = Integer.parseInt(button.getTag().toString());
                    onItemListener.onItemClick(button, selectPosition);

                }
            }
        });

        return button;
    }

    public DialogActionBottom setDataList(ArrayList<String> datas) {

        int count = linearLayout.getChildCount();
        if (count > 1) {
            linearLayout.removeViewsInLayout(1, count - 1);
        }

        isShowed = false;
        this.datas = (datas == null ? new ArrayList<String>() : datas);
        return this;
    }

//    public DialogActionBottom setDataList(int flag, ArrayList<TypeBean> datas) {
//
//        isShowed = false;
//        ArrayList<String> data = new ArrayList<>();
//        for (TypeBean model : datas) {
//            data.add(model.getDictItemValue());
//        }
//
//        this.datas = data;
//
//        return this;
//    }

//    public DialogActionBottom setDataList(ArrayList<VisitCardType> datas, int flag) {
//
//        isShowed = false;
//        ArrayList<String> data = new ArrayList<>();
//        for (VisitCardType model : datas) {
//            data.add(model.getCardName());
//        }
//
//        this.datas = data;
//
//        return this;
//    }

    public DialogActionBottom setTitle(String s) {

        title.setText(s);
        return this;

    }

    public DialogActionBottom setTitleVisible(boolean v) {

        if (v == false) {
            title.setVisibility(View.GONE);
            boolTitle = false;
        } else {
            title.setVisibility(View.VISIBLE);
            boolTitle = true;
        }
        return this;
    }

    public DialogActionBottom setTitleHeight(int dp) {

        LinearLayout.LayoutParams l = (LinearLayout.LayoutParams) title
                .getLayoutParams();
        l.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
        title.setLayoutParams(l);
        return this;
    }

    public DialogActionBottom setTitleColor(int color) {

        title.setTextColor(color);
        return this;
    }

    public DialogActionBottom setTitleTextSize(int px) {

        // title.setTextSize(UiUtils.sp2px(context, size));
        title.setTextSize(px);
        return this;
    }

    public DialogActionBottom setItemTextColor(@ColorRes int color) {

        itemTextColor = context.getResources().getColor(color);
        bottomBtn.setTextColor(itemTextColor);
        return this;
    }

    public DialogActionBottom setItemTextSize(int sp) {

        itemTextSize = sp;
        bottomBtn.setTextSize(itemTextSize);
        return this;
    }

    public DialogActionBottom setItemHeight(int dp) {

        itemHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams l = (LinearLayout.LayoutParams) bottomBtn
                .getLayoutParams();
        l.height = itemHeight;
        bottomBtn.setLayoutParams(l);
        return this;
    }

    public DialogActionBottom setBottomButtonText(String s) {

        bottomBtn.setText(s);
        return this;
    }

    public ArrayList<String> getData() {

        return datas;
    }

    public int getSelectPosition() {

        return this.selectPosition;
    }

    public void show() {

        if (!isShowed) {
            loadItemDatas();
            isShowed = true;
        }
        mDialog.show();

    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public DialogActionBottom setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public interface OnItemListener {

        public void onItemClick(Button button, int position);
    }

    public DialogActionBottom setOnItemListener(OnItemListener onItemListener) {

        this.onItemListener = onItemListener;
        return this;
    }
}
