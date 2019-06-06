package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.EvaluationListDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description:我的评价
 * autour: huang
 * date: 2019/5/30 9:40
 * update: 2019/5/30
 * version:
 */
public interface EvaluationView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void  getEvaluationList();

    void getEvaluationListSuccessful(EvaluationListDto evaluationListDto);
}
