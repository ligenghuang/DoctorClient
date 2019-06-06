package com.example.doctorclient.actions;

import com.example.doctorclient.event.DrugClassListDto;
import com.example.doctorclient.event.DrugDetailsDto;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.SelectDrugsView;
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
 * description:选择药方
 * autour: huang
 * date: 2019/5/20 11:42
 * update: 2019/5/20
 * version:
 */
public class SelectDrugsAction extends BaseAction<SelectDrugsView> {
    public SelectDrugsAction(RxAppCompatActivity _rxAppCompatActivity, SelectDrugsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取药品分类
     */
    public void getDrugClass(){
        post(WebUrlUtil.POST_DROUGCLASS,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), WebUrlUtil.POST_DROUGCLASS)
        ));
    }

    /**
     * 获取药品列表
     */
    public void getDrugByClass(String drugClass){
        post(WebUrlUtil.POST_DRUGBYCLASS,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("drug_class",drugClass),WebUrlUtil.POST_DRUGBYCLASS)
        ));
    }

    /**
     * 获取药品详情
     */
    public void  getDrugByIuid(String iuid){
        post(WebUrlUtil.POST_DRUG_BY_IUID,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("iuid",iuid),WebUrlUtil.POST_DRUG_BY_IUID)
        ));
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
                    case WebUrlUtil.POST_DROUGCLASS:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                            DrugClassListDto drugClassListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DrugClassListDto>() {
                            }.getType());
                            view.getDrugClassSuccessful(drugClassListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DRUGBYCLASS:
                        //todo 获取药品列表
                        if (aBoolean){
                            L.e("xx","输入返回结果 "+action.getUserData().toString());
                            try {
                                DrugListDto drugListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<DrugListDto>() {
                                }.getType());
                                view.getDrugByClassSuccessful(drugListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DRUG_BY_IUID:
                        if (aBoolean){
                            L.e("xx","输入返回结果 "+action.getUserData().toString());
                            try {
                                DrugDetailsDto drugDetailsDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<DrugDetailsDto>() {
                                }.getType());
                                view.getDrugByIuidSuccessful(drugDetailsDto);
                            }catch (JsonSyntaxException e){
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
