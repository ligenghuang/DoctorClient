package com.example.doctorclient.event;

public class RoogIMDto {

    /**
     * code : 200
     * userId : jlk456j5
     * token : sfd9823ihufi
     */

    private int code;
    private String userId;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId == null ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"userId\":\'" + userId + "\'" +
                ", \"token\":\'" + token + "\'" +
                '}';
    }
}
