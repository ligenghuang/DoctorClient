package com.example.doctorclient.event.post;
/**
* 注册请求体
* @author lgh
* created at 2019/5/14 0014 10:57
*/
public class RegisteredPost {

    private String type;
    private String doctorNmae;
    private String userName;
    private String password;
    private String sms_code;
    private String sex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoctorNmae() {
        return doctorNmae;
    }

    public void setDoctorNmae(String doctorNmae) {
        this.doctorNmae = doctorNmae;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
