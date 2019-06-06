package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.CommonLanguageListDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.MessageDetailInquiryDto;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.SendMessageDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description:消息详情
 * autour: huang
 * date: 2019/5/23 13:36
 * update: 2019/5/23
 * version:
 */
public interface MessageDetailView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getAskHeadByUserId();

    void getAskHeadByUserIdSuccessful(MessageDetailInquiryDto inquiryDetailDto);

    void getAskChat();

    void getAskChatMore();

    void getAskChatSuccessful(MessageDetailListDto messageDetailListDto);

    void sendMessage(String chat_note);

    void sendMessageSuccessful(SendMessageDto sendMessageDto);
    void sendPicturesaSuccessful(SendMessageDto sendMessageDto);

    void endSession();
    void endSessionSuccessful(GeneralDto generalDto);

    void getCommonLanguage();
    void getCommonLanguageSuccessful(CommonLanguageListDto commonLanguageListDto);

    void sendCommonLanguage(String txt);
    void sendCommonLanguageSuccessful(GeneralDto generalDto);

    void deleteCommonLanguage(String iuid);
    void deleteCommonLanguageSuccessful(GeneralDto generalDto);

    void getMessage(MessageDto messageDto);
}
