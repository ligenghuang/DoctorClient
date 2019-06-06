package com.example.doctorclient.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DrugListDto implements Serializable{

    /**
     * code : 1
     * data : [{"IUID":"116","askDrugId":null,"name":"盐酸阿莫罗芬搽剂","the_class":null,"otc_class":null,"the_company":"江苏福邦药业有限公司","the_spec":"0.05","element":"主要成份为：盐酸阿莫罗芬。","appear":"无色或几乎无色的澄明液体。","indication":null,"num_note":"###############################################################################################################################################################################################################################################################","bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":null,"pinyin":null,"py":null,"xq":null}]
     * msg :
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

    public static class DataBean implements Serializable {
        /**
         * IUID : 116
         * askDrugId : null
         * name : 盐酸阿莫罗芬搽剂
         * the_class : null
         * otc_class : null
         * the_company : 江苏福邦药业有限公司
         * the_spec : 0.05
         * element : 主要成份为：盐酸阿莫罗芬。
         * appear : 无色或几乎无色的澄明液体。
         * indication : null
         * num_note : ###############################################################################################################################################################################################################################################################
         * bad_effect : null
         * bad_no : null
         * attention_note : null
         * drug_response : null
         * theory : null
         * keep_note : null
         * the_img : /DOC/prescription/img/editorial_prescription_imgs.png
         * Accessid : null
         * EndDate : null
         * DateStart : null
         * create_time : null
         * brokerage : null
         * price : 49.5
         * drug_num : null
         * pinyin : null
         * py : null
         * xq : null
         */

        private String IUID;
        private String askDrugId;
        private String name;
        private Object the_class;
        private Object otc_class;
        private String the_company;
        private String the_spec;
        private String element;
        private String appear;
        private Object indication;
        private String num_note;
        private Object bad_effect;
        private Object bad_no;
        private Object attention_note;
        private Object drug_response;
        private Object theory;
        private Object keep_note;
        private String the_img;
        private Object Accessid;
        private Object EndDate;
        private Object DateStart;
        private Object create_time;
        private Object brokerage;
        private double price;
        private int drug_num;
        private Object pinyin;
        private Object py;
        private Object xq;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getAskDrugId() {
            return askDrugId;
        }

        public void setAskDrugId(String askDrugId) {
            this.askDrugId = askDrugId;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getThe_class() {
            return the_class;
        }

        public void setThe_class(Object the_class) {
            this.the_class = the_class;
        }

        public Object getOtc_class() {
            return otc_class;
        }

        public void setOtc_class(Object otc_class) {
            this.otc_class = otc_class;
        }

        public String getThe_company() {
            return the_company == null ? "" : the_company;
        }

        public void setThe_company(String the_company) {
            this.the_company = the_company;
        }

        public String getThe_spec() {
            return the_spec == null ? "" : the_spec;
        }

        public void setThe_spec(String the_spec) {
            this.the_spec = the_spec;
        }

        public String getElement() {
            return element == null ? "" : element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public String getAppear() {
            return appear == null ? "" : appear;
        }

        public void setAppear(String appear) {
            this.appear = appear;
        }

        public Object getIndication() {
            return indication;
        }

        public void setIndication(Object indication) {
            this.indication = indication;
        }

        public String getNum_note() {
            return num_note == null ? "" : num_note;
        }

        public void setNum_note(String num_note) {
            this.num_note = num_note;
        }

        public Object getBad_effect() {
            return bad_effect;
        }

        public void setBad_effect(Object bad_effect) {
            this.bad_effect = bad_effect;
        }

        public Object getBad_no() {
            return bad_no;
        }

        public void setBad_no(Object bad_no) {
            this.bad_no = bad_no;
        }

        public Object getAttention_note() {
            return attention_note;
        }

        public void setAttention_note(Object attention_note) {
            this.attention_note = attention_note;
        }

        public Object getDrug_response() {
            return drug_response;
        }

        public void setDrug_response(Object drug_response) {
            this.drug_response = drug_response;
        }

        public Object getTheory() {
            return theory;
        }

        public void setTheory(Object theory) {
            this.theory = theory;
        }

        public Object getKeep_note() {
            return keep_note;
        }

        public void setKeep_note(Object keep_note) {
            this.keep_note = keep_note;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public Object getAccessid() {
            return Accessid;
        }

        public void setAccessid(Object accessid) {
            Accessid = accessid;
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

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(Object brokerage) {
            this.brokerage = brokerage;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getDrug_num() {
            return drug_num;
        }

        public void setDrug_num(int drug_num) {
            this.drug_num = drug_num;
        }

        public Object getPinyin() {
            return pinyin;
        }

        public void setPinyin(Object pinyin) {
            this.pinyin = pinyin;
        }

        public Object getPy() {
            return py;
        }

        public void setPy(Object py) {
            this.py = py;
        }

        public Object getXq() {
            return xq;
        }

        public void setXq(Object xq) {
            this.xq = xq;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"IUID\":\'" + IUID + "\'" +
                    ", \"askDrugId\":\'" + askDrugId + "\'" +
                    ", \"name\":\'" + name + "\'" +
                    '}';
        }
    }
}
