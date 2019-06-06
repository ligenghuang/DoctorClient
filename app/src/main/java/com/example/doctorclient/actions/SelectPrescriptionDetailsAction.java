package com.example.doctorclient.actions;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.AddPrescribePost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.SelectPrescriptionDetailsView;
import com.example.doctorclient.util.config.MyApp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
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
 * description:新建处方
 * autour: huang
 * date: 2019/5/20 11:12
 * update: 2019/5/20
 * version:
 */
public class SelectPrescriptionDetailsAction extends BaseAction<SelectPrescriptionDetailsView> {
    public SelectPrescriptionDetailsAction(RxAppCompatActivity _rxAppCompatActivity, SelectPrescriptionDetailsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取药品清单
     * @param iuid
     */
    public void getDrugSaveDetailByIuid(String iuid) {
        post(WebUrlUtil.POST_DRUG_DETAILBYIUID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("iuid", iuid), WebUrlUtil.POST_DRUG_DETAILBYIUID)));
    }

    /**
     * 获取药品备注
     * @param iuid
     */
    public void getDrugSaveHeadByIuid(String iuid) {
        post(WebUrlUtil.POST_DRUG_HEADBYIUID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("iuid", iuid), WebUrlUtil.POST_DRUG_HEADBYIUID)));
    }

    /**
     * 上传图片
     * @param path
     */
    public void updatafileName(String path) {
        File file = new File(path);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build();
        post(WebUrlUtil.POST_ASK_FILENAME, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_ASK_FILENAME)
        ));
    }

    /**
     * 新增处方
     * @param prescribePost
     */
    public void AddPrescribe(AddPrescribePost prescribePost) {
        String theImg = "[]";
        for (int i = 0; i < prescribePost.getTheImg().size(); i++) {
            if (i == 0) {
                theImg = "[";
            }else {
                theImg = theImg+",";
            }
            theImg = theImg + "\""+prescribePost.getTheImg().get(i)+ "\"";
            if (i == prescribePost.getTheImg().size() - 1) {
                theImg = theImg + "]";
            }
        }
        String mycars = "";
        for (int i = 0; i < prescribePost.getMycars().size(); i++) {
            if (i == 0) {
                mycars = "[";
            }else {
                mycars = mycars+",";
            }
            mycars = mycars + prescribePost.getMycars().get(i).toString();
            if (i == prescribePost.getMycars().size() - 1) {
                mycars = mycars + "]";
            }
        }

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type", "null")
                .addFormDataPart("askdrugheadid", prescribePost.getAskdrugheadid())
                .addFormDataPart("askIuid", prescribePost.getAskIuid())
                .addFormDataPart("the_memo", prescribePost.getThe_memo())
                .addFormDataPart("theImg" ,theImg)
                .addFormDataPart("mycars",mycars)
                .build();
        post(WebUrlUtil.POST_ADDPRESCRIBE,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),requestBody,WebUrlUtil.POST_ADDPRESCRIBE)));
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
                    case WebUrlUtil.POST_DRUG_DETAILBYIUID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                PrescriptionDrugListDto prescriptionDrugListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PrescriptionDrugListDto>() {
                                }.getType());
                                view.getDrugSaveDetailByIuidSuccessful(prescriptionDrugListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;

                    case WebUrlUtil.POST_DRUG_HEADBYIUID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                PrescriptionDrugInfoDto prescriptionDrugInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PrescriptionDrugInfoDto>() {
                                }.getType());
                                view.getDrugSaveHeadByIuidSuccessful(prescriptionDrugInfoDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
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

                    case WebUrlUtil.POST_ADDPRESCRIBE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.AddPrescribeSuccessful(generalDto);
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
