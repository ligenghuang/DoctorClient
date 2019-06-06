package com.example.doctorclient.event;

import com.google.gson.annotations.SerializedName;

public class MessageDto {


    /**
     * event : chat
     * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
     * touserid : 9884b9cd-2222-40ee-8859-e6878b0081f1
     * note : asd
     * class : txt
     * iuid : f5371ebd-29ec-42cf-8848-582f1c9fc338
     * userImg : null
     * time : 1559097273789
     */

    private String event;
    private String userid;
    private String touserid;
    private String note;
    @SerializedName("class")
    private String classX;
    private String iuid;
    private String userImg;
    private String time;
    private String res;

    public String getRes() {
        return res == null ? "" : res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTouserid() {
        return touserid;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getIuid() {
        return iuid;
    }

    public void setIuid(String iuid) {
        this.iuid = iuid;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
