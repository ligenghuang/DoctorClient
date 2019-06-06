package com.example.doctorclient.event;

public class GetMessageDto {

    private String event;
    private String userid;
    private String res;

    public String getEvent() {
        return event == null ? "" : event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUserid() {
        return userid == null ? "" : userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRes() {
        return res == null ? "" : res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
