package com.example.doctorclient.event;

public class UserInfoDto {


    /**
     * code : 1
     * data : {"niceImg":null,"the_level":"执业医师","docName":"李松","hospital":"广州广大整形美容医院","income":null,"fact_price":2,"price_day":2}
     * msg : success
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
         * niceImg : null
         * the_level : 执业医师
         * docName : 李松
         * hospital : 广州广大整形美容医院
         * income : null
         * fact_price : 2.0
         * price_day : 2.0
         */

        private String niceImg;
        private String the_level;
        private String docName;
        private String hospital;
        private double income;
        private double fact_price;
        private double price_day;
        private String practicing_time;

        public String getPracticing_time() {
            return practicing_time;
        }

        public void setPracticing_time(String practicing_time) {
            this.practicing_time = practicing_time;
        }

        public String getNiceImg() {
            return niceImg == null ? "" : niceImg;
        }

        public void setNiceImg(String niceImg) {
            this.niceImg = niceImg;
        }

        public String getThe_level() {
            return the_level;
        }

        public void setThe_level(String the_level) {
            this.the_level = the_level;
        }

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }

        public double getFact_price() {
            return fact_price;
        }

        public void setFact_price(double fact_price) {
            this.fact_price = fact_price;
        }

        public double getPrice_day() {
            return price_day;
        }

        public void setPrice_day(double price_day) {
            this.price_day = price_day;
        }
    }
}
