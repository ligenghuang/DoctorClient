package com.example.doctorclient.event;

import java.util.List;

public class MessageListDto {

    /**
     * code : 1
     * data : [{"IUID":null,"askid":"747edd52-63c9-4ba3-aae1-330848c5c2ff","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":null,"chat_note":"/DOC/toChatWith/18529250717/201952216555616j.jpg","chat_time":"/Date(1558512355857)/","chat_time_stamp":1558512355857,"touserid":null,"sys_flag":null,"read_flag":null,"notread":0,"userIMG":null,"niceName":"144389","EndDate":null,"DateStart":null,"the_class":1,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null}]
     * msg : null
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private Object msg;
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

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
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
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * IUID : null
         * askid : 747edd52-63c9-4ba3-aae1-330848c5c2ff
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * patientid : null
         * chat_note : /DOC/toChatWith/18529250717/201952216555616j.jpg
         * chat_time : /Date(1558512355857)/
         * chat_time_stamp : 1558512355857
         * touserid : null
         * sys_flag : null
         * read_flag : null
         * notread : 0
         * userIMG : null
         * niceName : 144389
         * EndDate : null
         * DateStart : null
         * the_class : 1
         * Accessid : null
         * userPhone : null
         * touserPhone : null
         * width : null
         * heigh : null
         */

        private Object IUID;
        private String askid;
        private String userid;
        private Object patientid;
        private String chat_note;
        private String chat_time;
        private long chat_time_stamp;
        private Object touserid;
        private Object sys_flag;
        private Object read_flag;
        private int notread;
        private String userIMG;
        private String niceName;
        private Object EndDate;
        private Object DateStart;
        private int the_class;
        private Object Accessid;
        private Object userPhone;
        private Object touserPhone;
        private Object width;
        private Object heigh;

        public Object getIUID() {
            return IUID;
        }

        public void setIUID(Object IUID) {
            this.IUID = IUID;
        }

        public String getAskid() {
            return askid == null ? "" : askid;
        }

        public void setAskid(String askid) {
            this.askid = askid;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public Object getPatientid() {
            return patientid;
        }

        public void setPatientid(Object patientid) {
            this.patientid = patientid;
        }

        public String getChat_note() {
            return chat_note == null ? "" : chat_note;
        }

        public void setChat_note(String chat_note) {
            this.chat_note = chat_note;
        }

        public String getChat_time() {
            return chat_time == null ? "" : chat_time;
        }

        public void setChat_time(String chat_time) {
            this.chat_time = chat_time;
        }

        public long getChat_time_stamp() {
            return chat_time_stamp;
        }

        public void setChat_time_stamp(long chat_time_stamp) {
            this.chat_time_stamp = chat_time_stamp;
        }

        public Object getTouserid() {
            return touserid;
        }

        public void setTouserid(Object touserid) {
            this.touserid = touserid;
        }

        public Object getSys_flag() {
            return sys_flag;
        }

        public void setSys_flag(Object sys_flag) {
            this.sys_flag = sys_flag;
        }

        public Object getRead_flag() {
            return read_flag;
        }

        public void setRead_flag(Object read_flag) {
            this.read_flag = read_flag;
        }

        public int getNotread() {
            return notread;
        }

        public void setNotread(int notread) {
            this.notread = notread;
        }

        public String getUserIMG() {
            return userIMG == null ? "" : userIMG;
        }

        public void setUserIMG(String userIMG) {
            this.userIMG = userIMG;
        }

        public String getNiceName() {
            return niceName == null ? "" : niceName;
        }

        public void setNiceName(String niceName) {
            this.niceName = niceName;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object endDate) {
            EndDate = endDate;
        }

        public Object getDateStart() {
            return DateStart;
        }

        public void setDateStart(Object dateStart) {
            DateStart = dateStart;
        }

        public int getThe_class() {
            return the_class;
        }

        public void setThe_class(int the_class) {
            this.the_class = the_class;
        }

        public Object getAccessid() {
            return Accessid;
        }

        public void setAccessid(Object accessid) {
            Accessid = accessid;
        }

        public Object getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(Object userPhone) {
            this.userPhone = userPhone;
        }

        public Object getTouserPhone() {
            return touserPhone;
        }

        public void setTouserPhone(Object touserPhone) {
            this.touserPhone = touserPhone;
        }

        public Object getWidth() {
            return width;
        }

        public void setWidth(Object width) {
            this.width = width;
        }

        public Object getHeigh() {
            return heigh;
        }

        public void setHeigh(Object heigh) {
            this.heigh = heigh;
        }
    }
}
