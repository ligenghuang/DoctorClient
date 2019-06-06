package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.AddPrescribePost;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description:
 * autour: huang
 * date: 2019/5/27 16:27
 * update: 2019/5/27
 * version:
 */
public interface SelectPrescriptionDetailsView extends BaseView {

    void getDrugSaveHeadByIuid();
    void getDrugSaveHeadByIuidSuccessful(PrescriptionDrugInfoDto prescriptionDrugInfoDto);

    void getDrugSaveDetailByIuid();
    void getDrugSaveDetailByIuidSuccessful(PrescriptionDrugListDto prescriptionDrugListDto);

    void updataFileName(String str);
    void updataFileNameSuccessful(String str);

    void AddPrescribe(AddPrescribePost prescribePost);
    void AddPrescribeSuccessful(GeneralDto generalDto);
}
