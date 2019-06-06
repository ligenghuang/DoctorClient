package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class CommonLanguageListDto {

    /**
     * code : 1
     * data : [{"iuid":"3","userId":null,"textContent":"医生医生医生","term_type":1,"create_time":null},{"iuid":"4","userId":null,"textContent":"医院医院医院医院","term_type":1,"create_time":null},{"iuid":"b3176b99-6183-4ba9-b88d-51011b39ff5b","userId":"9884b9cd-2222-40ee-8859-e6878b0081f1","textContent":"哒哒哒","term_type":2,"create_time":"/Date(1559099528587)/"}]
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
         * iuid : 3
         * userId : null
         * textContent : 医生医生医生
         * term_type : 1
         * create_time : null
         */

        private String iuid;
        private Object userId;
        private String textContent;
        private int term_type;
        private Object create_time;

        public String getIuid() {
            return iuid == null ? "" : iuid;
        }

        public void setIuid(String iuid) {
            this.iuid = iuid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getTextContent() {
            return textContent == null ? "" : textContent;
        }

        public void setTextContent(String textContent) {
            this.textContent = textContent;
        }

        public int getTerm_type() {
            return term_type;
        }

        public void setTerm_type(int term_type) {
            this.term_type = term_type;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }
    }
}
