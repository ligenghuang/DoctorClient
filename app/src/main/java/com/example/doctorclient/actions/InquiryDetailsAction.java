package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.InquiryDetailsView;
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
* 问诊详情
* @author lgh
* created at 2019/5/18 0018 14:05
*/
public class InquiryDetailsAction extends BaseAction<InquiryDetailsView> {
    public InquiryDetailsAction(RxAppCompatActivity _rxAppCompatActivity,InquiryDetailsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取问诊详情
     * @param iuid
     */
    public void getAskHeadById(String iuid){
        post(WebUrlUtil.POST_ASKHEADBYID,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("iuid",iuid),WebUrlUtil.POST_ASKHEADBYID)
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
                    case WebUrlUtil.POST_ASKHEADBYID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                InquiryDetailDto inquiryDetailDto = gson.fromJson(action.getUserData().toString(), new TypeToken<InquiryDetailDto>() {
                                }.getType());
                                view.getAskHeadByIdSuccessful(inquiryDetailDto);
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
