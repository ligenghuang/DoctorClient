package com.example.doctorclient.event.post;

public class WeiXinLoginPost {
    /**
     * userName--手机号， sms_code,--短信验证码，invitName--邀请码，unionid--微信唯一标识
     */
    private String userName;
    private String sms_code;
    private String invitName;
    private String unionid;

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSms_code() {
        return sms_code == null ? "" : sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getInvitName() {
        return invitName == null ? "" : invitName;
    }

    public void setInvitName(String invitName) {
        this.invitName = invitName;
    }

    public String getUnionid() {
        return unionid == null ? "" : unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userName\":\'" + userName + "\'" +
                ", \"sms_code\":\'" + sms_code + "\'" +
                ", \"invitName\":\'" + invitName + "\'" +
                ", \"unionid\":\'" + unionid + "\'" +
                '}';
    }
}
