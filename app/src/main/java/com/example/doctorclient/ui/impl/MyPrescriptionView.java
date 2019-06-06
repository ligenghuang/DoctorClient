package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.MyPrescriptionDto;
import com.lgh.huanglib.util.base.BaseView;

/**
 * 我的处方
 *
 * @author lgh
 * created at 2019/5/18 0018 10:20
 */
public interface MyPrescriptionView extends BaseView {

    void getPrescription();

    void getPrescriptionSuccessful(MyPrescriptionDto myPrescriptionDto);
}
