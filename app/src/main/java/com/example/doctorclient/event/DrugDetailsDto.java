package com.example.doctorclient.event;

public class DrugDetailsDto {


    /**
     * code : 1
     * data : {"IUID":"77","askDrugId":null,"name":"小儿多维元素片(小儿善存片)","the_class":"薄膜衣片","otc_class":"非处方药","the_company":"惠氏制药有限公司","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","element":"本品每片主要成份及含量为：\n维生素A 5000国际单位 维生素C 50毫克\n维生素D 400国际单位 叶酸 100微克\n维生素B1 1.5毫克 烟酰胺 20毫克\n维生素B2 1.7毫克 泛酸 10毫克\n维生素B6 2毫克 钙 162毫克\n维生素B12 4微克 磷 125毫克\n","appear":"善存片为薄膜衣片","indication":"用于3-12岁儿童维生素和矿物质的补充。","num_note":"口服，一日1片","bad_effect":"偶见胃部不适。","bad_no":"无","attention_note":"1.严格按规定的剂量服用，需要大量服用时请咨询医师或药师。\n2.慢性肾功能衰竭，高钙血症，高磷血症伴肾性佝偻病患者禁用。\n3.如服用过量或出现严重不良反应请立即就医。\n4.当本品性状发生改变时禁止服用。\n5.请将此药品放在儿童不能接触的地方。","drug_response":"抗酸药可影响本品中维生素A的吸收，故不应同服。\n不应与含有大量镁，钙的药物合用，以免引起高镁，高钙血症。\n如正在服用其他药品时，使用本品前请咨询医师或药师。\n","theory":"维生素和矿物质均为维持机体正常代谢和身体健康必不可少的重要物质。二者是构成多种辅酶和激素的重要成分，缺乏时可导致代谢障碍，而引致多种疾病。","keep_note":"遮光，密闭保存。","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":"/Date(1543766400000)/","brokerage":0.01,"price":49.5,"drug_num":null,"pinyin":null,"py":null,"xq":null}
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
         * IUID : 77
         * askDrugId : null
         * name : 小儿多维元素片(小儿善存片)
         * the_class : 薄膜衣片
         * otc_class : 非处方药
         * the_company : 惠氏制药有限公司
         * the_spec : 60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶
         * element : 本品每片主要成份及含量为：
         维生素A 5000国际单位 维生素C 50毫克
         维生素D 400国际单位 叶酸 100微克
         维生素B1 1.5毫克 烟酰胺 20毫克
         维生素B2 1.7毫克 泛酸 10毫克
         维生素B6 2毫克 钙 162毫克
         维生素B12 4微克 磷 125毫克
         * appear : 善存片为薄膜衣片
         * indication : 用于3-12岁儿童维生素和矿物质的补充。
         * num_note : 口服，一日1片
         * bad_effect : 偶见胃部不适。
         * bad_no : 无
         * attention_note : 1.严格按规定的剂量服用，需要大量服用时请咨询医师或药师。
         2.慢性肾功能衰竭，高钙血症，高磷血症伴肾性佝偻病患者禁用。
         3.如服用过量或出现严重不良反应请立即就医。
         4.当本品性状发生改变时禁止服用。
         5.请将此药品放在儿童不能接触的地方。
         * drug_response : 抗酸药可影响本品中维生素A的吸收，故不应同服。
         不应与含有大量镁，钙的药物合用，以免引起高镁，高钙血症。
         如正在服用其他药品时，使用本品前请咨询医师或药师。
         * theory : 维生素和矿物质均为维持机体正常代谢和身体健康必不可少的重要物质。二者是构成多种辅酶和激素的重要成分，缺乏时可导致代谢障碍，而引致多种疾病。
         * keep_note : 遮光，密闭保存。
         * the_img : /DOC/prescription/img/editorial_prescription_imgs.png
         * Accessid : null
         * EndDate : null
         * DateStart : null
         * create_time : /Date(1543766400000)/
         * brokerage : 0.01
         * price : 49.5
         * drug_num : null
         * pinyin : null
         * py : null
         * xq : null
         */

        private String IUID;
        private Object askDrugId;
        private String name;
        private String the_class;
        private String otc_class;
        private String the_company;
        private String the_spec;
        private String element;
        private String appear;
        private String indication;
        private String num_note;
        private String bad_effect;
        private String bad_no;
        private String attention_note;
        private String drug_response;
        private String theory;
        private String keep_note;
        private String the_img;
        private Object Accessid;
        private Object EndDate;
        private Object DateStart;
        private String create_time;
        private double brokerage;
        private double price;
        private Object drug_num;
        private Object pinyin;
        private Object py;
        private Object xq;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
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

        public String getThe_class() {
            return the_class == null ? "" : the_class;
        }

        public void setThe_class(String the_class) {
            this.the_class = the_class;
        }

        public String getOtc_class() {
            return otc_class == null ? "" : otc_class;
        }

        public void setOtc_class(String otc_class) {
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

        public String getIndication() {
            return indication == null ? "" : indication;
        }

        public void setIndication(String indication) {
            this.indication = indication;
        }

        public String getNum_note() {
            return num_note == null ? "" : num_note;
        }

        public void setNum_note(String num_note) {
            this.num_note = num_note;
        }

        public String getBad_effect() {
            return bad_effect == null ? "" : bad_effect;
        }

        public void setBad_effect(String bad_effect) {
            this.bad_effect = bad_effect;
        }

        public String getBad_no() {
            return bad_no == null ? "" : bad_no;
        }

        public void setBad_no(String bad_no) {
            this.bad_no = bad_no;
        }

        public String getAttention_note() {
            return attention_note == null ? "" : attention_note;
        }

        public void setAttention_note(String attention_note) {
            this.attention_note = attention_note;
        }

        public String getDrug_response() {
            return drug_response == null ? "" : drug_response;
        }

        public void setDrug_response(String drug_response) {
            this.drug_response = drug_response;
        }

        public String getTheory() {
            return theory == null ? "" : theory;
        }

        public void setTheory(String theory) {
            this.theory = theory;
        }

        public String getKeep_note() {
            return keep_note == null ? "" : keep_note;
        }

        public void setKeep_note(String keep_note) {
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

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getDrug_num() {
            return drug_num;
        }

        public void setDrug_num(Object drug_num) {
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
