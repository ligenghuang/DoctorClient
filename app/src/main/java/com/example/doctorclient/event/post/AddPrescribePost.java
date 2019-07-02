package com.example.doctorclient.event.post;

import java.util.ArrayList;
import java.util.List;

public class AddPrescribePost {

    private String type;
    private String askdrugheadid;
    private String askIuid;
    private String the_memo;
    private String diagnosis;
    private List<String> theImg;
    private List<DrugBean> mycars;

    public String getDiagnosis() {
        return diagnosis == null ? "" : diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAskdrugheadid() {
        return askdrugheadid == null ? "" : askdrugheadid;
    }

    public void setAskdrugheadid(String askdrugheadid) {
        this.askdrugheadid = askdrugheadid;
    }

    public String getAskIuid() {
        return askIuid == null ? "" : askIuid;
    }

    public void setAskIuid(String askIuid) {
        this.askIuid = askIuid;
    }

    public String getThe_memo() {
        return the_memo == null ? "" : the_memo;
    }

    public void setThe_memo(String the_memo) {
        this.the_memo = the_memo;
    }

    public List<String> getTheImg() {
        if (theImg == null) {
            return new ArrayList<>();
        }
        return theImg;
    }

    public void setTheImg(List<String> theImg) {
        this.theImg = theImg;
    }

    public List<DrugBean> getMycars() {
        if (mycars == null) {
            return new ArrayList<>();
        }
        return mycars;
    }

    public void setMycars(List<DrugBean> mycars) {
        this.mycars = mycars;
    }

    public static class DrugBean{
        private String IUID;
        private  String drug_num;
        private String use_note;
        private String drugid;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getDrug_num() {
            return drug_num == null ? "" : drug_num;
        }

        public void setDrug_num(String drug_num) {
            this.drug_num = drug_num;
        }

        public String getUse_note() {
            return use_note == null ? "" : use_note;
        }

        public void setUse_note(String use_note) {
            this.use_note = use_note;
        }

        public String getDrugid() {
            return drugid == null ? "" : drugid;
        }

        public void setDrugid(String drugid) {
            this.drugid = drugid;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"IUID\":\"")
                    .append(getIUID()).append('\"');
            sb.append(",\"drug_num\":\"")
                    .append(drug_num).append('\"');
            sb.append(",\"use_note\":\"")
                    .append(use_note).append('\"');
            sb.append(",\"drugid\":\"")
                    .append(drugid).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }
}
