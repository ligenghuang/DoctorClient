package com.example.doctorclient.util.picker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.doctorclient.R;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.IsFastClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimePickerBuilder {
    TimePickerView pvTime;
    OptionsPickerView pvWeek;
    Context context;

    public TimePickerBuilder(Context context) {
        this.context = context;
    }



    /**
     * 用于初始化选择器
     *
     * @return
     */
    public OptionsPickerView setSexPicker( List<String> sexLists,String title,final SexPickerCustomListener weekPickerCustomListener) {
        pvWeek = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                IsFastClick.lastClickTime = 0;
                weekPickerCustomListener.sexSelect(sexLists.get(options1));
                L.e("lgh", "week  == " + sexLists.get(options1));
            }
        })

                .setCancelColor(Color.BLACK)// TODO: 2018/11/5 取消的颜色
                .setSubmitColor(Color.BLACK)// TODO: 2018/11/5 确认的颜色
                .setTitleText(title)
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvWeek.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;

            pvWeek.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.cus_picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setBackgroundDrawable(null);
                dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.horizontalMargin = 0;
                dialogWindow.setAttributes(layoutParams);
            }
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    IsFastClick.lastClickTime = 0;
                }
            });

        }
//        List<List<String>> lists = new ArrayList<>();
//        lists.add(weekLists);
        pvWeek.setPicker(sexLists);
        return pvWeek;
    }


    /**
     * 显示时分
     *
     * @param timePickerCustomListener
     * @return
     */
    public TimePickerView TimePickerBuilder(TimePickerCustomListener timePickerCustomListener) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar date = Calendar.getInstance();
        date.set(1900,1,1);
        Calendar enddDate = Calendar.getInstance();
        enddDate.set(2042, 12, 31, 23, 59);
        pvTime = new BaseTimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                IsFastClick.lastClickTime = 0;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/:MM");
                String time = format.format(date);
                timePickerCustomListener.customLayout(date);
//
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setDate(selectedDate)
                .setRangDate(date, enddDate)
                .setCancelColor(Color.BLACK)// TODO: 2018/11/5 取消的颜色
                .setSubmitColor(Color.BLACK)// TODO: 2018/11/5 确认的颜色
                .setDividerColor(Color.BLACK)
                .setType(new boolean[]{true, true, false, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.cus_picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    IsFastClick.lastClickTime = 0;
                }
            });
        }
        return pvTime;
    }

    /**
     * 显示年月日
     *
     * @param timePickerCustomListener
     * @return
     */
    public TimePickerView TimePickerBuilder3(TimePickerCustomListener timePickerCustomListener) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar enddDate = Calendar.getInstance();
        enddDate.set(2042, 12, 31, 23, 59);
        pvTime = new BaseTimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy:mm:dd HH:mm");
                String time = format.format(date);
                timePickerCustomListener.customLayout(date);
//
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setDate(selectedDate)
                .setRangDate(selectedDate, enddDate)
                .setCancelColor(Color.BLACK)// TODO: 2018/11/5 取消的颜色
                .setSubmitColor(Color.BLACK)// TODO: 2018/11/5 确认的颜色
                .setDividerColor(Color.BLACK)
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.cus_picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        return pvTime;
    }

    public interface TimePickerCustomListener {
        void customLayout(Date date);
    }

    public interface SexPickerCustomListener {
        void sexSelect(String sexStr);
    }
}
