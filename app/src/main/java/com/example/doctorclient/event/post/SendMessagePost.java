package com.example.doctorclient.event.post;

public class SendMessagePost {
    private String event;
    private String userid;
    private String touserid;
    private String note;
    private String _class;
    private String iuid;
    private String userImg;
    private long time;

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

    public String getToUserId() {
        return touserid == null ? "" : touserid;
    }

    public void setToUserId(String toUserId) {
        this.touserid = toUserId;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String get_class() {
        return _class == null ? "" : _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getIuid() {
        return iuid == null ? "" : iuid;
    }

    public void setIuid(String iuid) {
        this.iuid = iuid;
    }

    public String getUserImg() {
        return userImg == null ? "" : userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"event\":\"")
                .append(event).append('\"');
        sb.append(",\"userid\":\"")
                .append(userid).append('\"');
        sb.append(",\"touserid\":\"")
                .append(touserid).append('\"');
        sb.append(",\"note\":\"")
                .append(note).append('\"');
        sb.append(",\"class\":\"")
                .append(_class).append('\"');
        sb.append(",\"iuid\":\"")
                .append(iuid).append('\"');
        sb.append(",\"userImg\":\"")
                .append(userImg).append('\"');
        sb.append(",\"time\":")
                .append(time);
        sb.append('}');
        return sb.toString();
    }

    public String toLogin() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"event\":\"")
                .append(event).append('\"');
        sb.append(",\"userid\":\"")
                .append(userid).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
