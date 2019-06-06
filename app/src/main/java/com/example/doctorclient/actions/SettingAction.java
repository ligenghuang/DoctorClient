package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.SettingView;
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
 * description:设置
 * autour: huang
 * date: 2019/5/22 13:55
 * update: 2019/5/22
 * version:
 */
public class SettingAction extends BaseAction<SettingView> {
    public SettingAction(RxAppCompatActivity _rxAppCompatActivity,SettingView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 退出登录
     */
    public void logout(){
        post(WebUrlUtil.POST_LOGOUT,false,service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("H5ORDOC","1"),WebUrlUtil.POST_LOGOUT)
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
                    case WebUrlUtil.POST_LOGOUT:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            view.logoutSuccessful(generalDto);
                            return;
                        }
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

