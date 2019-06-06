package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.DrugClassListDto;
import com.example.doctorclient.event.DrugDetailsDto;
import com.example.doctorclient.event.DrugListDto;
import com.lgh.huanglib.util.base.BaseView;

/**
 * description:选择药品
 * autour: huang
 * date: 2019/5/20 11:43
 * update: 2019/5/20
 * version:
 */
public interface SelectDrugsView extends BaseView {

    void getDrugClass();

    void getDrugClassSuccessful(DrugClassListDto drugClassListDto);

    void getDrugByClass(String drugClass);

    void getDrugByClassSuccessful(DrugListDto drugListDto);

    void getDrugByIuid(String iuid);

    void getDrugByIuidSuccessful(DrugDetailsDto drugDetailsDto);
}
