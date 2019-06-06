package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.RegisteredCodeDto;
import com.example.doctorclient.event.post.RegisteredPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.RegisteredView;
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
 * 注册
 * @author lgh
 * created at 2019/5/14 0014 9:22
 */
public class RegisteredAction extends BaseAction<RegisteredView>{

    public RegisteredAction(RxAppCompatActivity _rxAppCompatActivity, RegisteredView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }


    /**
     * 注册 获取验证码
     * @param userName
     */
    public void getCode(String userName){
        post(WebUrlUtil.POST_CHECKS, false, service -> manager.runHttp(
                service.PostData_1(CollectionsUtils.generateMap("type","1","userName",userName),WebUrlUtil.POST_CHECKS)));
    }

    /**
     * 注册
     * @param registeredPost
     */
    public void registered(RegisteredPost registeredPost){
        post(WebUrlUtil.POST_REGISTER, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),CollectionsUtils.generateMap("type","1","doctorName",registeredPost.getDoctorNmae(),
                "userName",registeredPost.getUserName(),"sms_code",registeredPost.getSms_code(),
                "password",registeredPost.getPassword(),"sex",registeredPost.getSex()),WebUrlUtil.POST_REGISTER)));
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
                    case WebUrlUtil.POST_CHECKS:
                        //todo 获取验证码
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            RegisteredCodeDto registeredCodeDto = gson.fromJson(action.getUserData().toString(), new TypeToken<RegisteredCodeDto>() {
                            }.getType());
                            view.CodeSuccessful(registeredCodeDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_REGISTER:
                        //todo 注册
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.registeredSuccessful(generalDto);
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
