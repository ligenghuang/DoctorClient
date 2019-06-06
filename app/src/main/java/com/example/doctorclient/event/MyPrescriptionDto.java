package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class MyPrescriptionDto {

    /**
     * code : 1
     * data : [{"DrugMV":[{"IUID":null,"askDrugId":null,"name":"复方丹参滴丸","the_class":null,"otc_class":null,"the_company":null,"the_spec":"每丸重27mg","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":2,"pinyin":null,"py":null,"xq":null},{"IUID":null,"askDrugId":null,"name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":2,"pinyin":null,"py":null,"xq":null}],"patientName":"小李","drug_money":198,"pay_flag":0,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":1.98,"askdrugheadid":"0e9830de-6d3a-42d4-bda7-819fc3fcc249","imt_url":"http://192.168.0.104:8014/DOC/prescription/img/geren2.png"},{"DrugMV":[],"patientName":"小李","drug_money":0,"pay_flag":0,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":0,"askdrugheadid":"8eeb1818-cc35-4fba-9ced-7d6f2b2c53ab","imt_url":"http://192.168.0.104:8014/DOC/prescription/img/geren2.png"},{"DrugMV":[{"IUID":null,"askDrugId":null,"name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":2,"pinyin":null,"py":null,"xq":null},{"IUID":null,"askDrugId":null,"name":"贞芪扶正颗粒","the_class":null,"otc_class":null,"the_company":null,"the_spec":"5g","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":1,"pinyin":null,"py":null,"xq":null},{"IUID":null,"askDrugId":null,"name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":4,"pinyin":null,"py":null,"xq":null}],"patientName":"小李","drug_money":346.5,"pay_flag":0,"agree_flag":1,"reback_flag":0,"finish_flag":0,"brokerage":3.46,"askdrugheadid":"7ecf1ef7-cbd7-4bb5-9d57-73157a7d66dd","imt_url":"http://192.168.0.104:8014/DOC/prescription/img/geren2.png"}]
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

    public static class DataBean {
        /**
         * DrugMV : [{"IUID":null,"askDrugId":null,"name":"复方丹参滴丸","the_class":null,"otc_class":null,"the_company":null,"the_spec":"每丸重27mg","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":2,"pinyin":null,"py":null,"xq":null},{"IUID":null,"askDrugId":null,"name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":null,"bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":2,"pinyin":null,"py":null,"xq":null}]
         * patientName : 小李
         * drug_money : 198.0
         * pay_flag : 0
         * agree_flag : 0
         * reback_flag : 0
         * finish_flag : 0
         * brokerage : 1.98
         * askdrugheadid : 0e9830de-6d3a-42d4-bda7-819fc3fcc249
         * imt_url : http://192.168.0.104:8014/DOC/prescription/img/geren2.png
         */

        private String patientName;
        private double drug_money;
        private int pay_flag;
        private int agree_flag;
        private int reback_flag;
        private int finish_flag;
        private double brokerage;
        private String askdrugheadid;
        private String imt_url;
        private List<DrugMVBean> DrugMV;

        public String getPatientName() {
            return patientName == null ? "" : patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public double getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(double drug_money) {
            this.drug_money = drug_money;
        }

        public int getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(int pay_flag) {
            this.pay_flag = pay_flag;
        }

        public int getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(int agree_flag) {
            this.agree_flag = agree_flag;
        }

        public int getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(int reback_flag) {
            this.reback_flag = reback_flag;
        }

        public int getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(int finish_flag) {
            this.finish_flag = finish_flag;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public String getAskdrugheadid() {
            return askdrugheadid == null ? "" : askdrugheadid;
        }

        public void setAskdrugheadid(String askdrugheadid) {
            this.askdrugheadid = askdrugheadid;
        }

        public String getImt_url() {
            return imt_url == null ? "" : imt_url;
        }

        public void setImt_url(String imt_url) {
            this.imt_url = imt_url;
        }

        public List<DrugMVBean> getDrugMV() {
            if (DrugMV == null) {
                return new ArrayList<>();
            }
            return DrugMV;
        }

        public void setDrugMV(List<DrugMVBean> drugMV) {
            DrugMV = drugMV;
        }

        public static class DrugMVBean {
            /**
             * IUID : null
             * askDrugId : null
             * name : 复方丹参滴丸
             * the_class : null
             * otc_class : null
             * the_company : null
             * the_spec : 每丸重27mg
             * element : null
             * appear : null
             * indication : null
             * num_note : null
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
             * drug_num : 2.0
             * pinyin : null
             * py : null
             * xq : null
             */

            private Object IUID;
            private Object askDrugId;
            private String name;
            private Object the_class;
            private Object otc_class;
            private Object the_company;
            private String the_spec;
            private Object element;
            private Object appear;
            private Object indication;
            private Object num_note;
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

            public Object getIUID() {
                return IUID;
            }

            public void setIUID(Object IUID) {
                this.IUID = IUID;
            }

            public Object getAskDrugId() {
                return askDrugId;
            }

            public void setAskDrugId(Object askDrugId) {
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

            public Object getThe_company() {
                return the_company;
            }

            public void setThe_company(Object the_company) {
                this.the_company = the_company;
            }

            public String getThe_spec() {
                return the_spec == null ? "" : the_spec;
            }

            public void setThe_spec(String the_spec) {
                this.the_spec = the_spec;
            }

            public Object getElement() {
                return element;
            }

            public void setElement(Object element) {
                this.element = element;
            }

            public Object getAppear() {
                return appear;
            }

            public void setAppear(Object appear) {
                this.appear = appear;
            }

            public Object getIndication() {
                return indication;
            }

            public void setIndication(Object indication) {
                this.indication = indication;
            }

            public Object getNum_note() {
                return num_note;
            }

            public void setNum_note(Object num_note) {
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
        }
    }
}
