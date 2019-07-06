package com.example.doctorclient.event.post;

public class DoctorsInfoPost {

    private String file;
    private String practicing_img;
    private String name;
    private String sex;
    private String practicing_time;
    private String the_level;
    private String the_spec;
    private String departName;
    private String hospital;
    private int isPrescribe;
    private String phone;
    private String the_note;

    public String getThe_note() {
        return the_note == null ? "" : the_note;
    }

    public void setThe_note(String the_note) {
        this.the_note = the_note;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPracticing_img() {
        return practicing_img;
    }

    public void setPracticing_img(String practicing_img) {
        this.practicing_img = practicing_img;
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

    public String getPracticing_time() {
        return practicing_time;
    }

    public void setPracticing_time(String practicing_time) {
        this.practicing_time = practicing_time;
    }

    public String getThe_level() {
        return the_level;
    }

    public void setThe_level(String the_level) {
        this.the_level = the_level;
    }

    public String getThe_spec() {
        return the_spec;
    }

    public void setThe_spec(String the_spec) {
        this.the_spec = the_spec;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getIsPrescribe() {
        return isPrescribe;
    }

    public void setIsPrescribe(int isPrescribe) {
        this.isPrescribe = isPrescribe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
