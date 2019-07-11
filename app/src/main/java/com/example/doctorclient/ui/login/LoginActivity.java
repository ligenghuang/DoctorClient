package com.example.doctorclient.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.LoginAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.LoginDto;
import com.example.doctorclient.event.WeiLoginDto;
import com.example.doctorclient.ui.impl.LoginView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.wechat.ShareUtil;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页
 *
 * @author lgh
 * created at 2019/5/13 0013 15:05
 */
public class LoginActivity extends UserBaseActivity<LoginAction> implements LoginView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_login_account)
    EditText loginAccountEt;
    @BindView(R.id.et_login_pwd)
    EditText loginPwdEt;

    ShareUtil shareUtil;


    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginAction initAction() {
        return new LoginAction(this, this);
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
                .addTag("LoginActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.login_tip_5));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        shareUtil = new ShareUtil(this);
        shareUtil.register();
    }

    @Override
    protected void initView() {
        super.initView();
        shareUtil.setLoginListener(new ShareUtil.OnLoginResponseListener() {

            @Override
            public void onSuccess(ShareUtil.Response response) {
                //todo 微信登录
                L.e("ShareUtil",  "打印 ..onSuccess.."+response.toString());
//                wxResponse = dto.getUsername();
                if (CheckNetwork.checkNetwork2(mContext)){
                    baseAction.authorizationLogin(response.code);
                }
            }

            @Override
            public void onCancel() {
                L.e("ShareUtil", "打印 ..onCancel..");
                showToast( ResUtil.getString(R.string.app_login_user_tip_9));
                loadDiss();
            }

            @Override
            public void onFail(String message) {
                L.e("ShareUtil",  "打印 ..onFail.." + message);
                showToast( ResUtil.getString(R.string.app_login_user_tip_10));
                loadDiss();
            }
        });


    }

    @OnClick({R.id.tv_login_btn_login,R.id.tv_login_registered,R.id.tv_login_find_pwd,R.id.iv_login_WeChat})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_btn_login:
                //todo 登录
                submit();
                break;
            case R.id.tv_login_registered:
                //todo 注册
                jumpActivityNotFinish(mContext,RegisteredActivity.class);
                break;
            case R.id.tv_login_find_pwd:
                //todo 找回密码
                jumpActivityNotFinish(mContext, FindPwdCheckActivity.class);
                break;
            case R.id.iv_login_WeChat:
                //todo 微信登录
                if (!MyApp.getWxApi().isWXAppInstalled()) {
                    showToast( ResUtil.getString(R.string.wechat_login));
                    return;
                }
                loadDialog(ResUtil.getString(R.string.main_process));
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
//                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权
//                req.state = "yizhitong_userclient_login";
                MyApp.getWxApi().sendReq(req);
                break;
        }
    }

    /**
     * 登录
     */
    private void submit() {
        if (TextUtils.isEmpty(loginAccountEt.getText().toString())) {
            //todo 账号判空
            showNormalToast(ResUtil.getString(R.string.login_tip_1));
            return;
        }
        String accout = loginAccountEt.getText().toString();

        if (TextUtils.isEmpty(loginPwdEt.getText().toString())) {
            //todo 密码判空
            showNormalToast(ResUtil.getString(R.string.login_tip_1));
            return;
        }

        String pwd = loginPwdEt.getText().toString();

        Login(accout, pwd);
    }

    /**
     * 登录
     *
     * @param username
     * @param pwd
     */
    @Override
    public void Login(String username, String pwd) {
        if (CheckNetwork.checkNetwork2(this)) {
            loadDialog(ResUtil.getString(R.string.login_tip_7));
            baseAction.login(username, pwd);
        }
    }

    /**
     * 登录成功
     */
    @Override
    public void LoginSuccessful(LoginDto generalDto) {
        loadDiss();
        if (generalDto.getCode() != 1) {
            showNormalToast(generalDto.getMsg());
            return;
        }
        MySp.setToken(mContext, generalDto.getData().getIuid());
        MySp.setRoogUserId(mContext, generalDto.getData().getIuid());
        MySp.setRoogUserImg(mContext,generalDto.getData().getNiceImg());
        MySp.setRoogUserName(mContext,generalDto.getData().getNicename());
        finish();
    }

    @Override
    public void authorizationSuccessful(WeiLoginDto generalDto) {

        if (generalDto.getCode() != 1) {
            loadDiss();
            showNormalToast(generalDto.getMsg());
            return;
        }
        if (generalDto.getData().getPhome().isEmpty()){
            //TODO 未绑定手机号
            loadDiss();
            Intent intent = new Intent(mContext,BingPhoneActivity.class);
            intent.putExtra("unionid",generalDto.getData().getUnionid());
            startActivity(intent);
        }else {
            //TODO 已绑定手机号
            MySp.setToken(mContext, generalDto.getData().getIuid());
            MySp.setRoogUserId(mContext, generalDto.getData().getIuid());
            MySp.setRoogUserImg(mContext,generalDto.getData().getNiceImg());
            MySp.setRoogUserName(mContext,generalDto.getData().getNicename());
            loadDiss();
            finish();

        }
    }

    /**
     * 登录失败
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
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }
}
