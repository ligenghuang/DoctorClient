package com.example.doctorclient.event;

public class RongUserInfoDto {

    /**
     * code : 200
     * userName : 123
     * userPortrait :
     * createTime : 2016-05-24 10:38:19
     */

    private int code;
    private String userName;
    private String userPortrait;
    private String createTime;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPortrait() {
        return userPortrait == null ? "" : userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
