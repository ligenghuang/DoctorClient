package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.MessageListDto;
import com.lgh.huanglib.util.base.BaseView;

public interface MessageView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getMessageList();

    void getMessageListSuccessful(MessageListDto messageListDto);

    void getMessage(MessageDto messageDto);
}
