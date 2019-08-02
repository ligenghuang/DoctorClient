package com.example.doctorclient.actions;

import android.graphics.Bitmap;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.PhotographPrescriptionView;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.data.MySp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jkt.tcompress.TCompress;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description: 拍照开方
 * autour: huang
 * date: 2019/5/28 10:36
 * update: 2019/5/28
 * version:
 */
public class PhotographPrescriptionAction extends BaseAction<PhotographPrescriptionView>{
    public PhotographPrescriptionAction(RxAppCompatActivity _rxAppCompatActivity,PhotographPrescriptionView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 问诊信息
     * @param iuid
     */
    public void getAskHeadById(String iuid){
        post(WebUrlUtil.POST_ASKHEADBYID,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("iuid",iuid),WebUrlUtil.POST_ASKHEADBYID)
        ));
    }


    /**
     * 处方药品列表
     * @param iuid
     */
    public void getPrescriptionList(String iuid){
        post(WebUrlUtil.POST_ASKDRUGBYASKID,false,service -> manager.runHttp(
                service.PostData_1
                        (MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("askId",iuid),WebUrlUtil.POST_ASKDRUGBYASKID)
        ));
    }

    /**
     * 上传图片
     * @param path
     */
    public void updatafileName(String path, int width, int height){
        File file = new File(path);
        String name = DynamicTimeFormat.getTimestamp() + ".jpg";
        TCompress tCompress = new TCompress.Builder()
                .setMaxWidth(width)
                .setMaxHeight(height)
                .setQuality(70)
                .setFormat(Bitmap.CompressFormat.JPEG)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
        File compressedFile= tCompress.compressedToFile(file);

        //构建body
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("width", width + "")
                .addFormDataPart("heigh", height + "")
                .addFormDataPart("file", name, RequestBody.create(MediaType.parse("image/jpg"), compressedFile));
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_ASK_FILENAME, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),requestBody, WebUrlUtil.POST_ASK_FILENAME)));
    }

    /**
     * 开方
     * @param iuid
     * @param diagonsis
     * @param theImg
     */
    public void savePhotographPrescription(String iuid,String diagonsis,String theImg){
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("iuid",iuid)
                .addFormDataPart("diagonsis",diagonsis)
                .addFormDataPart("theImg",theImg)
                .build();

        post(WebUrlUtil.POST_ADDPHOTO_OPENING,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),requestBody,WebUrlUtil.POST_ADDPHOTO_OPENING)));
    }

    /**
     * sticky:表明优先接收最高级  threadMode = ThreadMode.MAIN：表明在主线程
     *
     * @param action
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void MessageEvent(final Action action) {
        L.e("xx", "action   接收到数据更新....." + action.getIdentifying() + " action.getErrorType() : " + action.getErrorType());

        final String msg = action.getMsg(action);
        Observable.just(action.getErrorType())
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer == 200);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                // 输出返回结果
                L.e("xx", "输出返回结果 " + aBoolean);

                switch (action.getIdentifying()) {
                    case WebUrlUtil.POST_ISLOGIN:

                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            if (generalDto.equals("1")){
                                view.isLoginSuccessful();
                                return;
                            }
                            view.onLigonError();
                            return;
                        }
//                        view.isLoginError();
                        break;
                    case WebUrlUtil.POST_ASKHEADBYID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            InquiryDetailDto inquiryDetailDto = gson.fromJson(action.getUserData().toString(), new TypeToken<InquiryDetailDto>() {
                            }.getType());
                            view.getAskHeadByIdSuccessful(inquiryDetailDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ASKDRUGBYASKID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                PrescriptionListDto prescriptionListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PrescriptionListDto>() {
                                }.getType());
                                view.getPrescriptionListSuccessful(prescriptionListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ASK_FILENAME:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String str = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            view.updataFileNameSuccessful(str);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADDPHOTO_OPENING:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.savePhotographPrescriptionSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());

                        break;

                }

            }

        });

    }

    public void toRegister() {

        register(this);
    }

    public void toUnregister() {

        unregister(this);
    }
}
