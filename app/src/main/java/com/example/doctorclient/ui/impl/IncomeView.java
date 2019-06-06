package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.DoctorInfoDto;
import com.lgh.huanglib.util.base.BaseView;
/**
* 我的收入
* @author lgh
* created at 2019/5/17 0017 13:50
*/
public interface IncomeView extends BaseView {

    void getDocIncome();

    void getDocIncomeSuccessful(DoctorInfoDto doctorInfoDto);

}
