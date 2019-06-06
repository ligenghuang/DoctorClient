package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.PrescriptionDetailDto;
import com.lgh.huanglib.util.base.BaseView;

/**
 * description:处方详情
 * autour: huang
 * date: 2019/5/22 10:27
 * update: 2019/5/22
 * version:
 */
public interface PrescriptionDetailsView  extends BaseView {

    void getPreInfo(String iuid);

    void getPreInfoSuccessful(PrescriptionDetailDto prescriptionDetailDto);
}
