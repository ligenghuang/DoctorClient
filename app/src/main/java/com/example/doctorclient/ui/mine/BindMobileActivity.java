package com.example.doctorclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BindMobileAction;
import com.example.doctorclient.ui.impl.BindMobileView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 绑定手机(未使用)
* @author lgh
* created at 2019/5/17 0017 10:40
*/
public class BindMobileActivity extends UserBaseActivity<BindMobileAction> implements BindMobileView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_mobile)
    EditText mobileEt;


    @Override
    protected BindMobileAction initAction() {
        return new BindMobileAction(this,this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_bind_mobile;
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
                .statusBarDarkFont(true, 0.2f)
                .addTag("BindMobileActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.user_info_tip_23));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        mobileEt.setText(getIntent().getStringExtra("mobile"));
    }

    @OnClick(R.id.tv_bind)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_bind:
                finish();
                break;
        }
    }

    @Override
    public void onLigonError() {
        jumpActivity(mContext, LoginActivity.class);
    }
    @Override
    public void onError(String message, int code) {

    }
}
