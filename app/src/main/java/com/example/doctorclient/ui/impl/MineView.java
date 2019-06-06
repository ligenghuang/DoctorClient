package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.UserInfoDto;
import com.lgh.huanglib.util.base.BaseView;
/**
* 我的
* @author lgh
* created at 2019/5/15 0015 16:02
*/
public interface MineView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getUserInfo();

    void getUserInfoSuccessful(UserInfoDto userInfoDto);

    void updateFactPrice(String num);

    void updateFactPriceSuccessful(GeneralDto generalDto);
}
