package com.example.doctorclient.ui.login;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.doctorclient.ui.impl.FindPwdView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.AuthCodeView;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 找回密码
* @author lgh
* created at 2019/5/14 0014 11:33
*/
public class FindPwdCheckActivity extends UserBaseActivity<FindPwdAction> implements FindPwdView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.codeView)
    AuthCodeView codeView;
    @BindView(R.id.et_find_pwd_account)
    EditText accountEt;
    @BindView(R.id.et_find_pwd_code)
    EditText codeEt;
    @BindView(R.id.tv_find_pwd_next)
    TextView nextTv;

    String authCode = "";

    @Override
    public int intiLayout() {
        return R.layout.activity_find_pwd_check;
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
                .addTag("FindPwdCheckActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.find_pwd_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        getCode();
        nextTv.setEnabled(false);
       setEditTexts(accountEt);
       setEditTexts(codeEt);
    }

    /**
     * 判断是否可进入下一步
     * @param editText
     */
    private void setEditTexts(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nextTv.setEnabled(!(TextUtils.isEmpty(accountEt.getText().toString()) || TextUtils.isEmpty(codeEt.getText().toString())));
                nextTv.setSelected(!(TextUtils.isEmpty(accountEt.getText().toString()) || TextUtils.isEmpty(codeEt.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_find_pwd_next,R.id.codeView})
     void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_find_pwd_next:
                //todo 下一步
                RetrievePws(accountEt.getText().toString(),codeEt.getText().toString());
//                Intent intent = new Intent(this,FindPwdActivity.class);
//                intent.putExtra("userName",accountEt.getText().toString());
//                intent.putExtra("imgCode",codeEt.getText().toString());
//                startActivity(intent);
                break;
            case R.id.codeView:
                //todo 获取验证码
                getCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    @Override
    public void getCode() {
        if (CheckNetwork.checkNetwork2(this)){
            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.getCode();
        }
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void getCodeSuccessful(GeneralDto codeDto) {
        loadDiss();
        authCode = codeDto.getData();
        codeView.refresh(codeDto.getData());
    }

    /**
     * 校验身份
     * @param username
     * @param imgCode
     */
    @Override
    public void RetrievePws(String username, String imgCode) {
        if (CheckNetwork.checkNetwork2(this)){
            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.RetrievePws(username,imgCode);
        }
    }

    /**
     * 校验身份成功
     * @param generalDto
     */
    @Override
    public void RetrievePwsSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1){
            Intent intent = new Intent(this,FindPwdActivity.class);
            intent.putExtra("userName",accountEt.getText().toString());
            intent.putExtra("imgCode",codeEt.getText().toString());
            startActivity(intent);
        }else {
            //todo 校验失败
            showNormalToast(generalDto.getMsg());
            getCode();
        }
    }

    @Override
    public void getLoginCode() {

    }

    @Override
    public void getLoginCodeSuccessful(GeneralDto generalDto) {

    }

    @Override
    public void findPwd(String username,String pwd,String againPwd,String smsCode) {

    }

    @Override
    public void findPwdSuccessful(GeneralDto generalDto) {
    }

    /**
     * 失败
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
