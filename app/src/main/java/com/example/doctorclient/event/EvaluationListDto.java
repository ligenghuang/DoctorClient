package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class EvaluationListDto {


    /**
     * code : 1
     * data : [{"IUID":"c03cfeeb-4778-4462-9f7e-0a129de1c55c","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","askid":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","use_img":null,"nickname":"144389","the_note":"医术精湛 态度良好 dsfdsafsdf ","the_star":5,"create_time":"/Date(1559180182460)/","doctorName":null,"phone":null,"DateStart":null,"EndDate":null,"anonymous_flag":null}]
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
         * IUID : c03cfeeb-4778-4462-9f7e-0a129de1c55c
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * askid : 6f9a643d-d170-41dc-b439-acab7f2d5bd7
         * use_img : null
         * nickname : 144389
         * the_note : 医术精湛 态度良好 dsfdsafsdf
         * the_star : 5
         * create_time : /Date(1559180182460)/
         * doctorName : null
         * phone : null
         * DateStart : null
         * EndDate : null
         * anonymous_flag : null
         */

        private String IUID;
        private String doctorid;
        private String userid;
        private String askid;
        private String use_img;
        private String nickname;
        private String the_note;
        private int the_star;
        private String create_time;
        private Object doctorName;
        private Object phone;
        private Object DateStart;
        private Object EndDate;
        private Object anonymous_flag;
        private long create_time_stamp;

        public long getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(long create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getDoctorid() {
            return doctorid == null ? "" : doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAskid() {
            return askid == null ? "" : askid;
        }

        public void setAskid(String askid) {
            this.askid = askid;
        }

        public String getUse_img() {
            return use_img == null ? "" : use_img;
        }

        public void setUse_img(String use_img) {
            this.use_img = use_img;
        }

        public String getNickname() {
            return nickname == null ? "" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getThe_note() {
            return the_note == null ? "" : the_note;
        }

        public void setThe_note(String the_note) {
            this.the_note = the_note;
        }

        public int getThe_star() {
            return the_star;
        }

        public void setThe_star(int the_star) {
            this.the_star = the_star;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(Object doctorName) {
            this.doctorName = doctorName;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getDateStart() {
            return DateStart;
        }

        public void setDateStart(Object dateStart) {
            DateStart = dateStart;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object endDate) {
            EndDate = endDate;
        }

        public Object getAnonymous_flag() {
            return anonymous_flag;
        }

        public void setAnonymous_flag(Object anonymous_flag) {
            this.anonymous_flag = anonymous_flag;
        }
    }
}
