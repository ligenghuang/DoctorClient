package com.example.doctorclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.data.GetJsonDataUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
/**
* description ： 服务条款
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/25
*/
public class ServiceAgreementActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.webview)
    WebView webView;

    @Override
    public int intiLayout() {
        return R.layout.activity_service_agreement;
    }

    @Override
    protected BaseAction initAction() {
        return null;
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
                .addTag("ServiceAgreementActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true, 0.2f)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.setting_tip_6));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

    }


}
