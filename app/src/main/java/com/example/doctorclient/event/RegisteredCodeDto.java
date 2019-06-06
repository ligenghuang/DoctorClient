package com.example.doctorclient.event;

public class RegisteredCodeDto {

    /**
     * msg : 已发送至您的手机，请查收
     * result : 0
     */

    private String msg;
    private int result;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
