package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.lgh.huanglib.util.base.BaseView;

/**
* 找回密码
* @author lgh
* created at 2019/5/14 0014 11:52
*/
public interface FindPwdView extends BaseView {

    /**
     * 获取图片验证码
     */
    void getCode();

    /**
     * 获取图片验证码成功
     * @param codeDto
     */
    void getCodeSuccessful(GeneralDto codeDto);

    /**
     * 校验身份
     * @param username
     * @param imgCode
     */
    void RetrievePws(String username,String imgCode);

    /**
     * 校验身份 成功
     * @param generalDto
     */
    void RetrievePwsSuccessful(GeneralDto generalDto);

    /**
     * 获取登录验证码
     */
    void getLoginCode();

    /**
     * 获取登录验证码 成功
     * @param generalDto
     */
    void getLoginCodeSuccessful(GeneralDto generalDto);

    /**
     * 找回密码
     * @param username
     * @param pwd
     * @param againPwd
     * @param smsCode
     */
    void findPwd(String username,String pwd,String againPwd,String smsCode);

    /**
     * 找回密码成功
     */
    void findPwdSuccessful(GeneralDto generalDto);

}
