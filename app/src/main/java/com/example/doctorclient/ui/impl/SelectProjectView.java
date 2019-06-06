package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.DepartidDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description:选择项目
 * autour: huang
 * date: 2019/5/21 10:34
 * update: 2019/5/21
 * version:
 */
public interface SelectProjectView extends BaseView {

    void getDepartVip1();

    void getDepartVip1Successful(DepartidDto generalDto);

    void getDepartVip2(String iuid);

    void getDepartVip2Successful( DepartidDto generalDto);
}
