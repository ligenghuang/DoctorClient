package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.lgh.huanglib.util.base.BaseView;

/**
* 处方
* @author lgh
* created at 2019/5/18 0018 14:04
*/
public interface PrescriptionView extends BaseView {

    void getAskHeadById();

    void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto);
    void updateDiagnosis(String iuid,String diagonsis);
    void updateDiagnosisSuccessful(GeneralDto generalDto);

}
