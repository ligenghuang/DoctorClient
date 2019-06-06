package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class IncomeDetailsListDto {

    /**
     * code : 1
     * data : [{"IUID":"0001f09e-bf23-47ea-b567-3797d777abc3","COIN_ID":null,"ORDERID":"L64190023874","GENERATIONTIME":"/Date(1542615023873)/","TRANSATIONTYPE":"问诊","AMOUNTINCURRED":"+0.0200000000000000","NOTE":"回答患者XXX的问诊","userId":"9884b9cd-2222-40ee-8859-e6878b0081f1","left_money":null,"AVAILABLE":0.02,"FREEZE":0,"tempMoeny":0,"Re_investmentPool":0}]
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
         * IUID : 0001f09e-bf23-47ea-b567-3797d777abc3
         * COIN_ID : null
         * ORDERID : L64190023874
         * GENERATIONTIME : /Date(1542615023873)/
         * TRANSATIONTYPE : 问诊
         * AMOUNTINCURRED : +0.0200000000000000
         * NOTE : 回答患者XXX的问诊
         * userId : 9884b9cd-2222-40ee-8859-e6878b0081f1
         * left_money : null
         * AVAILABLE : 0.02
         * FREEZE : 0.0
         * tempMoeny : 0.0
         * Re_investmentPool : 0.0
         */

        private String IUID;
        private Object COIN_ID;
        private String ORDERID;
        private String GENERATIONTIME;
        private String TRANSATIONTYPE;
        private String AMOUNTINCURRED;
        private long GENERATIONTIME_stamp;
        private String NOTE;
        private String userId;
        private Object left_money;
        private double AVAILABLE;
        private double FREEZE;
        private double tempMoeny;
        private double Re_investmentPool;

        public long getGENERATIONTIME_stamp() {
            return GENERATIONTIME_stamp;
        }

        public void setGENERATIONTIME_stamp(long GENERATIONTIME_stamp) {
            this.GENERATIONTIME_stamp = GENERATIONTIME_stamp;
        }

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public Object getCOIN_ID() {
            return COIN_ID;
        }

        public void setCOIN_ID(Object COIN_ID) {
            this.COIN_ID = COIN_ID;
        }

        public String getORDERID() {
            return ORDERID == null ? "" : ORDERID;
        }

        public void setORDERID(String ORDERID) {
            this.ORDERID = ORDERID;
        }

        public String getGENERATIONTIME() {
            return GENERATIONTIME == null ? "" : GENERATIONTIME;
        }

        public void setGENERATIONTIME(String GENERATIONTIME) {
            this.GENERATIONTIME = GENERATIONTIME;
        }

        public String getTRANSATIONTYPE() {
            return TRANSATIONTYPE == null ? "" : TRANSATIONTYPE;
        }

        public void setTRANSATIONTYPE(String TRANSATIONTYPE) {
            this.TRANSATIONTYPE = TRANSATIONTYPE;
        }

        public String getAMOUNTINCURRED() {
            return AMOUNTINCURRED == null ? "" : AMOUNTINCURRED;
        }

        public void setAMOUNTINCURRED(String AMOUNTINCURRED) {
            this.AMOUNTINCURRED = AMOUNTINCURRED;
        }

        public String getNOTE() {
            return NOTE == null ? "" : NOTE;
        }

        public void setNOTE(String NOTE) {
            this.NOTE = NOTE;
        }

        public String getUserId() {
            return userId == null ? "" : userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getLeft_money() {
            return left_money;
        }

        public void setLeft_money(Object left_money) {
            this.left_money = left_money;
        }

        public double getAVAILABLE() {
            return AVAILABLE;
        }

        public void setAVAILABLE(double AVAILABLE) {
            this.AVAILABLE = AVAILABLE;
        }

        public double getFREEZE() {
            return FREEZE;
        }

        public void setFREEZE(double FREEZE) {
            this.FREEZE = FREEZE;
        }

        public double getTempMoeny() {
            return tempMoeny;
        }

        public void setTempMoeny(double tempMoeny) {
            this.tempMoeny = tempMoeny;
        }

        public double getRe_investmentPool() {
            return Re_investmentPool;
        }

        public void setRe_investmentPool(double re_investmentPool) {
            Re_investmentPool = re_investmentPool;
        }
    }
}
