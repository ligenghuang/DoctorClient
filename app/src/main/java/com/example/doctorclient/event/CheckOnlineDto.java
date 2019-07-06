package com.example.doctorclient.event;
/**
* description ： 判断是否在线
* author : lgh
* email : 1045105946@qq.com
* date : 2019/7/6
*/
public class CheckOnlineDto {

    /**
     * code : 200
     * status : 1
     */

    private int code;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
