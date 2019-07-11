package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.WeiLoginDto;
import com.example.doctorclient.event.post.WeiXinLoginPost;
import com.lgh.huanglib.util.base.BaseView;

/**
* description ： 绑定手机
* author : lgh
* email : 1045105946@qq.com
* date : 2019/7/10
*/
public interface BingPhoneView extends BaseView {

    void weiXinChecks(String phone);

    void weiXinChecksSuccessful(GeneralDto generalDto);

    void weiXinLogin(WeiXinLoginPost weiXinLoginPost);

    void weiXinLoginSuccessful(WeiLoginDto generalDto);
}
