package com.example.doctorclient.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BingPhoneAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.WeiLoginDto;
import com.example.doctorclient.event.post.WeiXinLoginPost;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.BingPhoneView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.data.MySp;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.FormatUtils;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 微信登录  绑定手机号
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/9
 */
public class BingPhoneActivity extends UserBaseActivity<BingPhoneAction> implements BingPhoneView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.et_invit)
    EditText mEtInvit;

    //获取验证码倒计时
    private MyCountDownTimer timer;

    String unionid = "";

    @Override
    public int intiLayout() {
        return R.layout.activity_bing_phone;
    }

    @Override
    protected BingPhoneAction initAction() {
        return new BingPhoneAction(this, this);
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
                .addTag("BingPhoneActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.bing_phone_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        unionid = getIntent().getStringExtra("unionid");

        timer = new MyCountDownTimer(60000, 1000);
    }


    @OnClick({R.id.tv_get_code, R.id.tv_login, R.id.tv_find_pwd_next})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_code:
                //todo 获取验证码
                if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
                    //todo 医生手机号判空
                    showNormalToast(ResUtil.getString(R.string.registered_tip_2));
                    return;
                }
                weiXinChecks(mEtPhone.getText().toString());
                break;
            case R.id.tv_login:
                //TODO 登录
                finish();
                break;
            case R.id.tv_find_pwd_next:
                //TODO 提交数据
                submit();
                break;
        }
    }

    private void submit() {
        WeiXinLoginPost post = new WeiXinLoginPost();

        if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
            //todo 医生手机号判空
            showNormalToast(ResUtil.getString(R.string.registered_tip_2));
            return;
        }
        post.setUserName(mEtPhone.getText().toString());

        if (TextUtils.isEmpty(mEtCode.getText().toString())) {
            //todo 判空验证码
            showNormalToast(ResUtil.getString(R.string.registered_tip_3));
            return;
        }
        post.setSms_code(mEtCode.getText().toString());

        post.setInvitName(mEtInvit.getText().toString());
        post.setUnionid(unionid);

        //todo 绑定
        weiXinLogin(post);
    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    @Override
    public void weiXinChecks(String phone) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.weiXinChecks(phone);
        }
    }

    /**
     * 获取验证码 成功
     */
    @Override
    public void weiXinChecksSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        if (generalDto.getCode() == 1){
            if (timer != null) {
                timer.cancel();
            }
            timer.start();
        }
    }

    /**
     * 绑定手机
     *
     * @param weiXinLoginPost
     */
    @Override
    public void weiXinLogin(WeiXinLoginPost weiXinLoginPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.weiXinLogin(weiXinLoginPost);
        }
    }

    /**
     * 绑定手机 成功
     */
    @Override
    public void weiXinLoginSuccessful(WeiLoginDto generalDto) {
        loadDiss();
        MySp.setToken(mContext, generalDto.getData().getIuid());
        MySp.setRoogUserId(mContext, generalDto.getData().getIuid());
        MySp.setRoogUserImg(mContext,generalDto.getData().getNiceImg());
        MySp.setRoogUserName(mContext,generalDto.getData().getNicename());
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
        finish();
    }

    /**
     * 失败
     *
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {

    }

    /**************************************计时器 start*******************************************/
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            mTvGetCode.setEnabled(false);
            mTvGetCode.setSelected(true);
            mTvGetCode.setText(FormatUtils.format(getString(R.string.registered_tip_13), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            mTvGetCode.setEnabled(true);
            mTvGetCode.setSelected(false);
            mTvGetCode.setText(R.string.registered_tip_14);
        }
    }
/*****************************************计时器 end**************************************************/
@Override
public void onResume() {
    super.onResume();
    if (baseAction != null) {
        baseAction.toRegister();
    }
}

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }
}
