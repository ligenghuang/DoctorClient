package com.example.doctorclient.ui.mine.income;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.IncomeAction;
import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.ui.impl.IncomeView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.inquiry.InquiryDetailsActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 我的收入
* @author lgh
* created at 2019/5/17 0017 13:53
*/
public class IncomeActivity extends UserBaseActivity<IncomeAction> implements IncomeView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_income_amount)
    TextView incomeAmountTv;

    double incomeAmount = 0;

    @Override
    protected IncomeAction initAction() {
        return new IncomeAction(this,this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_income;
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
                .addTag("IncomeActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.mine_tip_2));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        incomeAmountTv.setText("￥"+getIntent().getDoubleExtra("incomeAmount",0));
        loadDialog();
    }

    @OnClick({R.id.rl_withdrawal,R.id.rl_billing_details})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_withdrawal:
                //todo 提现
                Intent intent = new Intent(mContext,WithdrawalActivity.class);
                intent.putExtra("incomeAmount",incomeAmount);
                startActivity(intent);
                break;
            case R.id.rl_billing_details:
                //todo 收入明细
                jumpActivityNotFinish(mContext, IncomeDetailsActivity.class);
                break;
        }
    }

    /**
     * 获取我的收入
     */
    @Override
    public void getDocIncome() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getDocIncome();
        }
    }

    /**
     * 获取我的收入 成功
     * @param doctorInfoDto
     */
    @Override
    public void getDocIncomeSuccessful(DoctorInfoDto doctorInfoDto) {
        loadDiss();
        incomeAmountTv.setText("￥"+doctorInfoDto.getData().getIncome());
        incomeAmount = doctorInfoDto.getData().getIncome();
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

    /**
     * 未登录
     */
    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
        getDocIncome();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }
}
