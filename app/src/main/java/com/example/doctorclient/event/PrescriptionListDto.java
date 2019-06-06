package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionListDto {

    /**
     * code : 1
     * data : [{"IUID":"3495f1c0-c609-4b60-8046-42e413c27dd4","name":null,"the_memo":"第三方士大夫士大夫aaa","askid":null,"patientid":null,"doctorid":null,"askdrug_no":null,"create_time":null,"pay_time":null,"pay_flag":0,"pay_class":null,"drug_money":null,"doctor_profit":null,"agree_flag":null,"finish_flag":null,"reback_flag":null,"pay_money":null,"EndDate":null,"DateStart":null,"PEndDate":null,"PDateStart":null,"AskDrugMV":[{"IUID":null,"askid":null,"drugid":null,"askdrugheadid":null,"drug_name":"小儿多维元素片(小儿善存片)","the_company":"惠氏制药有限公司","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","drug_num":4,"drug_price":null,"drug_money":null,"one_num":null,"day_num":null,"use_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"drug_img":null,"DDH":null,"CFname":null,"the_memo":null,"isPhoto":null}],"stock_flag":null,"patientName":null,"doctorName":null,"isPhoto":null},{"IUID":"4cb6a57d-b3a4-4edc-8ace-4dbca3be740b","name":null,"the_memo":"","askid":null,"patientid":null,"doctorid":null,"askdrug_no":null,"create_time":null,"pay_time":null,"pay_flag":0,"pay_class":null,"drug_money":null,"doctor_profit":null,"agree_flag":null,"finish_flag":null,"reback_flag":null,"pay_money":null,"EndDate":null,"DateStart":null,"PEndDate":null,"PDateStart":null,"AskDrugMV":[{"IUID":null,"askid":null,"drugid":null,"askdrugheadid":null,"drug_name":"贞芪扶正颗粒","the_company":"吉林省辉南长龙生化药业股份有限公司 ","the_spec":"5g","drug_num":2,"drug_price":null,"drug_money":null,"one_num":null,"day_num":null,"use_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"drug_img":null,"DDH":null,"CFname":null,"the_memo":null,"isPhoto":null},{"IUID":null,"askid":null,"drugid":null,"askdrugheadid":null,"drug_name":"小儿多维元素片(小儿善存片)","the_company":"惠氏制药有限公司","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","drug_num":2,"drug_price":null,"drug_money":null,"one_num":null,"day_num":null,"use_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"drug_img":null,"DDH":null,"CFname":null,"the_memo":null,"isPhoto":null}],"stock_flag":null,"patientName":null,"doctorName":null,"isPhoto":null},{"IUID":"1b203a86-939b-490f-a178-2abdc4ab26ce","name":null,"the_memo":"第三方士大夫士大夫aaa","askid":null,"patientid":null,"doctorid":null,"askdrug_no":null,"create_time":null,"pay_time":null,"pay_flag":0,"pay_class":null,"drug_money":null,"doctor_profit":null,"agree_flag":null,"finish_flag":null,"reback_flag":null,"pay_money":null,"EndDate":null,"DateStart":null,"PEndDate":null,"PDateStart":null,"AskDrugMV":[{"IUID":null,"askid":null,"drugid":null,"askdrugheadid":null,"drug_name":"小儿多维元素片(小儿善存片)","the_company":"惠氏制药有限公司","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","drug_num":4,"drug_price":null,"drug_money":null,"one_num":null,"day_num":null,"use_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"drug_img":null,"DDH":null,"CFname":null,"the_memo":null,"isPhoto":null}],"stock_flag":null,"patientName":null,"doctorName":null,"isPhoto":null}]
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
         * IUID : 3495f1c0-c609-4b60-8046-42e413c27dd4
         * name : null
         * the_memo : 第三方士大夫士大夫aaa
         * askid : null
         * patientid : null
         * doctorid : null
         * askdrug_no : null
         * create_time : null
         * pay_time : null
         * pay_flag : 0
         * pay_class : null
         * drug_money : null
         * doctor_profit : null
         * agree_flag : null
         * finish_flag : null
         * reback_flag : null
         * pay_money : null
         * EndDate : null
         * DateStart : null
         * PEndDate : null
         * PDateStart : null
         * AskDrugMV : [{"IUID":null,"askid":null,"drugid":null,"askdrugheadid":null,"drug_name":"小儿多维元素片(小儿善存片)","the_company":"惠氏制药有限公司","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","drug_num":4,"drug_price":null,"drug_money":null,"one_num":null,"day_num":null,"use_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"drug_img":null,"DDH":null,"CFname":null,"the_memo":null,"isPhoto":null}]
         * stock_flag : null
         * patientName : null
         * doctorName : null
         * isPhoto : null
         */

        private String IUID;
        private Object name;
        private String the_memo;
        private Object askid;
        private Object patientid;
        private Object doctorid;
        private Object askdrug_no;
        private Object create_time;
        private Object pay_time;
        private int pay_flag;
        private Object pay_class;
        private Object drug_money;
        private Object doctor_profit;
        private Object agree_flag;
        private Object finish_flag;
        private Object reback_flag;
        private Object pay_money;
        private Object EndDate;
        private Object DateStart;
        private Object PEndDate;
        private Object PDateStart;
        private Object stock_flag;
        private Object patientName;
        private Object doctorName;
        private Object isPhoto;
        private List<AskDrugMVBean> AskDrugMV;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getThe_memo() {
            return the_memo == null ? "" : the_memo;
        }

        public void setThe_memo(String the_memo) {
            this.the_memo = the_memo;
        }

        public Object getAskid() {
            return askid;
        }

        public void setAskid(Object askid) {
            this.askid = askid;
        }

        public Object getPatientid() {
            return patientid;
        }

        public void setPatientid(Object patientid) {
            this.patientid = patientid;
        }

        public Object getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(Object doctorid) {
            this.doctorid = doctorid;
        }

        public Object getAskdrug_no() {
            return askdrug_no;
        }

        public void setAskdrug_no(Object askdrug_no) {
            this.askdrug_no = askdrug_no;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getPay_time() {
            return pay_time;
        }

        public void setPay_time(Object pay_time) {
            this.pay_time = pay_time;
        }

        public int getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(int pay_flag) {
            this.pay_flag = pay_flag;
        }

        public Object getPay_class() {
            return pay_class;
        }

        public void setPay_class(Object pay_class) {
            this.pay_class = pay_class;
        }

        public Object getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(Object drug_money) {
            this.drug_money = drug_money;
        }

        public Object getDoctor_profit() {
            return doctor_profit;
        }

        public void setDoctor_profit(Object doctor_profit) {
            this.doctor_profit = doctor_profit;
        }

        public Object getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(Object agree_flag) {
            this.agree_flag = agree_flag;
        }

        public Object getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(Object finish_flag) {
            this.finish_flag = finish_flag;
        }

        public Object getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(Object reback_flag) {
            this.reback_flag = reback_flag;
        }

        public Object getPay_money() {
            return pay_money;
        }

        public void setPay_money(Object pay_money) {
            this.pay_money = pay_money;
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

        public Object getPEndDate() {
            return PEndDate;
        }

        public void setPEndDate(Object PEndDate) {
            this.PEndDate = PEndDate;
        }

        public Object getPDateStart() {
            return PDateStart;
        }

        public void setPDateStart(Object PDateStart) {
            this.PDateStart = PDateStart;
        }

        public Object getStock_flag() {
            return stock_flag;
        }

        public void setStock_flag(Object stock_flag) {
            this.stock_flag = stock_flag;
        }

        public Object getPatientName() {
            return patientName;
        }

        public void setPatientName(Object patientName) {
            this.patientName = patientName;
        }

        public Object getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(Object doctorName) {
            this.doctorName = doctorName;
        }

        public Object getIsPhoto() {
            return isPhoto;
        }

        public void setIsPhoto(Object isPhoto) {
            this.isPhoto = isPhoto;
        }

        public List<AskDrugMVBean> getAskDrugMV() {
            if (AskDrugMV == null) {
                return new ArrayList<>();
            }
            return AskDrugMV;
        }

        public void setAskDrugMV(List<AskDrugMVBean> askDrugMV) {
            AskDrugMV = askDrugMV;
        }

        public static class AskDrugMVBean {
            /**
             * IUID : null
             * askid : null
             * drugid : null
             * askdrugheadid : null
             * drug_name : 小儿多维元素片(小儿善存片)
             * the_company : 惠氏制药有限公司
             * the_spec : 60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶
             * drug_num : 4.0
             * drug_price : null
             * drug_money : null
             * one_num : null
             * day_num : null
             * use_note : null
             * the_img : /DOC/prescription/img/editorial_prescription_imgs.png
             * Accessid : null
             * drug_img : null
             * DDH : null
             * CFname : null
             * the_memo : null
             * isPhoto : null
             */

            private Object IUID;
            private Object askid;
            private Object drugid;
            private Object askdrugheadid;
            private String drug_name;
            private String the_company;
            private String the_spec;
            private int drug_num;
            private Object drug_price;
            private Object drug_money;
            private Object one_num;
            private Object day_num;
            private Object use_note;
            private String the_img;
            private Object Accessid;
            private Object drug_img;
            private Object DDH;
            private Object CFname;
            private Object the_memo;
            private Object isPhoto;

            public Object getIUID() {
                return IUID;
            }

            public void setIUID(Object IUID) {
                this.IUID = IUID;
            }

            public Object getAskid() {
                return askid;
            }

            public void setAskid(Object askid) {
                this.askid = askid;
            }

            public Object getDrugid() {
                return drugid;
            }

            public void setDrugid(Object drugid) {
                this.drugid = drugid;
            }

            public Object getAskdrugheadid() {
                return askdrugheadid;
            }

            public void setAskdrugheadid(Object askdrugheadid) {
                this.askdrugheadid = askdrugheadid;
            }

            public String getDrug_name() {
                return drug_name == null ? "" : drug_name;
            }

            public void setDrug_name(String drug_name) {
                this.drug_name = drug_name;
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

            public int getDrug_num() {
                return drug_num;
            }

            public void setDrug_num(int drug_num) {
                this.drug_num = drug_num;
            }

            public Object getDrug_price() {
                return drug_price;
            }

            public void setDrug_price(Object drug_price) {
                this.drug_price = drug_price;
            }

            public Object getDrug_money() {
                return drug_money;
            }

            public void setDrug_money(Object drug_money) {
                this.drug_money = drug_money;
            }

            public Object getOne_num() {
                return one_num;
            }

            public void setOne_num(Object one_num) {
                this.one_num = one_num;
            }

            public Object getDay_num() {
                return day_num;
            }

            public void setDay_num(Object day_num) {
                this.day_num = day_num;
            }

            public Object getUse_note() {
                return use_note;
            }

            public void setUse_note(Object use_note) {
                this.use_note = use_note;
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

            public Object getDrug_img() {
                return drug_img;
            }

            public void setDrug_img(Object drug_img) {
                this.drug_img = drug_img;
            }

            public Object getDDH() {
                return DDH;
            }

            public void setDDH(Object DDH) {
                this.DDH = DDH;
            }

            public Object getCFname() {
                return CFname;
            }

            public void setCFname(Object CFname) {
                this.CFname = CFname;
            }

            public Object getThe_memo() {
                return the_memo;
            }

            public void setThe_memo(Object the_memo) {
                this.the_memo = the_memo;
            }

            public Object getIsPhoto() {
                return isPhoto;
            }

            public void setIsPhoto(Object isPhoto) {
                this.isPhoto = isPhoto;
            }
        }
    }
}
