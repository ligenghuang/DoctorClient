package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.lgh.huanglib.util.base.BaseView;
/**
* 问诊详情
* @author lgh
* created at 2019/5/18 0018 14:04
*/
public interface InquiryDetailsView extends BaseView {

    void getAskHeadById();

    void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto);

    void Confirmation(String iuid);

    void ConfirmationSuccessful(GeneralDto generalDt);
}
