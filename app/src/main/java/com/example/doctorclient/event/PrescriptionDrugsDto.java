package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDrugsDto {


    /**
     * code : 1
     * data : [{"IUID":"c10b7712-f40e-449d-80a4-311b7e46ca06","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"name1","departid":"4","departName":null,"the_memo":null,"drugCount":2,"create_time":"/Date(1557991842620)/"},{"IUID":"a5d93823-9daf-42f8-a78f-5f28ff96dc8b","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"name","departid":"4","departName":null,"the_memo":null,"drugCount":2,"create_time":"/Date(1557991809797)/"},{"IUID":"74bbe44b-cc61-4fc0-b1cd-c57d16585d01","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"name","departid":"4","departName":null,"the_memo":null,"drugCount":1,"create_time":"/Date(1557913352753)/"},{"IUID":"dd9ce740-1ded-47a5-abbd-d5b4bb2510ca","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"name","departid":"4","departName":null,"the_memo":null,"drugCount":1,"create_time":"/Date(1557913327107)/"},{"IUID":"c5d9a1b7-b93d-4608-9acc-297a8df8be61","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"感冒药","departid":"4","departName":null,"the_memo":"学费水电费水电费","drugCount":0,"create_time":"/Date(1551670593837)/"},{"IUID":"d72d11f8-7c67-42ec-abff-3139f1730076","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"感冒药","departid":"4","departName":null,"the_memo":"感冒药","drugCount":0,"create_time":"/Date(1551670410650)/"}]
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
         * IUID : c10b7712-f40e-449d-80a4-311b7e46ca06
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * name : name1
         * departid : 4
         * departName : null
         * the_memo : null
         * drugCount : 2
         * create_time : /Date(1557991842620)/
         */

        private String IUID;
        private String doctorid;
        private String name;
        private String departid;
        private Object departName;
        private Object the_memo;
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

        public Object getDepartName() {
            return departName;
        }

        public void setDepartName(Object departName) {
            this.departName = departName;
        }

        public Object getThe_memo() {
            return the_memo;
        }

        public void setThe_memo(Object the_memo) {
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
