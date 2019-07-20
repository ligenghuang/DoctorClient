package com.example.doctorclient.ui.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lxj.xpopup.photoview.PhotoView;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * description ： 图片展示
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/20
 */
public class ImageDetailActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;
    @BindView(R.id.photoView)
    PhotoView photoView;

    String avator;

    @Override
    public int intiLayout() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .addTag("ImageDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        avator = getIntent().getStringExtra("avator");
        L.e("lgh_url", avator);
        GlideUtil.setImage(mContext, avator, photoView, R.color.white);

    }


    @Override
    protected BaseAction initAction() {
        return null;
    }
}
