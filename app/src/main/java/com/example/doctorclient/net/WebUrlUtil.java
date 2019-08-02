package com.example.doctorclient.net;


import com.example.doctorclient.BuildConfig;

public class WebUrlUtil {
    static {
        //配合retrofit，需要以/结尾
        if (BuildConfig.DEBUG) {
            BASE_URL = "http://192.168.1.62:8014/";
            IMG_URL = "http://192.168.1.62:8014";
//            BASE_URL = "http://api.yizhitong100.com/";
//            IMG_URL = "http://api.yizhitong100.com";
        } else {
            BASE_URL = "http://192.168.1.62:8014/";
            IMG_URL = "http://192.168.1.62:8014";
//            BASE_URL = "http://api.yizhitong100.com/";
//            IMG_URL = "http://api.yizhitong100.com";
        }
    }

    public static String BASE_URL;
    public static String IMG_URL;
    public static String SOCKET = "wss://wss.tongdiandashan.com";

    /**
     * 登录
     */
    public static final String POST_LOGIN = "Mine/LoginAction";

    /**
     * 注册
     */
    public static final String POST_REGISTER = "DMine/RegisterApp";

    /**
     * 获取验证码(图片)
     */
    public static final String POST_CAPTCHAVAPP = "Mine/captchavApp";

    /**
     * 获取验证码
     */
    public static final String POST_CHECKS = "Mine/checks";

    /**
     * 找回密码 校验身份
     */
    public static final String POST_RETRIEVEPWS = "Mine/AppRetrievePws";

    /**
     * 找回密码 修改
     */
    public static final String POST_CHANGE_PWD = "Mine/AppRetrievePws1";

    /**
     * 判断登录
     */
    public static final String POST_ISLOGIN = "DMine/isLogin";

    /**
     * 获取个人信息
     */
    public static final String POST_USERINFO = "DMine/getSessionUserInfoApp";

    /**
     * 获取个人认证资料
     */
    public static final String POST_DOCTOR_INFO = "DMine/getDoctorsInfoApp";

    /**
     * 我的收入
     */
    public static final String POST_GETDOCINCOME = "DMine/getDocIncomeApp";

    /**
     * 保存个人认证资料
     */
    public static final String POST_DOCTORSAUTH ="DMine/doctorsAuth";

    /**
     * 获取科室
     */
    public static final String POST_FIND_DEPARTID = "Doctors/findDepartid";

    /**
     * 获取明细
     */
    public static final String POST_MOENY_INCOME_BY_USER = "DMine/getMoenyInoutByUserApp";

    /**
     * 我的问诊单
     */
    public static final String POST_ASKHEAD = "Ask/getAskHeadApp";

    /**
     * 我的处方
     */
    public static final String POST_PRESCRIPTION_LIST = "Prescription/getPrescriptionApp";

    /**
     * 问诊详情
     */
    public static final String POST_ASKHEADBYID = "Ask/getAskHeadByIdApp";

    /**
     * 处方
     */
    public static final String POST_DRUGSAVEHEADBYDEPID = "DMine/getDrugsaveHeadByDepIdApp";

    /**
     * 药品分类
     */
    public static final String POST_DROUGCLASS = "Drug/getDrugClassApp";

    /**
     * 药品列表
     */
    public static final String POST_DRUGBYCLASS  ="Drug/getDrugByClassApp";

    /**
     * 科室列表1
     */
    public static final String POST_DEPARTVIP1 = "DMine/getDepartVip1App";

    /**
     * 科室列表2
     */
    public static final String POST_DEPARTVIP2 = "DMine/getDepartVip2App";

    /**
     * 保存处方
     */
    public static final String POST_UPDATEDRUGSAVE = "DMine/updateDrugSave";

    /**
     * 确认接诊
     */
    public static final String POST_CONFIRMATION_OF_CONSULTATION = "Ask/ConfirmationOfConsultation";

    /**
     * 药品详情
     */
    public static final String POST_DRUG_BY_IUID = "Drug/getDrugByIuidApp";

    /**
     * 处方详情
     */
    public static final String POST_PREINFOAPP = "Prescription/getPreInfoApp";

    /**
     * 意见反馈
     */
    public static final String POST_ADDSERVICE_NOTE = "DMine/addServiceNote";

    /**
     * 消息列表
     */
    public static final String POST_MESSAGE_LIST = "Inquiry/findAskChatByTouserID";

    /**
     * 消息详情  用户信息
     */
    public static final String POST_MESSAGE_USERID = "Ask/getAskHeadByUserIdApp";

    /**
     * 消息详情  消息列表
     */
    public static final String POST_ASKCHAT = "Ask/getAskChatApp";

    /**
     * 发送消息 文字
     */
    public static final String POST_SEND_MESSAGE  = "Ask/sendMessageApp";
    /**
     * 发送消息 图片
     */
    public static final String POST_SEND_PICTURESA = "Ask/sendPicturesApp";

    /**
     * 药品清单
     */
    public static final String POST_DRUG_DETAILBYIUID = "DMine/getDrugSaveDetailByIuidApp";

    /**
     * 处方备注
     */
    public static final String POST_DRUG_HEADBYIUID = "DMine/getDrugSaveHeadByIuidApp";

    /**
     * 上传图片
     */
    public static final String POST_ASK_FILENAME = "Ask/fileName";

    /**
     * 保存处方
     */
    public static final String POST_ADDPRESCRIBE = "Prescription/AddPrescribe";

    /**
     * 处方药列表
     */
    public static final String POST_ASKDRUGBYASKID = "Prescription/getAskDrugByAskIdApp";

    /**
     * 拍照开方
     */
    public static final String POST_ADDPHOTO_OPENING = "Ask/addPhoto_opening";

    /**
     * 结束会话
     */
    public static final String POST_END_SESSION = "Ask/endSession";

    /**
     * 获取常用语
     */
    public static final String POST_COMMONLANGUGE = "Ask/getCommonLanguageApp";

    /**
     * 新增常用语
     */
    public static final String POST_ADD_COMMONLANGUGE = "Ask/addCommonLanguage";

    /**
     * 设置问诊费
     */
    public static final String POST_UPDATEFACT_PRICE = "DMine/updateFact_price";

    /**
     * 我的评价
     */
    public static final String POST_DOCTOR_EVAL = "DMine/getDoctorEvalByDocApp";

    /**
     * 删除常用语
     */
    public static final String POST_DELETE_COMMONLANGUAGE = "Ask/deleteCommonLanguage";

    /**
     * 退出登录
     */
    public static final String POST_LOGOUT = "Mine/userout";

    /**
     * 邀请用户
     */
    public static final String POST_ACCOUNTAJAX = "DMine/accountAjaxApp";

    /**
     * 编辑已开处方
     */
    public static final String POST_PREINFO = "Prescription/getPreInfoApp";
    /**
     * 更新问诊信息
     */
    public static final String POST_UPDATEDIAGNOSIS = "Ask/updateDiagnosis";

    /**
     * 医院列表
     */
    public static final String POST_HOSPITALNAME = "Mine/getHospitalName";

    /**
     * 微信登录WeiXin/WeiXinLogin
     */
    public static final String POST_WEIXIN_LOGIN = "WeiXin/WeiXinLogin";

    /**
     * 微信登录 绑定手机号 获取验证码
     */
    public static final String POST_WEIXIN_CHECKS = "Mine/weiXinChecks";

    /**
     * 微信登录 绑定手机号
     */
    public static final String POST_WEIXIN_BINGPHONE = "Mine/weiXinLoginApp";

    /**
     * DMine/getDepart
     */
    public static final String POST_DMINE_DEPART = "DMine/getDepartApp";

    /**
     * 绑定银行卡 DMine/BindingBank
     */
    public static final String POST_BINDBANK= "DMine/BindingBank";

    /**
     * 判断是否绑定银行卡 isBindingBankCard
     */
    public static final String POST_IS_NINDBANKCARD = "DMine/isBindingBankCard";

    /**
     * 提现 addMoenyOut
     */
    public static final String POST_ADD_MOENYOUT = "DMine/addMoenyOut";

    /**
     * 获取问诊费  DMine/getConsultationFee
     */
    public static final String POST_GETCONSULTATIONFEE = "DMine/getConsultationFee";

    /**
     * 接收到消息
     */
    public static final String GET_MESSAGE = "getMessageApp";
    public static final String GET_MESSAGE_1 = "getMessageApp_1";
}
