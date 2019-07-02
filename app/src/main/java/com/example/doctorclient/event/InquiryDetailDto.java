package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class InquiryDetailDto {


    /**
     * code : 1
     * data : {"patienName":null,"askIUID":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","askdrugheadid":null,"userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":{"IUID":null,"userid":null,"name":"小李","sex":"","birt_date":null,"height":null,"weight":null,"phone":null,"relation":null,"allergy_note":"","med_history":"","med_family":"","create_time":null,"age":0,"med_drug":null},"the_img":"/DOC/18529250717/2019516144849376j.jpg","doctorid":null,"docUserId":"9884b9cd-2222-40ee-8859-e6878b0081f1","doctorName":"李松","the_level":"执业医师","hospital":"广州中医院大学附属医院","diagnosis":null,"departid":null,"ill_note":"dfdsf","Ill_img":["/DOC/toChatWith//2019312123132397j.jpg"],"doctor_money":11,"drug_money":null,"all_money":11,"drug_flag":null,"pay_money":11,"pay_class":null,"pay_flag":null,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":7.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":"74天","the_memo":null,"departName":null,"create_time_stamp":0,"pay_time_stamp":0}
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
         * patienName : null
         * askIUID : 6f9a643d-d170-41dc-b439-acab7f2d5bd7
         * askdrugheadid : null
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * PatientMV : {"IUID":null,"userid":null,"name":"小李","sex":"","birt_date":null,"height":null,"weight":null,"phone":null,"relation":null,"allergy_note":"","med_history":"","med_family":"","create_time":null,"age":0,"med_drug":null}
         * the_img : /DOC/18529250717/2019516144849376j.jpg
         * doctorid : null
         * docUserId : 9884b9cd-2222-40ee-8859-e6878b0081f1
         * doctorName : 李松
         * the_level : 执业医师
         * hospital : 广州中医院大学附属医院
         * diagnosis : null
         * departid : null
         * ill_note : dfdsf
         * Ill_img : ["/DOC/toChatWith//2019312123132397j.jpg"]
         * doctor_money : 11.0
         * drug_money : null
         * all_money : 11.0
         * drug_flag : null
         * pay_money : 11.0
         * pay_class : null
         * pay_flag : null
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
         * laveTime : 74天
         * the_memo : null
         * departName : null
         * create_time_stamp : 0
         * pay_time_stamp : 0
         */

        private Object patienName;
        private String askIUID;
        private Object askdrugheadid;
        private String userid;
        private String patientid;
        private PatientMVBean PatientMV;
        private String the_img;
        private Object doctorid;
        private String docUserId;
        private String doctorName;
        private String the_level;
        private String hospital;
        private String diagnosis;
        private Object departid;
        private String ill_note;
        private double doctor_money;
        private Object drug_money;
        private double all_money;
        private Object drug_flag;
        private double pay_money;
        private Object pay_class;
        private Object pay_flag;
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
        private String laveTime;
        private Object the_memo;
        private Object departName;
        private int create_time_stamp;
        private int pay_time_stamp;
        private List<String> Ill_img;

        public Object getPatienName() {
            return patienName;
        }

        public void setPatienName(Object patienName) {
            this.patienName = patienName;
        }

        public String getAskIUID() {
            return askIUID == null ? "" : askIUID;
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

        public PatientMVBean getPatientMV() {
            return PatientMV;
        }

        public void setPatientMV(PatientMVBean patientMV) {
            PatientMV = patientMV;
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

        public String getDocUserId() {
            return docUserId == null ? "" : docUserId;
        }

        public void setDocUserId(String docUserId) {
            this.docUserId = docUserId;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getThe_level() {
            return the_level == null ? "" : the_level;
        }

        public void setThe_level(String the_level) {
            this.the_level = the_level;
        }

        public String getHospital() {
            return hospital == null ? "" : hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getDiagnosis() {
            return diagnosis == null ? "无" : diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
        }

        public String getIll_note() {
            return ill_note == null ? "" : ill_note;
        }

        public void setIll_note(String ill_note) {
            this.ill_note = ill_note;
        }

        public double getDoctor_money() {
            return doctor_money;
        }

        public void setDoctor_money(double doctor_money) {
            this.doctor_money = doctor_money;
        }

        public Object getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(Object drug_money) {
            this.drug_money = drug_money;
        }

        public double getAll_money() {
            return all_money;
        }

        public void setAll_money(double all_money) {
            this.all_money = all_money;
        }

        public Object getDrug_flag() {
            return drug_flag;
        }

        public void setDrug_flag(Object drug_flag) {
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

        public Object getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(Object pay_flag) {
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
            return create_time == null ? "" : create_time;
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

        public void setDrugMV(Object drugMV) {
            DrugMV = drugMV;
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

        public void setAskdrug_img(Object askdrug_img) {
            Askdrug_img = askdrug_img;
        }

        public String getLaveTime() {
            return laveTime == null ? "" : laveTime;
        }

        public void setLaveTime(String laveTime) {
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

        public int getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(int create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public int getPay_time_stamp() {
            return pay_time_stamp;
        }

        public void setPay_time_stamp(int pay_time_stamp) {
            this.pay_time_stamp = pay_time_stamp;
        }

        public List<String> getIll_img() {
            if (Ill_img == null) {
                return new ArrayList<>();
            }
            return Ill_img;
        }

        public void setIll_img(List<String> ill_img) {
            Ill_img = ill_img;
        }

        public static class PatientMVBean {
            /**
             * IUID : null
             * userid : null
             * name : 小李
             * sex :
             * birt_date : null
             * height : null
             * weight : null
             * phone : null
             * relation : null
             * allergy_note :
             * med_history :
             * med_family :
             * create_time : null
             * age : 0
             * med_drug : null
             */

            private Object IUID;
            private Object userid;
            private String name;
            private String sex;
            private Object birt_date;
            private Object height;
            private String weight;
            private Object phone;
            private Object relation;
            private String allergy_note;
            private String med_history;
            private String med_family;
            private Object create_time;
            private int age;
            private Object med_drug;

            public Object getIUID() {
                return IUID;
            }

            public void setIUID(Object IUID) {
                this.IUID = IUID;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex == null ? "" : sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getBirt_date() {
                return birt_date;
            }

            public void setBirt_date(Object birt_date) {
                this.birt_date = birt_date;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }

            public String getWeight() {
                return weight == null ? "" : weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getRelation() {
                return relation;
            }

            public void setRelation(Object relation) {
                this.relation = relation;
            }

            public String getAllergy_note() {
                return allergy_note == null ? "" : allergy_note;
            }

            public void setAllergy_note(String allergy_note) {
                this.allergy_note = allergy_note;
            }

            public String getMed_history() {
                return med_history == null ? "" : med_history;
            }

            public void setMed_history(String med_history) {
                this.med_history = med_history;
            }

            public String getMed_family() {
                return med_family == null ? "" : med_family;
            }

            public void setMed_family(String med_family) {
                this.med_family = med_family;
            }

            public Object getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Object create_time) {
                this.create_time = create_time;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public Object getMed_drug() {
                return med_drug;
            }

            public void setMed_drug(Object med_drug) {
                this.med_drug = med_drug;
            }
        }
    }
}
