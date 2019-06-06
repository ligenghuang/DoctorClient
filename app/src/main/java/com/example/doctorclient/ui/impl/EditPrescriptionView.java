package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.post.DrugSavePost;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description: 编辑处方
 * autour: huang
 * date: 2019/5/21 9:18
 * update: 2019/5/21
 * version:
 */
public interface EditPrescriptionView extends BaseView {

    void updateDrugSave(DrugSavePost drugSavePost);

    void updateDrugSaveSuccessful(GeneralDto generalDto);

}
