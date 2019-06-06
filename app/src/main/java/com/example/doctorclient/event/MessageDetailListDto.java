package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailListDto {

    /**
     * code : 1
     * data : {"count":48,"list":[{"IUID":"021f0a8f-0d55-4176-8e11-e55466483d5a","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"您好！请问有什么可以帮您？","chat_time":"/Date(1551670351530)/","chat_time_stamp":1551670351530,"touserid":"b4aece8e-0014-494c-bed1-ac900dac1f25","sys_flag":null,"read_flag":1,"notread":null,"userIMG":"/DOC/18529250717/2019516144849376j.jpg","niceName":"李松","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null},{"IUID":"fa4311f7-3fa2-463d-98a4-dfc3f6ce0311","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"炬华科技好看","chat_time":"/Date(1551926366913)/","chat_time_stamp":null,"touserid":"9884b9cd-2222-40ee-8859-e6878b0081f1","sys_flag":null,"read_flag":1,"notread":null,"userIMG":null,"niceName":"144389","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null},{"IUID":"06815784-1ca3-4890-9e06-4b9243cac423","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"炬华科技好看","chat_time":"/Date(1551926371170)/","chat_time_stamp":null,"touserid":"9884b9cd-2222-40ee-8859-e6878b0081f1","sys_flag":null,"read_flag":1,"notread":null,"userIMG":null,"niceName":"144389","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null}]}
     * msg :
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
         * count : 48
         * list : [{"IUID":"021f0a8f-0d55-4176-8e11-e55466483d5a","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"您好！请问有什么可以帮您？","chat_time":"/Date(1551670351530)/","chat_time_stamp":1551670351530,"touserid":"b4aece8e-0014-494c-bed1-ac900dac1f25","sys_flag":null,"read_flag":1,"notread":null,"userIMG":"/DOC/18529250717/2019516144849376j.jpg","niceName":"李松","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null},{"IUID":"fa4311f7-3fa2-463d-98a4-dfc3f6ce0311","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"炬华科技好看","chat_time":"/Date(1551926366913)/","chat_time_stamp":null,"touserid":"9884b9cd-2222-40ee-8859-e6878b0081f1","sys_flag":null,"read_flag":1,"notread":null,"userIMG":null,"niceName":"144389","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null},{"IUID":"06815784-1ca3-4890-9e06-4b9243cac423","askid":"b8bae022-835c-415a-8e4a-035aaac8ee42","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","chat_note":"炬华科技好看","chat_time":"/Date(1551926371170)/","chat_time_stamp":null,"touserid":"9884b9cd-2222-40ee-8859-e6878b0081f1","sys_flag":null,"read_flag":1,"notread":null,"userIMG":null,"niceName":"144389","EndDate":null,"DateStart":null,"the_class":0,"Accessid":null,"userPhone":null,"touserPhone":null,"width":null,"heigh":null}]
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            if (list == null) {
                return new ArrayList<>();
            }
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * IUID : 021f0a8f-0d55-4176-8e11-e55466483d5a
             * askid : b8bae022-835c-415a-8e4a-035aaac8ee42
             * userid : 9884b9cd-2222-40ee-8859-e6878b0081f1
             * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
             * chat_note : 您好！请问有什么可以帮您？
             * chat_time : /Date(1551670351530)/
             * chat_time_stamp : 1551670351530
             * touserid : b4aece8e-0014-494c-bed1-ac900dac1f25
             * sys_flag : null
             * read_flag : 1
             * notread : null
             * userIMG : /DOC/18529250717/2019516144849376j.jpg
             * niceName : 李松
             * EndDate : null
             * DateStart : null
             * the_class : 0
             * Accessid : null
             * userPhone : null
             * touserPhone : null
             * width : null
             * heigh : null
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
            private Object width;
            private Object heigh;

            public String getIUID() {
                return IUID == null ? "" : IUID;
            }

            public void setIUID(String IUID) {
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

            public String getPatientid() {
                return patientid == null ? "" : patientid;
            }

            public void setPatientid(String patientid) {
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

            public String getTouserid() {
                return touserid == null ? "" : touserid;
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
}
