package com.example.doctorclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.FeedbackAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.ui.impl.FeedbackView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:意见反馈
 * autour: huang
 * date: 2019/5/22 14:04
 * update: 2019/5/22
 * version:
 */
public class FeedbackActivity extends UserBaseActivity<FeedbackAction> implements FeedbackView {


    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_feedback)
    EditText feedbackEt;
    @BindView(R.id.tv_feedback_num)
    TextView feedbackNumTv;

    @BindView(R.id.tv_mobile)
    EditText mobileTv;

    @Override
    public int intiLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected FeedbackAction initAction() {
        return new FeedbackAction(this,this);
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
                .addTag("FeedbackActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.feedback_tip_4));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        feedbackEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(feedbackEt.getText().toString())){
                    feedbackNumTv.setText("0/200");
                }else {
                    feedbackNumTv.setText(feedbackEt.getText().length()+"/200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.tv_submit)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                if (TextUtils.isEmpty(feedbackEt.getText().toString())){
                    showNormalToast(ResUtil.getString(R.string.feedback_tip_1));
                    return;
                }
                getFeedback(feedbackEt.getText().toString(),mobileTv.getText().toString());
                break;
        }
    }

    @Override
    public void getFeedback(String note, String phone) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getFeedback(note,phone);
        }
    }

    @Override
    public void getFeedbackSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1){
            showNormalToast(generalDto.getMsg());
            finish();
        }
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void finish() {
        super.finish();
        hideInput();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null){
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null){
            baseAction.toUnregister();
        }
    }
}
