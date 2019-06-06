package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DrugDto;
import com.example.doctorclient.event.PrescriptionDrugsDto;
import com.lgh.huanglib.util.base.BaseView;
/**
* 选择处方
* @author lgh
* created at 2019/5/18 0018 16:14
*/
public interface SelectPrescriptionView extends BaseView {

    void getDepartList();

    void getDepartListSuccessful(DepartidDto departidDto);

    void getDrugsaveHeadByDepId(String id);

    void getDrugsaveHeadByDepIdSuccessful(PrescriptionDrugsDto prescriptionDrugsDto);
}
