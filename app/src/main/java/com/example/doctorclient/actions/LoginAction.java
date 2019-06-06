package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.LoginDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.LoginView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 登录
 *
 * @author lgh
 * created at 2019/5/13 0013 15:05
 */
public class LoginAction extends BaseAction<LoginView> {
    public LoginAction(RxAppCompatActivity _rxAppCompatActivity, LoginView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }


    /**
     * 登录
     * @param username
     * @param pwd
     */
    public void login(final String username, String pwd) {
        post(WebUrlUtil.POST_LOGIN, false, service -> manager.runHttp(service.PostData_1(CollectionsUtils.generateMap("userName",username,"password",pwd,"type","1"),WebUrlUtil.POST_LOGIN)));
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
                    case WebUrlUtil.POST_LOGIN:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            LoginDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<LoginDto>() {
                            }.getType());
                            view.LoginSuccessful(generalDto);
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
