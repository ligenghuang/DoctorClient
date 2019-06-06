package com.example.doctorclient.event.post;

import java.util.ArrayList;
import java.util.List;

public class DrugSavePost {

    private String type;
    private String iuid;
    private String name;
    private String depart;
    private String the_memo;
    private List<DrugBean> mycars;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIuid() {
        return iuid == null ? "" : iuid;
    }

    public void setIuid(String iuid) {
        this.iuid = iuid;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart == null ? "" : depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getThe_memo() {
        return the_memo == null ? "" : the_memo;
    }

    public void setThe_memo(String the_memo) {
        this.the_memo = the_memo;
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"iuid\":\"")
                .append(iuid).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"depart\":\"")
                .append(depart).append('\"');
        sb.append(",\"the_memo\":\"")
                .append(the_memo).append('\"');
        sb.append(",\"mycars\":")
                .append(mycars);
        sb.append('}');
        return sb.toString();
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
            return "{" +
                    "\"IUID\":\'" + IUID + "\'" +
                    ", \"drug_num\":\'" + drug_num + "\'" +
                    ", \"use_note\":\'" + use_note + "\'" +
                    ", \"drugid\":\'" + drugid + "\'" +
                    '}';
        }
    }
}
