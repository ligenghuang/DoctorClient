package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.WeiLoginDto;
import com.example.doctorclient.event.post.WeiXinLoginPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.BingPhoneView;
import com.example.doctorclient.util.config.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ： 绑定手机号
* author : lgh
* email : 1045105946@qq.com
* date : 2019/7/10
*/
public class BingPhoneAction extends BaseAction<BingPhoneView> {

    public BingPhoneAction(RxAppCompatActivity _rxAppCompatActivity, BingPhoneView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取验证码
     * @param phone
     */
    public void weiXinChecks(String phone){
        post(WebUrlUtil.POST_WEIXIN_CHECKS,false, service -> manager.runHttp(
                service.PostData_1(CollectionsUtils.generateMap("userPhone",phone),WebUrlUtil.POST_WEIXIN_CHECKS)));
    }

    /**
     * 绑定
     * @param weiXinLoginPost
     */
    public void weiXinLogin(WeiXinLoginPost weiXinLoginPost){
        post(WebUrlUtil.POST_WEIXIN_BINGPHONE,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("userName",weiXinLoginPost.getUserName(),
                        "sms_code",weiXinLoginPost.getSms_code(),"invitName",weiXinLoginPost.getInvitName(),
                        "unionid",weiXinLoginPost.getUnionid()),WebUrlUtil.POST_WEIXIN_BINGPHONE)));
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
                    case WebUrlUtil.POST_WEIXIN_CHECKS:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.weiXinChecksSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_WEIXIN_BINGPHONE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            WeiLoginDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<WeiLoginDto>() {
                            }.getType());
                            view.weiXinLoginSuccessful(generalDto);
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
