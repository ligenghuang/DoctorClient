package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.RegisteredCodeDto;
import com.example.doctorclient.event.post.RegisteredPost;
import com.lgh.huanglib.util.base.BaseView;

/**
* 注册
* @author lgh
* created at 2019/5/14 0014 10:24
*/
public interface RegisteredView extends BaseView {

    /**
     * 注册
     */
    void registered(RegisteredPost registeredPost);

    /**
     * 注册成功
     */
    void registeredSuccessful(GeneralDto generalDto);


    /**
     * 获取验证码
     */
    void registeredCode(String userName);


    /**
     * 获取验证码成功
     */
    void CodeSuccessful(RegisteredCodeDto registeredCodeDto);

}
