package com.example.doctorclient.event;

import java.util.List;

public class MyInquiryDto {


    /**
     * code : 1
     * data : [{"patienName":"小李","askIUID":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","askdrugheadid":null,"userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":null,"doctorid":null,"docUserId":null,"doctorName":null,"the_level":null,"hospital":null,"diagnosis":null,"departid":null,"ill_note":"dfdsf","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":11,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":7.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"747edd52-63c9-4ba3-aae1-330848c5c2ff","askdrugheadid":null,"userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":null,"doctorid":null,"docUserId":null,"doctorName":null,"the_level":null,"hospital":null,"diagnosis":null,"departid":null,"ill_note":"电饭锅电饭锅","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":11,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":0,"isEval":0,"DrugMV":null,"brokerage":7.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"b8bae022-835c-415a-8e4a-035aaac8ee41","askdrugheadid":null,"userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":null,"doctorid":null,"docUserId":null,"doctorName":null,"the_level":null,"hospital":null,"diagnosis":null,"departid":null,"ill_note":"转账支持下","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":2,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":3,"isEval":0,"DrugMV":null,"brokerage":1.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"b8bae022-835c-415a-8e4a-035aaac8ee42","askdrugheadid":null,"userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":null,"doctorid":null,"docUserId":null,"doctorName":null,"the_level":null,"hospital":null,"diagnosis":null,"departid":null,"ill_note":"撒的撒打算","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":2,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":1.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0}]
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
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * patienName : 小李
         * askIUID : 6f9a643d-d170-41dc-b439-acab7f2d5bd7
         * askdrugheadid : null
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * PatientMV : null
         * the_img : null
         * doctorid : null
         * docUserId : null
         * doctorName : null
         * the_level : null
         * hospital : null
         * diagnosis : null
         * departid : null
         * ill_note : dfdsf
         * Ill_img : null
         * doctor_money : null
         * drug_money : null
         * all_money : null
         * drug_flag : null
         * pay_money : 11.0
         * pay_class : null
         * pay_flag : 1
         * start_time : null
         * end_time : null
         * create_time : /Date(1551670008920)/
         * pay_time : null
         * prescription_name : null
         * status_flag : null
         * ask_flag : 2
         * isEval : 0
         * DrugMV : null
         * brokerage : 7.7
         * agree_flag : null
         * finish_flag : null
         * reback_flag : null
         * askdrug_no : null
         * Askdrug_img : null
         * laveTime : null
         * the_memo : null
         * departName : null
         * create_time_stamp : 1551670008920
         * pay_time_stamp : 0
         */

        private String patienName;
        private String askIUID;
        private Object askdrugheadid;
        private String userid;
        private String patientid;
        private Object PatientMV;
        private String the_img;
        private Object doctorid;
        private Object docUserId;
        private Object doctorName;
        private Object the_level;
        private Object hospital;
        private Object diagnosis;
        private Object departid;
        private String ill_note;
        private Object Ill_img;
        private Object doctor_money;
        private Object drug_money;
        private Object all_money;
        private int drug_flag;
        private double pay_money;
        private Object pay_class;
        private int pay_flag;
        private Object start_time;
        private Object end_time;
        private String create_time;
        private Object pay_time;
        private Object prescription_name;
        private Object status_flag;
        private int ask_flag;
        private int isEval;
        private Object DrugMV;
        private double brokerage;
        private Object agree_flag;
        private Object finish_flag;
        private Object reback_flag;
        private Object askdrug_no;
        private Object Askdrug_img;
        private Object laveTime;
        private Object the_memo;
        private Object departName;
        private long create_time_stamp;
        private int pay_time_stamp;

        public String getPatienName() {
            return patienName;
        }

        public void setPatienName(String patienName) {
            this.patienName = patienName;
        }

        public String getAskIUID() {
            return askIUID;
        }

        public void setAskIUID(String askIUID) {
            this.askIUID = askIUID;
        }

        public Object getAskdrugheadid() {
            return askdrugheadid;
        }

        public void setAskdrugheadid(Object askdrugheadid) {
            this.askdrugheadid = askdrugheadid;
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

        public Object getPatientMV() {
            return PatientMV;
        }

        public void setPatientMV(Object PatientMV) {
            this.PatientMV = PatientMV;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public Object getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(Object doctorid) {
            this.doctorid = doctorid;
        }

        public Object getDocUserId() {
            return docUserId;
        }

        public void setDocUserId(Object docUserId) {
            this.docUserId = docUserId;
        }

        public Object getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(Object doctorName) {
            this.doctorName = doctorName;
        }

        public Object getThe_level() {
            return the_level;
        }

        public void setThe_level(Object the_level) {
            this.the_level = the_level;
        }

        public Object getHospital() {
            return hospital;
        }

        public void setHospital(Object hospital) {
            this.hospital = hospital;
        }

        public Object getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(Object diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
        }

        public String getIll_note() {
            return ill_note;
        }

        public void setIll_note(String ill_note) {
            this.ill_note = ill_note;
        }

        public Object getIll_img() {
            return Ill_img;
        }

        public void setIll_img(Object Ill_img) {
            this.Ill_img = Ill_img;
        }

        public Object getDoctor_money() {
            return doctor_money;
        }

        public void setDoctor_money(Object doctor_money) {
            this.doctor_money = doctor_money;
        }

        public Object getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(Object drug_money) {
            this.drug_money = drug_money;
        }

        public Object getAll_money() {
            return all_money;
        }

        public void setAll_money(Object all_money) {
            this.all_money = all_money;
        }

        public int getDrug_flag() {
            return drug_flag;
        }

        public void setDrug_flag(int drug_flag) {
            this.drug_flag = drug_flag;
        }

        public double getPay_money() {
            return pay_money;
        }

        public void setPay_money(double pay_money) {
            this.pay_money = pay_money;
        }

        public Object getPay_class() {
            return pay_class;
        }

        public void setPay_class(Object pay_class) {
            this.pay_class = pay_class;
        }

        public int getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(int pay_flag) {
            this.pay_flag = pay_flag;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getPay_time() {
            return pay_time;
        }

        public void setPay_time(Object pay_time) {
            this.pay_time = pay_time;
        }

        public Object getPrescription_name() {
            return prescription_name;
        }

        public void setPrescription_name(Object prescription_name) {
            this.prescription_name = prescription_name;
        }

        public Object getStatus_flag() {
            return status_flag;
        }

        public void setStatus_flag(Object status_flag) {
            this.status_flag = status_flag;
        }

        public int getAsk_flag() {
            return ask_flag;
        }

        public void setAsk_flag(int ask_flag) {
            this.ask_flag = ask_flag;
        }

        public int getIsEval() {
            return isEval;
        }

        public void setIsEval(int isEval) {
            this.isEval = isEval;
        }

        public Object getDrugMV() {
            return DrugMV;
        }

        public void setDrugMV(Object DrugMV) {
            this.DrugMV = DrugMV;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
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

        public Object getAskdrug_no() {
            return askdrug_no;
        }

        public void setAskdrug_no(Object askdrug_no) {
            this.askdrug_no = askdrug_no;
        }

        public Object getAskdrug_img() {
            return Askdrug_img;
        }

        public void setAskdrug_img(Object Askdrug_img) {
            this.Askdrug_img = Askdrug_img;
        }

        public Object getLaveTime() {
            return laveTime;
        }

        public void setLaveTime(Object laveTime) {
            this.laveTime = laveTime;
        }

        public Object getThe_memo() {
            return the_memo;
        }

        public void setThe_memo(Object the_memo) {
            this.the_memo = the_memo;
        }

        public Object getDepartName() {
            return departName;
        }

        public void setDepartName(Object departName) {
            this.departName = departName;
        }

        public long getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(long create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public int getPay_time_stamp() {
            return pay_time_stamp;
        }

        public void setPay_time_stamp(int pay_time_stamp) {
            this.pay_time_stamp = pay_time_stamp;
        }
    }
}
