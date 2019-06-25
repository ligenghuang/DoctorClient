package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.PrescriptionListDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description: 拍照开方
 * autour: huang
 * date: 2019/5/28 10:36
 * update: 2019/5/28
 * version:
 */
public interface PhotographPrescriptionView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void getAskHeadById();

    void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto);

    void getPrescriptionList();

    void getPrescriptionListSuccessful(PrescriptionListDto prescriptionListDto);

    void updataFileName(String str);
    void updataFileNameSuccessful(String str);

    void savePhotographPrescription(String iuid,String diagonsis,String theImg);

    void savePhotographPrescriptionSuccessful(GeneralDto generalDto);


}
