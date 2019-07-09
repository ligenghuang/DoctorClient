package com.example.doctorclient.util.base;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.actions.BaseAction;
import com.lgh.huanglib.util.base.BaseActivity;
import com.lgh.huanglib.util.cusview.Wave;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;


/**
 * @author lgh
 * created at 2019/5/13 0013 14:30
 */
public abstract class UserBaseActivity<P extends BaseAction> extends BaseActivity {

    protected P baseAction;

    protected abstract P initAction();


    protected RecyclerView baseRecyclerView;
    protected LinearLayout baseLlNodata;
    protected Wave baseWaveLoading;
    protected ImageView baseIvPlaceholderImage;
    protected TextView baseTvPlaceholderTip;
    protected SmartRefreshLayout baseSmartRefreshLayout;
    View[] viewLis = new View[5];

    protected void binding() {
        setContentView(intiLayout());
        ButterKnife.bind(this);
        baseAction = initAction();

        initTitlebar();
        init();
        initView();
    }


    @Override
    public void finish() {
        super.finish();
        hideInput();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (baseAction != null) {
            baseAction.unregister();
            baseAction.dettachView();
        }

    }


    /**
     * 如果想使用 加载状态的控件 先初始化这个方法
     */
    protected void initStateView(RecyclerView recyclerView,
                                 LinearLayout llNodata,
                                 Wave waveLoading,
                                 ImageView ivPlaceholderImage,
                                 TextView tvPlaceholderTip) {
        this.baseRecyclerView = recyclerView;
        this.baseLlNodata = llNodata;
        this.baseWaveLoading = waveLoading;
        this.baseIvPlaceholderImage = ivPlaceholderImage;
        this.baseTvPlaceholderTip = tvPlaceholderTip;
        viewLis[0] = baseRecyclerView;
        viewLis[1] = baseLlNodata;
        viewLis[2] = baseWaveLoading;
        viewLis[3] = baseIvPlaceholderImage;
        viewLis[4] = baseTvPlaceholderTip;
    }


    /**
     * 如果想使用 加载状态的控件 先初始化这个方法
     */
    protected void initStateView(SmartRefreshLayout smartRefreshLayout,
                                 LinearLayout llNodata,
                                 Wave waveLoading,
                                 ImageView ivPlaceholderImage,
                                 TextView tvPlaceholderTip) {
        this.baseSmartRefreshLayout = smartRefreshLayout;
        this.baseLlNodata = llNodata;
        this.baseWaveLoading = waveLoading;
        this.baseIvPlaceholderImage = ivPlaceholderImage;
        this.baseTvPlaceholderTip = tvPlaceholderTip;
        viewLis[0] = baseSmartRefreshLayout;
        viewLis[1] = baseLlNodata;
        viewLis[2] = baseWaveLoading;
        viewLis[3] = baseIvPlaceholderImage;
        viewLis[4] = baseTvPlaceholderTip;
    }

    /**
     * 解决EditText与NestedScrollView 滚动冲突问题
     * @param editText
     */
    public void setEditText(EditText editText){
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //canScrollVertically()方法为判断指定方向上是否可以滚动,参数为正数或负数,负数检查向上是否可以滚动,正数为检查向下是否可以滚动
                if (editText.canScrollVertically(1) || editText.canScrollVertically(-1)){
                    v.getParent().requestDisallowInterceptTouchEvent(true);//requestDisallowInterceptTouchEvent();要求父类布局不在拦截触摸事件
                    if (event.getAction() == MotionEvent.ACTION_UP){ //判断是否松开
                        v.getParent().requestDisallowInterceptTouchEvent(false); //requestDisallowInterceptTouchEvent();让父类布局继续拦截触摸事件
                    }
                }
                return false;
            }
        });
    }


}
