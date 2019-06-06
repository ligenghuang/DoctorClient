package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.DrugSavePost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.EditPrescriptionView;
import com.example.doctorclient.ui.impl.EditPrescriptionView2;
import com.example.doctorclient.util.config.MyApp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description:编辑处方
 * autour: huang
 * date: 2019/5/21 9:19
 * update: 2019/5/21
 * version:
 */
public class EditPrescriptionAction2 extends BaseAction<EditPrescriptionView2>{
    public EditPrescriptionAction2(RxAppCompatActivity _rxAppCompatActivity, EditPrescriptionView2 view) {
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
     * 保存处方
     * @param drugSavePost
     */
    public void updateDrugSave(DrugSavePost drugSavePost){
        String mycars = "";
        for (int i = 0; i <drugSavePost.getMycars().size() ; i++) {
            if (i == 0){
                mycars = "[";
            }
            if (i>0 && i < drugSavePost.getMycars().size()){
                mycars = mycars +",";
            }
            mycars = mycars + drugSavePost.getMycars().get(i).toString();

            if (i == drugSavePost.getMycars().size()-1){
                mycars = mycars +"]";
            }
        }
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type", drugSavePost.getType())
                .addFormDataPart("iuid", drugSavePost.getIuid())
                .addFormDataPart("name",drugSavePost.getName())
                .addFormDataPart("depart",drugSavePost.getDepart())
                .addFormDataPart("the_memo",drugSavePost.getThe_memo())
                .addFormDataPart("mycars",mycars).build();

        post(WebUrlUtil.POST_UPDATEDRUGSAVE,false,service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),requestBody,WebUrlUtil.POST_UPDATEDRUGSAVE
        )));
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
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                if (generalDto.getCode() == -2){
                                    view.onLigonError();
                                }else {
                                    view.onError(generalDto.getMsg(), action.getErrorType());
                                }
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
                            try{
                                PrescriptionDrugInfoDto prescriptionDrugInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PrescriptionDrugInfoDto>() {
                                }.getType());
                                view.getDrugSaveHeadByIuidSuccessful(prescriptionDrugInfoDto);
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                if (generalDto.getCode() == -2){
                                    view.onLigonError();
                                }else {
                                    view.onError(generalDto.getMsg(), action.getErrorType());
                                }
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_UPDATEDRUGSAVE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.updateDrugSaveSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
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
