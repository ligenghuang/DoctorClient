package com.example.doctorclient.event;

public class MessageDetailInquiryDto {


    /**
     * code : 1
     * data : {"askID":"747edd52-63c9-4ba3-aae1-330848c5c2ff","lastTime":"4天","name":"小李","sex":"","age":0,"note":"电饭锅电饭锅","askFlag":1}
     * msg : 查询成功
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private DataBean data;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * askID : 747edd52-63c9-4ba3-aae1-330848c5c2ff
         * lastTime : 4天
         * name : 小李
         * sex :
         * age : 0
         * note : 电饭锅电饭锅
         * askFlag : 1
         */

        private String askID;
        private String lastTime;
        private String name;
        private String sex;
        private int age;
        private String note;
        private int askFlag;

        public String getAskID() {
            return askID == null ? "" : askID;
        }

        public void setAskID(String askID) {
            this.askID = askID;
        }

        public String getLastTime() {
            return lastTime == null ? "" : lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNote() {
            return note == null ? "" : note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getAskFlag() {
            return askFlag;
        }

        public void setAskFlag(int askFlag) {
            this.askFlag = askFlag;
        }
    }
}
