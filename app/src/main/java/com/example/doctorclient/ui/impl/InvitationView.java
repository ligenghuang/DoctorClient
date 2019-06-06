package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.InviteCodeDto;
import com.lgh.huanglib.util.base.BaseView;
/**
 * description: 邀请用户
 * autour: huang
 * date: 2019/5/30 19:13
 * update: 2019/5/30
 * version:
 */
public interface InvitationView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getInviteCode();
    void getInviteCodeSuccessful(InviteCodeDto inviteCodeDto);
}
