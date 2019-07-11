package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class DepartListDto {

    /**
     * code : 0
     * data : [{"name":"皮肤科","child":[{"name":"中药科"}]},{"name":"内科","child":[{"name":"心脏科"},{"name":"呼吸内科"}]},{"name":"骨科","child":[{"name":"接骨科"}]},{"name":"中医科","child":[{"name":"针灸科"},{"name":"推拿科"}]}]
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
    private ArrayList<JsonBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
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

    public void setAccessid(int accessid) {
        Accessid = accessid;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public ArrayList<JsonBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(ArrayList<JsonBean> data) {
        this.data = data;
    }
}
