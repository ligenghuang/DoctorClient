package com.example.doctorclient.ui.mine.income;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.WithdrawalAction;
import com.example.doctorclient.event.BankDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.post.BindBankPost;
import com.example.doctorclient.ui.impl.WithdrawalView;
import com.example.doctorclient.util.Utilt;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.dialog.BindBankDialog;
import com.example.doctorclient.util.dialog.UpdateFactPriceDialog;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.IsFastClick;
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
    @BindView(R.id.tv_bank_number)
    TextView mTvBankNumber;

    double incomeAmount = 0;
    String Amount = "0";
    int type = 0;

    boolean isBindBank = false;
    BankDto.DataBean dataBean = new BankDto.DataBean();

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
        titleTv.setText(ResUtil.getString(R.string.income_tip_2));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        incomeAmount = getIntent().getDoubleExtra("incomeAmount", 0);
        incomeAmountTv.setText("￥" + incomeAmount);
        isBindingBankCard();
    }

    @OnClick({R.id.ll_withdrawal_amount, R.id.ll_bank,R.id.tv_withdrawal
    })
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_withdrawal_amount:
                //todo 输入提现金额
                UpdateFactPriceDialog modifyDialog = new UpdateFactPriceDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.withdrawal_tip_10));
                modifyDialog.setOnClickListener(new UpdateFactPriceDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showToast(ResUtil.getString(R.string.withdrawal_tip_10));
                            return;
                        } else {
                            //todo 判断小数点是否在第一位
                            if (Utilt.isNumber(txet)) {
                                withdrawalAmountTv.setText("￥0" + txet);
                                Amount = "0"+txet;
                            } else {
                                withdrawalAmountTv.setText("￥" + txet);
                                Amount = txet;
                            }

                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.ll_bank:
                //todo 绑定银行卡
                BindBankDialog bindBankDialog = new BindBankDialog(mContext, R.style.MY_AlertDialog,isBindBank,dataBean);
                bindBankDialog.setOnClickListener(new BindBankDialog.OnClickListener() {
                    @Override
                    public void confirm(BindBankPost post) {
                        //TODO 请求绑定银行接口
                        bindBank(post);
                        bindBankDialog.dismiss();
                    }
                });
                bindBankDialog.show();
                break;
            case R.id.tv_withdrawal:
                withdrawal();
                break;
        }
    }

    /**
     * 提现
     */
    private void withdrawal() {
        if (Amount.equals("0")){
            showNormalToast(ResUtil.getString(R.string.withdrawal_tip_16));
            return;
        }

//        if (Double.parseDouble(Amount) > incomeAmount){
//            showNormalToast(ResUtil.getString(R.string.withdrawal_tip_19));
//            return;
//        }

        if (isBindBank){
            addMoenyOut();
        }else {
            showNormalToast(ResUtil.getString(R.string.withdrawal_tip_17));
        }
    }

    /**
     * 判断是否绑定银行卡
     */
    @Override
    public void isBindingBankCard() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.isBindingBankCard();
        }
    }

    /**
     * 已绑定银行卡
     * @param bankDto
     */
    @Override
    public void isBindingBankCardSuccessful(BankDto bankDto) {
        loadDiss();
        dataBean = bankDto.getData();
        String cardId = Utilt.getHideBankCardNum(bankDto.getData().getCardID());
        mTvBankNumber.setText(cardId+"    "+bankDto.getData().getName());
        isBindBank = true;
    }

    /**
     * 未绑定银行卡
     * @param message
     */
    @Override
    public void isBindingBankCardErron(String message) {
        loadDiss();
        isBindBank = false;
//        showNormalToast(message);
    }

    /**
     * 绑定银行卡
     * @param post
     */
    @Override
    public void bindBank(BindBankPost post) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.bindBank(post);
        }
    }

    /**
     * 绑定银行卡 成功
     * @param bankDto
     */
    @Override
    public void bundBankSuccessful(BankDto bankDto) {
        loadDiss();
        showNormalToast(bankDto.getMsg());
        String cardId = Utilt.getHideBankCardNum(bankDto.getData().getCardID());
        mTvBankNumber.setText(cardId+"    "+bankDto.getData().getName());
        isBindBank = true;
    }

    /**
     * 提现
     */
    @Override
    public void addMoenyOut() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.addMoenyOut(Amount);
        }
    }

    /**
     * 提现成功
     * @param generalDto
     */
    @Override
    public void addMoenyOutSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
       if (generalDto.getCode() == 1){
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }


}
