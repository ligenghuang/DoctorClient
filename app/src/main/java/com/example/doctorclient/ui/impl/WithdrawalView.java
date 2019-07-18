package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.BankDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.post.BindBankPost;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description: 提现
 * autour: huang
 * date: 2019/5/29 15:44
 * update: 2019/5/29
 * version:
 */
public interface WithdrawalView extends BaseView {

    void isBindingBankCard();
    void isBindingBankCardSuccessful(BankDto bankDto);
    void isBindingBankCardErron(String message);

    /**
     * 绑定银行卡
     */
    void bindBank(BindBankPost post);
    void bundBankSuccessful(BankDto bankDto);

    /**
     * 提现
     */
    void addMoenyOut();
    void addMoenyOutSuccessful(GeneralDto generalDto);

}
