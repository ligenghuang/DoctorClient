package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.MyInquiryDto;
import com.example.doctorclient.event.MyPrescriptionDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.MyPrescriptionView;
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
* 我的处方药
* @author lgh
* created at 2019/5/18 0018 10:21
*/
public class MyPrescriptionAction extends BaseAction<MyPrescriptionView> {

    public MyPrescriptionAction(RxAppCompatActivity _rxAppCompatActivity,MyPrescriptionView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取我的处方 列表
     * @param status
     */
    public void getPrescription(int status){
//        if (status == -1){
//            post(WebUrlUtil.POST_PRESCRIPTION_LIST,false,service -> manager.runHttp(
//                    service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("H5ORDOC","1","status","0"),WebUrlUtil.POST_PRESCRIPTION_LIST)));
//        }else {
//            post(WebUrlUtil.POST_PRESCRIPTION_LIST,false,service -> manager.runHttp(
//                    service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("H5ORDOC","1","status",status+""),WebUrlUtil.POST_PRESCRIPTION_LIST)));
//        }
        post(WebUrlUtil.POST_PRESCRIPTION_LIST,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("H5ORDOC","1","status",status+""),WebUrlUtil.POST_PRESCRIPTION_LIST)));

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
                    case WebUrlUtil.POST_PRESCRIPTION_LIST:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                MyPrescriptionDto myPrescriptionDto = gson.fromJson(action.getUserData().toString(), new TypeToken<MyPrescriptionDto>() {
                                }.getType());
                                view.getPrescriptionSuccessful(myPrescriptionDto);
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(),new TypeToken<GeneralDto>(){}.getType());
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
