package com.example.doctorclient.event;

public class BankDto {


    /**
     * code : 1
     * data : {"name":"哈哈哈","cardID":"45558555","bank":"呵呵哈哈哈","bank_definite":"144555"}
     * msg : 已绑定
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
         * name : 哈哈哈
         * cardID : 45558555
         * bank : 呵呵哈哈哈
         * bank_definite : 144555
         */

        private String name;
        private String cardID;
        private String bank;
        private String bank_definite;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardID() {
            return cardID == null ? "" : cardID;
        }

        public void setCardID(String cardID) {
            this.cardID = cardID;
        }

        public String getBank() {
            return bank == null ? "" : bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBank_definite() {
            return bank_definite == null ? "" : bank_definite;
        }

        public void setBank_definite(String bank_definite) {
            this.bank_definite = bank_definite;
        }
    }
}
