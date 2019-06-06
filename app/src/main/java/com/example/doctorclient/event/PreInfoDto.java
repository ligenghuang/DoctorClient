package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class PreInfoDto {


    /**
     * code : 1
     * data : {"patienName":null,"askIUID":"747edd52-63c9-4ba3-aae1-330848c5c2ff","askdrugheadid":"3495f1c0-c609-4b60-8046-42e413c27dd4","userid":null,"patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":{"IUID":null,"userid":null,"name":"小李","sex":"","birt_date":null,"height":null,"weight":null,"phone":null,"relation":null,"allergy_note":"","med_history":"","med_family":"","create_time":null,"age":0,"med_drug":null},"the_img":null,"doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":null,"hospital":null,"diagnosis":"","departid":null,"ill_note":"电饭锅电饭锅","Ill_img":["/DOC/toChatWith//201932121723874p.png"],"doctor_money":null,"drug_money":198,"all_money":null,"drug_flag":null,"pay_money":0,"pay_class":null,"pay_flag":0,"start_time":null,"end_time":null,"create_time":"/Date(1558160844397)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":null,"isEval":0,"DrugMV":[{"IUID":"77","askDrugId":"35a1b6f2-efb2-4186-a5b4-eab1e45474a6","name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":"口服，一日1片0","bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":4,"pinyin":null,"py":null,"xq":null}],"brokerage":1.98,"agree_flag":0,"finish_flag":0,"reback_flag":0,"askdrug_no":"20190518142724397","Askdrug_img":[],"laveTime":null,"the_memo":"第三方士大夫士大夫aaa","departName":null,"create_time_stamp":1558160844397,"pay_time_stamp":1558160844397}
     * msg :
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
         * askIUID : 747edd52-63c9-4ba3-aae1-330848c5c2ff
         * askdrugheadid : 3495f1c0-c609-4b60-8046-42e413c27dd4
         * userid : null
         * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * PatientMV : {"IUID":null,"userid":null,"name":"小李","sex":"","birt_date":null,"height":null,"weight":null,"phone":null,"relation":null,"allergy_note":"","med_history":"","med_family":"","create_time":null,"age":0,"med_drug":null}
         * the_img : null
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * docUserId : null
         * doctorName : 李松
         * the_level : null
         * hospital : null
         * diagnosis :
         * departid : null
         * ill_note : 电饭锅电饭锅
         * Ill_img : ["/DOC/toChatWith//201932121723874p.png"]
         * doctor_money : null
         * drug_money : 198.0
         * all_money : null
         * drug_flag : null
         * pay_money : 0.0
         * pay_class : null
         * pay_flag : 0
         * start_time : null
         * end_time : null
         * create_time : /Date(1558160844397)/
         * pay_time : null
         * prescription_name : null
         * status_flag : null
         * ask_flag : null
         * isEval : 0
         * DrugMV : [{"IUID":"77","askDrugId":"35a1b6f2-efb2-4186-a5b4-eab1e45474a6","name":"小儿多维元素片(小儿善存片)","the_class":null,"otc_class":null,"the_company":null,"the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":null,"appear":null,"indication":null,"num_note":"口服，一日1片0","bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":4,"pinyin":null,"py":null,"xq":null}]
         * brokerage : 1.98
         * agree_flag : 0
         * finish_flag : 0
         * reback_flag : 0
         * askdrug_no : 20190518142724397
         * Askdrug_img : []
         * laveTime : null
         * the_memo : 第三方士大夫士大夫aaa
         * departName : null
         * create_time_stamp : 1558160844397
         * pay_time_stamp : 1558160844397
         */

        private Object patienName;
        private String askIUID;
        private String askdrugheadid;
        private Object userid;
        private String patientid;
        private PatientMVBean PatientMV;
        private Object the_img;
        private String doctorid;
        private Object docUserId;
        private String doctorName;
        private Object the_level;
        private Object hospital;
        private String diagnosis;
        private Object departid;
        private String ill_note;
        private Object doctor_money;
        private double drug_money;
        private Object all_money;
        private Object drug_flag;
        private double pay_money;
        private Object pay_class;
        private int pay_flag;
        private Object start_time;
        private Object end_time;
        private String create_time;
        private Object pay_time;
        private Object prescription_name;
        private Object status_flag;
        private Object ask_flag;
        private int isEval;
        private double brokerage;
        private int agree_flag;
        private int finish_flag;
        private int reback_flag;
        private String askdrug_no;
        private Object laveTime;
        private String the_memo;
        private Object departName;
        private long create_time_stamp;
        private long pay_time_stamp;
        private List<String> Ill_img;
        private List<DrugMVBean> DrugMV;
        private List<String> Askdrug_img;

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

        public String getAskdrugheadid() {
            return askdrugheadid == null ? "" : askdrugheadid;
        }

        public void setAskdrugheadid(String askdrugheadid) {
            this.askdrugheadid = askdrugheadid;
        }

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
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

        public Object getThe_img() {
            return the_img;
        }

        public void setThe_img(Object the_img) {
            this.the_img = the_img;
        }

        public String getDoctorid() {
            return doctorid == null ? "" : doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public Object getDocUserId() {
            return docUserId;
        }

        public void setDocUserId(Object docUserId) {
            this.docUserId = docUserId;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
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

        public String getDiagnosis() {
            return diagnosis == null ? "" : diagnosis;
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

        public Object getDoctor_money() {
            return doctor_money;
        }

        public void setDoctor_money(Object doctor_money) {
            this.doctor_money = doctor_money;
        }

        public double getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(double drug_money) {
            this.drug_money = drug_money;
        }

        public Object getAll_money() {
            return all_money;
        }

        public void setAll_money(Object all_money) {
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

        public Object getAsk_flag() {
            return ask_flag;
        }

        public void setAsk_flag(Object ask_flag) {
            this.ask_flag = ask_flag;
        }

        public int getIsEval() {
            return isEval;
        }

        public void setIsEval(int isEval) {
            this.isEval = isEval;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public int getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(int agree_flag) {
            this.agree_flag = agree_flag;
        }

        public int getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(int finish_flag) {
            this.finish_flag = finish_flag;
        }

        public int getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(int reback_flag) {
            this.reback_flag = reback_flag;
        }

        public String getAskdrug_no() {
            return askdrug_no == null ? "" : askdrug_no;
        }

        public void setAskdrug_no(String askdrug_no) {
            this.askdrug_no = askdrug_no;
        }

        public Object getLaveTime() {
            return laveTime;
        }

        public void setLaveTime(Object laveTime) {
            this.laveTime = laveTime;
        }

        public String getThe_memo() {
            return the_memo == null ? "" : the_memo;
        }

        public void setThe_memo(String the_memo) {
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

        public long getPay_time_stamp() {
            return pay_time_stamp;
        }

        public void setPay_time_stamp(long pay_time_stamp) {
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

        public List<DrugMVBean> getDrugMV() {
            if (DrugMV == null) {
                return new ArrayList<>();
            }
            return DrugMV;
        }

        public void setDrugMV(List<DrugMVBean> drugMV) {
            DrugMV = drugMV;
        }

        public List<String> getAskdrug_img() {
            if (Askdrug_img == null) {
                return new ArrayList<>();
            }
            return Askdrug_img;
        }

        public void setAskdrug_img(List<String> askdrug_img) {
            Askdrug_img = askdrug_img;
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
            private Object weight;
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
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
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

            public Object getWeight() {
                return weight;
            }

            public void setWeight(Object weight) {
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
                return allergy_note;
            }

            public void setAllergy_note(String allergy_note) {
                this.allergy_note = allergy_note;
            }

            public String getMed_history() {
                return med_history;
            }

            public void setMed_history(String med_history) {
                this.med_history = med_history;
            }

            public String getMed_family() {
                return med_family;
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

        public static class DrugMVBean {
            /**
             * IUID : 77
             * askDrugId : 35a1b6f2-efb2-4186-a5b4-eab1e45474a6
             * name : 小儿多维元素片(小儿善存片)
             * the_class : null
             * otc_class : null
             * the_company : null
             * the_spec : 60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶
             * element : null
             * appear : null
             * indication : null
             * num_note : 口服，一日1片0
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
             * drug_num : 4.0
             * pinyin : null
             * py : null
             * xq : null
             */

            private String IUID;
            private String askDrugId;
            private String name;
            private Object the_class;
            private Object otc_class;
            private Object the_company;
            private String the_spec;
            private Object element;
            private Object appear;
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
                return IUID;
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
                return name;
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
                return the_spec;
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

            public String getNum_note() {
                return num_note;
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
                return the_img;
            }

            public void setThe_img(String the_img) {
                this.the_img = the_img;
            }

            public Object getAccessid() {
                return Accessid;
            }

            public void setAccessid(Object Accessid) {
                this.Accessid = Accessid;
            }

            public Object getEndDate() {
                return EndDate;
            }

            public void setEndDate(Object EndDate) {
                this.EndDate = EndDate;
            }

            public Object getDateStart() {
                return DateStart;
            }

            public void setDateStart(Object DateStart) {
                this.DateStart = DateStart;
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
}
