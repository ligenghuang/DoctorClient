package com.example.doctorclient.actions;

import android.telecom.CallAudioState;

import com.example.doctorclient.event.ConsultationFeeDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.MineView;
import com.example.doctorclient.util.data.MySp;
import com.google.gson.Gson;
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
 * 我的
 *
 * @author lgh
 * created at 2019/5/15 0015 16:03
 */
public class MineAction extends BaseAction<MineView> {
    public MineAction(RxAppCompatActivity _rxAppCompatActivity, MineView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 获取医生信息
     */
    public void getUserInfo() {
        post(WebUrlUtil.POST_USERINFO, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), WebUrlUtil.POST_USERINFO)));
    }

    /**
     * 设置问诊费
     * @param text
     */
    public void updateFactPrice(String text){
        post(WebUrlUtil.POST_UPDATEFACT_PRICE, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("fact_price",text), WebUrlUtil.POST_UPDATEFACT_PRICE)));
    }

    /**
     * 获取问诊费
     */
    public void getConsultationFee(){
        post(WebUrlUtil.POST_GETCONSULTATIONFEE, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), WebUrlUtil.POST_GETCONSULTATIONFEE)));
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
                            if (generalDto.equals("1")) {
                                view.isLoginSuccessful();
                                return;
                            }
                            view.isLoginError();
                            return;
                        }
//                        view.isLoginError();
                        break;
                    case WebUrlUtil.POST_USERINFO:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            UserInfoDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<UserInfoDto>() {
                            }.getType());
                            view.getUserInfoSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_UPDATEFACT_PRICE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.updateFactPriceSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_GETCONSULTATIONFEE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            ConsultationFeeDto consultationFeeDto = gson.fromJson(action.getUserData().toString(), new TypeToken<ConsultationFeeDto>() {
                            }.getType());
                            view.getConsultationFeeSuccessful(consultationFeeDto);
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
