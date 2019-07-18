package com.example.doctorclient.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.BankDto;
import com.example.doctorclient.event.post.BindBankPost;
import com.example.doctorclient.util.Utilt;
import com.example.doctorclient.util.cusview.BankCardTextWatcher;
import com.hjq.toast.ToastUtils;
import com.lgh.huanglib.util.data.ResUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description ：绑定银行卡
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/17
 */
public class BindBankDialog extends Dialog {

    Context context;

    @BindView(R.id.et_name)
    EditText nameEt;
    @BindView(R.id.et_bank)
    EditText bankEt;
    @BindView(R.id.et_bank_number)
    EditText bankNumberEt;
    @BindView(R.id.et_bank_subbranch)
    EditText bankSubbranchEt;

    OnClickListener onClickListener;

    boolean isBindBank;
    BankDto.DataBean dataBean;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BindBankDialog(@NonNull Context context, int themeResId, boolean isBindBank, BankDto.DataBean dataBean) {
        super(context, themeResId);
        this.context = context;
        this.dataBean = dataBean;
        this.isBindBank = isBindBank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bank_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        Window win = this.getWindow();
        win.setGravity(Gravity.CENTER);
        initView();
    }

    private void initView() {
       if (isBindBank){
           nameEt.setText(dataBean.getName());
           bankEt.setText(dataBean.getBank());
           bankSubbranchEt.setText(dataBean.getBank_definite());
           bankNumberEt.setText(dataBean.getCardID());
       }
        BankCardTextWatcher.bind(bankNumberEt);
    }


    @Override
    public void dismiss() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        super.dismiss();
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                //todo 提交数据
                confirm();
                break;
        }
    }

    private void confirm() {
        BindBankPost post = new BindBankPost();
        //TODO 持卡人姓名 判空
        if (TextUtils.isEmpty(nameEt.getText().toString())) {
            ToastUtils.getToast().cancel();
            ToastUtils.show(ResUtil.getString(R.string.withdrawal_tip_11));
        }
        post.setName(nameEt.getText().toString());

        //TODO 银行名称 判空
        if (TextUtils.isEmpty(bankEt.getText().toString())) {
            ToastUtils.getToast().cancel();
            ToastUtils.show(ResUtil.getString(R.string.withdrawal_tip_12));
        }
        post.setBank(bankEt.getText().toString());

        //TODO 开户行 判空
        if (TextUtils.isEmpty(bankSubbranchEt.getText().toString())) {
            ToastUtils.getToast().cancel();
            ToastUtils.show(ResUtil.getString(R.string.withdrawal_tip_15));
        }
        post.setBankSubbranch(bankSubbranchEt.getText().toString());

        //TODO 银行卡号 判空
        if (TextUtils.isEmpty(bankNumberEt.getText().toString())) {
            ToastUtils.getToast().cancel();
            ToastUtils.show(ResUtil.getString(R.string.withdrawal_tip_13));
        }
        //TODO  正则判断
        if (!Utilt.checkBankCard(bankNumberEt.getText().toString())){
            ToastUtils.getToast().cancel();
            ToastUtils.show(ResUtil.getString(R.string.withdrawal_tip_18));
        }
        post.setBankNumber(bankNumberEt.getText().toString());

        onClickListener.confirm(post);
    }

    public interface OnClickListener {
        void confirm(BindBankPost post);
    }

}
