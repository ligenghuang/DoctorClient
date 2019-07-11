package com.example.doctorclient.event;

public class WeiLoginDto {

    /**
     * code : 1
     * data : {"iuid":"bfc47664-e08b-40ed-93b0-bb462c14654f","userid":"630089","phome":null,"e_mail":null,"password":"D41D8CD98F0B24E980998ECF842","transactionpwd":null,"actualname":null,"idnumber":null,"isverified":0,"isphome":false,"ise_mail":false,"isgoogle":false,"invitationcode":"97004064","superiorID":null,"registrationTime":"/Date(1562727329517)/","inputfile":null,"inputfile1":null,"inputfile2":null,"verifiedRemarks":null,"the_class":null,"the_point":null,"sys_flag":null,"nicename":"🙈","coin_address":null,"capitalPassword":null,"niceImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/KtzDMKVegNHiblu7syicMJttrWJuxK3YTuwpGpiaibBMhUIN2Zer0g7sUCf2nxXwlso3BHhQq0bzLia8ia8MES3WtwXQ/132","isMineOwner":null,"proportion":null,"frzzez":null,"the_date":null,"assets":null,"url":null,"XXcount":null,"vip_star":null,"Accessid":0,"healthCoin":null,"openId":null,"unionid":"oz_VS1WdC7pTXDnQH5jo2ldb7fFo"}
     * msg : 登录成功
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
         * iuid : bfc47664-e08b-40ed-93b0-bb462c14654f
         * userid : 630089
         * phome : null
         * e_mail : null
         * password : D41D8CD98F0B24E980998ECF842
         * transactionpwd : null
         * actualname : null
         * idnumber : null
         * isverified : 0
         * isphome : false
         * ise_mail : false
         * isgoogle : false
         * invitationcode : 97004064
         * superiorID : null
         * registrationTime : /Date(1562727329517)/
         * inputfile : null
         * inputfile1 : null
         * inputfile2 : null
         * verifiedRemarks : null
         * the_class : null
         * the_point : null
         * sys_flag : null
         * nicename : 🙈
         * coin_address : null
         * capitalPassword : null
         * niceImg : http://thirdwx.qlogo.cn/mmopen/vi_32/KtzDMKVegNHiblu7syicMJttrWJuxK3YTuwpGpiaibBMhUIN2Zer0g7sUCf2nxXwlso3BHhQq0bzLia8ia8MES3WtwXQ/132
         * isMineOwner : null
         * proportion : null
         * frzzez : null
         * the_date : null
         * assets : null
         * url : null
         * XXcount : null
         * vip_star : null
         * Accessid : 0
         * healthCoin : null
         * openId : null
         * unionid : oz_VS1WdC7pTXDnQH5jo2ldb7fFo
         */

        private String iuid;
        private String userid;
        private String phome;
        private Object e_mail;
        private String password;
        private Object transactionpwd;
        private Object actualname;
        private Object idnumber;
        private int isverified;
        private boolean isphome;
        private boolean ise_mail;
        private boolean isgoogle;
        private String invitationcode;
        private Object superiorID;
        private String registrationTime;
        private Object inputfile;
        private Object inputfile1;
        private Object inputfile2;
        private Object verifiedRemarks;
        private Object the_class;
        private Object the_point;
        private Object sys_flag;
        private String nicename;
        private Object coin_address;
        private Object capitalPassword;
        private String niceImg;
        private Object isMineOwner;
        private Object proportion;
        private Object frzzez;
        private Object the_date;
        private Object assets;
        private Object url;
        private Object XXcount;
        private Object vip_star;
        private int Accessid;
        private Object healthCoin;
        private Object openId;
        private String unionid;

        public String getIuid() {
            return iuid == null ? "" : iuid;
        }

        public void setIuid(String iuid) {
            this.iuid = iuid;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPhome() {
            return phome == null ? "" : phome;
        }

        public void setPhome(String phome) {
            this.phome = phome;
        }

        public Object getE_mail() {
            return e_mail;
        }

        public void setE_mail(Object e_mail) {
            this.e_mail = e_mail;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getTransactionpwd() {
            return transactionpwd;
        }

        public void setTransactionpwd(Object transactionpwd) {
            this.transactionpwd = transactionpwd;
        }

        public Object getActualname() {
            return actualname;
        }

        public void setActualname(Object actualname) {
            this.actualname = actualname;
        }

        public Object getIdnumber() {
            return idnumber;
        }

        public void setIdnumber(Object idnumber) {
            this.idnumber = idnumber;
        }

        public int getIsverified() {
            return isverified;
        }

        public void setIsverified(int isverified) {
            this.isverified = isverified;
        }

        public boolean isphome() {
            return isphome;
        }

        public void setIsphome(boolean isphome) {
            this.isphome = isphome;
        }

        public boolean ise_mail() {
            return ise_mail;
        }

        public void setIse_mail(boolean ise_mail) {
            this.ise_mail = ise_mail;
        }

        public boolean isgoogle() {
            return isgoogle;
        }

        public void setIsgoogle(boolean isgoogle) {
            this.isgoogle = isgoogle;
        }

        public String getInvitationcode() {
            return invitationcode == null ? "" : invitationcode;
        }

        public void setInvitationcode(String invitationcode) {
            this.invitationcode = invitationcode;
        }

        public Object getSuperiorID() {
            return superiorID;
        }

        public void setSuperiorID(Object superiorID) {
            this.superiorID = superiorID;
        }

        public String getRegistrationTime() {
            return registrationTime == null ? "" : registrationTime;
        }

        public void setRegistrationTime(String registrationTime) {
            this.registrationTime = registrationTime;
        }

        public Object getInputfile() {
            return inputfile;
        }

        public void setInputfile(Object inputfile) {
            this.inputfile = inputfile;
        }

        public Object getInputfile1() {
            return inputfile1;
        }

        public void setInputfile1(Object inputfile1) {
            this.inputfile1 = inputfile1;
        }

        public Object getInputfile2() {
            return inputfile2;
        }

        public void setInputfile2(Object inputfile2) {
            this.inputfile2 = inputfile2;
        }

        public Object getVerifiedRemarks() {
            return verifiedRemarks;
        }

        public void setVerifiedRemarks(Object verifiedRemarks) {
            this.verifiedRemarks = verifiedRemarks;
        }

        public Object getThe_class() {
            return the_class;
        }

        public void setThe_class(Object the_class) {
            this.the_class = the_class;
        }

        public Object getThe_point() {
            return the_point;
        }

        public void setThe_point(Object the_point) {
            this.the_point = the_point;
        }

        public Object getSys_flag() {
            return sys_flag;
        }

        public void setSys_flag(Object sys_flag) {
            this.sys_flag = sys_flag;
        }

        public String getNicename() {
            return nicename == null ? "" : nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public Object getCoin_address() {
            return coin_address;
        }

        public void setCoin_address(Object coin_address) {
            this.coin_address = coin_address;
        }

        public Object getCapitalPassword() {
            return capitalPassword;
        }

        public void setCapitalPassword(Object capitalPassword) {
            this.capitalPassword = capitalPassword;
        }

        public String getNiceImg() {
            return niceImg == null ? "" : niceImg;
        }

        public void setNiceImg(String niceImg) {
            this.niceImg = niceImg;
        }

        public Object getIsMineOwner() {
            return isMineOwner;
        }

        public void setIsMineOwner(Object isMineOwner) {
            this.isMineOwner = isMineOwner;
        }

        public Object getProportion() {
            return proportion;
        }

        public void setProportion(Object proportion) {
            this.proportion = proportion;
        }

        public Object getFrzzez() {
            return frzzez;
        }

        public void setFrzzez(Object frzzez) {
            this.frzzez = frzzez;
        }

        public Object getThe_date() {
            return the_date;
        }

        public void setThe_date(Object the_date) {
            this.the_date = the_date;
        }

        public Object getAssets() {
            return assets;
        }

        public void setAssets(Object assets) {
            this.assets = assets;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public Object getXXcount() {
            return XXcount;
        }

        public void setXXcount(Object XXcount) {
            this.XXcount = XXcount;
        }

        public Object getVip_star() {
            return vip_star;
        }

        public void setVip_star(Object vip_star) {
            this.vip_star = vip_star;
        }

        public int getAccessid() {
            return Accessid;
        }

        public void setAccessid(int accessid) {
            Accessid = accessid;
        }

        public Object getHealthCoin() {
            return healthCoin;
        }

        public void setHealthCoin(Object healthCoin) {
            this.healthCoin = healthCoin;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public String getUnionid() {
            return unionid == null ? "" : unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }
    }
}
