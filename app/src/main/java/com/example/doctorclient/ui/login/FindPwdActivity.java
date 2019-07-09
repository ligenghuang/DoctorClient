package com.example.doctorclient.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.FindPwdAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.FindPwdView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.FormatUtils;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 找回密码 重设密码
* @author lgh
* created at 2019/5/14 0014 14:17
*/
public class FindPwdActivity extends UserBaseActivity<FindPwdAction> implements FindPwdView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_find_pwd)
    EditText findPwdEt;
    @BindView(R.id.et_find_pwd_again)
    EditText findPwdAgainEt;
    @BindView(R.id.et_find_pwd_code)
    EditText codeEt;
    @BindView(R.id.tv_find_pwd_code)
    TextView codeTv;
    @BindView(R.id.tv_find_pwd_submit)
    TextView submitTv;

    String userName;
    String imgCode;

    MyCountDownTimer timer;

    @Override
    public int intiLayout() {
        return R.layout.activity_find_pwd;
    }

    @Override
    protected FindPwdAction initAction() {
        return new FindPwdAction(this,this);
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
                .addTag("FindPwdActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.find_pwd_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        timer = new MyCountDownTimer(60000,1000);

        userName = getIntent().getStringExtra("userName");
        imgCode = getIntent().getStringExtra("imgCode");

        submitTv.setEnabled(false);
        setEditTexts(findPwdEt);
        setEditTexts(findPwdAgainEt);
        setEditTexts(codeEt);
    }


    /**
     * 判断是否可进行修改
     * @param editText
     */
    private void setEditTexts(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submitTv.setEnabled(!(TextUtils.isEmpty(findPwdEt.getText().toString()) || TextUtils.isEmpty(codeEt.getText().toString())||TextUtils.isEmpty(findPwdAgainEt.getText().toString())));
                submitTv.setSelected(!(TextUtils.isEmpty(findPwdEt.getText().toString()) || TextUtils.isEmpty(codeEt.getText().toString())||TextUtils.isEmpty(findPwdAgainEt.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_find_pwd_code,R.id.tv_find_pwd_submit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_find_pwd_code:
                //todo 获取验证码
                getLoginCode();
                break;
            case R.id.tv_find_pwd_submit:
                //todo 修改密码
                submit();
                break;
        }
    }

    /**
     * 修改密码
     */
    private void submit() {
        if (TextUtils.isEmpty(findPwdEt.getText().toString())){
            //todo 新密码 判空
            showNormalToast(ResUtil.getString(R.string.find_pwd_tip_7));
            return;
        }
        String pwd = findPwdEt.getText().toString();

        if (TextUtils.isEmpty(findPwdAgainEt.getText().toString())){
            //todo 再次输入新密码  判空
            showNormalToast(ResUtil.getString(R.string.find_pwd_tip_13));
            return;
        }
        String againPwd = findPwdAgainEt.getText().toString();

        if (!pwd.equals(againPwd)){
            //todo 两次密码不一致
            showNormalToast(ResUtil.getString(R.string.find_pwd_tip_13));
            return;
        }

        if (TextUtils.isEmpty(codeEt.getText().toString())){
            //todo 验证码 判空
            showNormalToast(ResUtil.getString(R.string.find_pwd_tip_9));
            return;
        }

        String smsCode = codeEt.getText().toString();

        findPwd(userName,pwd,againPwd,smsCode);
    }

    /**
     * 获取登录验证码
     */
    @Override
    public void getLoginCode() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.getCode(userName,imgCode);
        }
    }

    /**
     * 获取登录验证码成功
     * @param generalDto
     */
    @Override
    public void getLoginCodeSuccessful(GeneralDto generalDto) {
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
     * 修改密码
     * @param username
     * @param pwd
     * @param againPwd
     * @param smsCode
     */
    @Override
    public void findPwd(String username,String pwd,String againPwd,String smsCode) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.findPwd(username,pwd,againPwd,smsCode);
        }
    }

    /**
     * 修改密码 成功
     * @param generalDto
     */
    @Override
    public void findPwdSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        if (generalDto.getCode() == 1){
            ActivityStack.getInstance().exitIsNotHaveMain(LoginActivity.class, MainActivity.class);
            finish();
        }
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
            codeTv.setEnabled(false);
            codeTv.setSelected(true);
            codeTv.setText(FormatUtils.format(getString(R.string.find_pwd_tip_12), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            codeTv.setEnabled(true);
            codeTv.setSelected(false);
            codeTv.setText(R.string.registered_tip_14);
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


    @Override
    public void onError(String message, int code) {

    }

    @Override
    public void onLigonError() {

    }

    @Override
    public void getCode() {

    }

    @Override
    public void getCodeSuccessful(GeneralDto codeDto) {

    }

    @Override
    public void RetrievePws(String username, String imgCode) {

    }

    @Override
    public void RetrievePwsSuccessful(GeneralDto generalDto) {

    }


}
