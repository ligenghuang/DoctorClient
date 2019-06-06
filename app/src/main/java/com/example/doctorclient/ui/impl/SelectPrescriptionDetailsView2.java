package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PreInfoDto;
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
public interface SelectPrescriptionDetailsView2 extends BaseView {

    void getPreInfo();
    void getPreInfoSuccessful(PreInfoDto preInfoDto);

    void updataFileName(String str);
    void updataFileNameSuccessful(String str);

    void AddPrescribe(AddPrescribePost prescribePost);
    void AddPrescribeSuccessful(GeneralDto generalDto);
}
