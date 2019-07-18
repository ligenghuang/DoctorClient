package com.example.doctorclient.util;

import android.util.Log;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.net.service.HttpPostService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity;
import com.lgh.huanglib.util.L;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
* 工具类
* @author lgh
* created at 2019/5/15 0015 9:12
*/
public class Utilt {
    public static RequestBody getBody(String json){
        RequestBody body= RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        return body;
    }

    /***
     * 随机数
     * @return
     */
    public static int getRandom(){
        return new Random().nextInt(1000);
    }

    /**
     * SHA加密
     *
     * @param strSrc 明文
     *
     * @return 加密之后的密文
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts
     *            数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 转utf-8
     * @param str
     * @return
     */
    public static String toUtf8(String str){
        String result = null;

        try {
            result = URLDecoder.decode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            L.e("lgh_news",e.toString());
        }
        return result;
    }

    /**
     * 判断小数点开头
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return str.matches("\\.\\d+$");
    }

    //验证银行卡号
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    //从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 将银行卡中间八个字符隐藏为*
     */
    public static String getHideBankCardNum(String bankCardNum) {
        try {
            if (bankCardNum == null) return "未绑定";

            int length = bankCardNum.length();

            if (length > 4) {
                String endNum = bankCardNum.substring(15, length);
                bankCardNum = "**** **** **** " + endNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankCardNum;
    }
}
