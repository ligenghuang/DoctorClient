package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.DrugSavePost;
import com.lgh.huanglib.util.base.BaseView;

/**
 * description: 编辑处方
 * autour: huang
 * date: 2019/5/21 9:18
 * update: 2019/5/21
 * version:
 */
public interface EditPrescriptionView2 extends BaseView {

    void getDrugSaveHeadByIuid();
    void getDrugSaveHeadByIuidSuccessful(PrescriptionDrugInfoDto prescriptionDrugInfoDto);

    void getDrugSaveDetailByIuid();
    void getDrugSaveDetailByIuidSuccessful(PrescriptionDrugListDto prescriptionDrugListDto);

    void updateDrugSave(DrugSavePost drugSavePost);

    void updateDrugSaveSuccessful(GeneralDto generalDto);

}
