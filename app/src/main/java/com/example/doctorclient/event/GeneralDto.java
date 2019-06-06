package com.example.doctorclient.event;

public class GeneralDto {


    /**
     * code : 1
     * data : 9884b9cd-2222-40ee-8859-e6878b0081f1
     * msg : 登陆成功!
     * url : /Home/Index
     * wait : 3
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private String data;
    private String msg;
    private String url;
    private int wait;
    private int Accessid;
    private Object data2;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getAccessid() {
        return Accessid;
    }

    public void setAccessid(int Accessid) {
        this.Accessid = Accessid;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return "GeneralDto{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", wait=" + wait +
                ", Accessid=" + Accessid +
                ", data2=" + data2 +
                '}';
    }
}
