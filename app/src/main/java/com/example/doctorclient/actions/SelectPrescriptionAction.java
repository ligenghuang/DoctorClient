package com.example.doctorclient.actions;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DrugDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugsDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.SelectPrescriptionView;
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
/**
* 选择处方
* @author lgh
* created at 2019/5/18 0018 16:26
*/
public class SelectPrescriptionAction extends BaseAction<SelectPrescriptionView> {
    public SelectPrescriptionAction(RxAppCompatActivity _rxAppCompatActivity,SelectPrescriptionView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取科室
     */
    public void getFindDepartid(){
        post(WebUrlUtil.POST_FIND_DEPARTID,false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),WebUrlUtil.POST_FIND_DEPARTID)));
    }

    /**
     * 获取处方列表
     * @param id
     */
    public void getDrugsaveHeadByDepId(String id){
        post(WebUrlUtil.POST_DRUGSAVEHEADBYDEPID,false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("departid",id),WebUrlUtil.POST_DRUGSAVEHEADBYDEPID)));
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
                    case WebUrlUtil.POST_FIND_DEPARTID:
                        //todo 科室
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                DepartidDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DepartidDto>() {
                                }.getType());
                                view.getDepartListSuccessful(generalDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DRUGSAVEHEADBYDEPID:
                        //todo 处方
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                PrescriptionDrugsDto prescriptionDrugsDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PrescriptionDrugsDto>() {
                                }.getType());
                                view.getDrugsaveHeadByDepIdSuccessful(prescriptionDrugsDto);
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(),new TypeToken<GeneralDto>() {
                                }.getType());
                                if (generalDto.getCode() == -2){
                                    view.onLigonError();
                                }else {
                                    view.onError(generalDto.getMsg(),generalDto.getCode());
                                }
                                return;
                            }
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
