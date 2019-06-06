package com.example.doctorclient.event;

public class SendMessageDto {

    /**
     * code : 1
     * data : {"IUID":"5f7f656f-3591-4c22-ba90-683c38c374f8","askid":"747edd52-63c9-4ba3-aae1-330848c5c2ff","userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"哈哈","chat_time":"/Date(1558682913394)/","chat_time_stamp":1558682913394,"touserid":"b4aece8e-0014-494c-bed1-ac900dac1f25","sys_flag":null,"read_flag":0,"notread":null,"userIMG":"/DOC/18529250717/2019516144849376j.jpg","niceName":"李松","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":0,"heigh":0}
     * msg : 发送成功
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
         * IUID : 5f7f656f-3591-4c22-ba90-683c38c374f8
         * askid : 747edd52-63c9-4ba3-aae1-330848c5c2ff
         * userid : 9884b9cd-2222-40ee-8859-e6878b0081f1
         * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * chat_note : 哈哈
         * chat_time : /Date(1558682913394)/
         * chat_time_stamp : 1558682913394
         * touserid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * sys_flag : null
         * read_flag : 0
         * notread : null
         * userIMG : /DOC/18529250717/2019516144849376j.jpg
         * niceName : 李松
         * EndDate : null
         * DateStart : null
         * the_class : 0
         * Accessid : null
         * userPhone : null
         * touserPhone : null
         * width : 0
         * heigh : 0
         */

        private String IUID;
        private String askid;
        private String userid;
        private String patientid;
        private String chat_note;
        private String chat_time;
        private long chat_time_stamp;
        private String touserid;
        private Object sys_flag;
        private int read_flag;
        private Object notread;
        private String userIMG;
        private String niceName;
        private Object EndDate;
        private Object DateStart;
        private int the_class;
        private Object Accessid;
        private Object userPhone;
        private Object touserPhone;
        private int width;
        private int heigh;

        public String getIUID() {
            return IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getAskid() {
            return askid;
        }

        public void setAskid(String askid) {
            this.askid = askid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getChat_note() {
            return chat_note;
        }

        public void setChat_note(String chat_note) {
            this.chat_note = chat_note;
        }

        public String getChat_time() {
            return chat_time;
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

        public String getTouserid() {
            return touserid;
        }

        public void setTouserid(String touserid) {
            this.touserid = touserid;
        }

        public Object getSys_flag() {
            return sys_flag;
        }

        public void setSys_flag(Object sys_flag) {
            this.sys_flag = sys_flag;
        }

        public int getRead_flag() {
            return read_flag;
        }

        public void setRead_flag(int read_flag) {
            this.read_flag = read_flag;
        }

        public Object getNotread() {
            return notread;
        }

        public void setNotread(Object notread) {
            this.notread = notread;
        }

        public String getUserIMG() {
            return userIMG;
        }

        public void setUserIMG(String userIMG) {
            this.userIMG = userIMG;
        }

        public String getNiceName() {
            return niceName;
        }

        public void setNiceName(String niceName) {
            this.niceName = niceName;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object EndDate) {
            this.EndDate = EndDate;
        }

        public Object getDateStart() {
            return DateStart;
        }

        public void setDateStart(Object DateStart) {
            this.DateStart = DateStart;
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

        public void setAccessid(Object Accessid) {
            this.Accessid = Accessid;
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

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeigh() {
            return heigh;
        }

        public void setHeigh(int heigh) {
            this.heigh = heigh;
        }
    }
}
