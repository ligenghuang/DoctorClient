package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description:系统设置
 * autour: huang
 * date: 2019/5/22 13:56
 * update: 2019/5/22
 * version:
 */
public interface SettingView extends BaseView {

    void logout();
    void logoutSuccessful(String str);
}
