package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.IncomeDetailsListDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description: 收入明细
 * autour: huang
 * date: 2019/5/30 11:33
 * update: 2019/5/30
 * version:
 */
public interface IncomeDetailsView extends BaseView {

    void getIncomeDetailsList();

    void getIncomeDetailsSuccessful(IncomeDetailsListDto incomeDetailsListDto);
}
