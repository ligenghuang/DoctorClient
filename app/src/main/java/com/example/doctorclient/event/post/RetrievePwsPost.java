package com.example.doctorclient.event.post;

public class RetrievePwsPost {

    /**
     * userName : 18529250717
     * imgCode : qnpa
     */

    private String userName;
    private String imgCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"imgCode\":\"")
                .append(imgCode).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
