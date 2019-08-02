package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFeeDto {

    /**
     * code : 1
     * data : [{"id":"com.yizhitong.user_6","value":"6"},{"id":"com.yizhitong.user_12","value":"12"},{"id":"com.yizhitong.user_30","value":"30"},{"id":"com.yizhitong.user_40","value":"40"},{"id":"com.yizhitong.user_50","value":"50"},{"id":"com.yizhitong.user_60","value":"60"},{"id":"com.yizhitong.user_88","value":"88"},{"id":"com.yizhitong.user_98","value":"98"}]
     * msg : 查询成功
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;
    private List<DataBean> data;

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

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
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

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : com.yizhitong.user_6
         * value : 6
         */

        private String id;
        private String value;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value == null ? "" : value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
