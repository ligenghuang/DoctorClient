package com.example.doctorclient.event;

public class PrescriptionDrugInfoDto {

    /**
     * code : 1
     * data : {"IUID":"c987a285-f64c-453c-b095-8b7408f84d0c","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"的","departid":"1","departName":"皮肤科","the_memo":"饿","drugCount":0,"create_time":"/Date(1558420850877)/"}
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
         * IUID : c987a285-f64c-453c-b095-8b7408f84d0c
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * name : 的
         * departid : 1
         * departName : 皮肤科
         * the_memo : 饿
         * drugCount : 0
         * create_time : /Date(1558420850877)/
         */

        private String IUID;
        private String doctorid;
        private String name;
        private String departid;
        private String departName;
        private String the_memo;
        private int drugCount;
        private String create_time;

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

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartid() {
            return departid == null ? "" : departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getDepartName() {
            return departName == null ? "" : departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getThe_memo() {
            return the_memo == null ? "" : the_memo;
        }

        public void setThe_memo(String the_memo) {
            this.the_memo = the_memo;
        }

        public int getDrugCount() {
            return drugCount;
        }

        public void setDrugCount(int drugCount) {
            this.drugCount = drugCount;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
