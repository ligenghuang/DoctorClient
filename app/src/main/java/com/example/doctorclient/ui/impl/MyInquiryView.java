package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.MyInquiryDto;
import com.lgh.huanglib.util.base.BaseView;

/**
* 我的问诊单
* @author lgh
* created at 2019/5/17 0017 16:18
*/
public interface MyInquiryView extends BaseView {

    void getAskHead();

    void getAskHeadSuccessful(MyInquiryDto myInquiryDto);

    void Confirmation(String iuid);

    void ConfirmationSuccessful(GeneralDto generalDt);
}
