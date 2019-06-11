package com.example.doctorclient.ui.mine.income;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.WithdrawalAction;
import com.example.doctorclient.ui.impl.WithdrawalView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.dialog.UpdateFactPriceDialog;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现（未实现）
 *
 * @author lgh
 * created at 2019/5/17 0017 14:23
 */
public class WithdrawalActivity extends UserBaseActivity<WithdrawalAction> implements WithdrawalView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_income_amount)
    TextView incomeAmountTv;
    @BindView(R.id.tv_withdrawal_amount)
    TextView withdrawalAmountTv;

    @BindView(R.id.rb_wechat)
    RadioButton weChatRb;
    @BindView(R.id.rb_ali)
    RadioButton aliRb;

    double incomeAmount = 0;
    int type = 0;

    @Override
    public int intiLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected WithdrawalAction initAction() {
        return new WithdrawalAction(this, this);
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
                .addTag("WithdrawalActivity")  //给上面参数打标记，以后可以通过标记恢复
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
        incomeAmount = getIntent().getDoubleExtra("incomeAmount", 0);
        incomeAmountTv.setText("￥" + incomeAmount);
        weChatRb.setChecked(true);
        aliRb.setChecked(false);

    }

    @OnClick({R.id.tv_withdrawal_amount,R.id.ll_wechat,R.id.ll_ali,R.id.rb_wechat,R.id.rb_ali})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_withdrawal_amount:
                //todo 输入提现金额
                UpdateFactPriceDialog modifyDialog = new UpdateFactPriceDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.withdrawal_tip_10));
                modifyDialog.setOnClickListener(new UpdateFactPriceDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showToast(ResUtil.getString(R.string.withdrawal_tip_10));
                            return;
                        } else {
                            withdrawalAmountTv.setText("￥" + txet);
                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.ll_wechat:
            case R.id.rb_wechat:
                //TODO 提现方式 微信
                type = 0;
                weChatRb.setChecked(true);
                aliRb.setChecked(false);
                break;
            case R.id.ll_ali:
            case R.id.rb_ali:
                //TODO 提现方式 支付宝
                type =1;
                weChatRb.setChecked(false);
                aliRb.setChecked(true);
                break;
        }
    }

    @Override
    public void onError(String message, int code) {

    }

    @Override
    public void onLigonError() {

    }
}
