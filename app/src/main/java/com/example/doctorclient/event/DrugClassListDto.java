package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class DrugClassListDto {

    /**
     * code : 1
     * data : ["薄膜衣片","搽剂","肠溶片","滴丸剂","酊剂   外用","膏剂","胶囊","胶囊剂","颗粒剂","蜜丸剂","面膜","凝胶","凝胶剂","片剂","片剂  肝病辅助治疗药物","片剂 白三烯受体拮抗剂","溶液","乳膏","乳膏剂","软膏","软膏剂","水丸剂","糖浆剂","洗剂","液体","液体剂"]
     * msg :
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
    private List<String> data;

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

    public List<String> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
