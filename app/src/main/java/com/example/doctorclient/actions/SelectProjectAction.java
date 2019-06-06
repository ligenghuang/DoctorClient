package com.example.doctorclient.actions;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.SelectProjectView;
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
 * description:选择项目
 * autour: huang
 * date: 2019/5/21 10:48
 * update: 2019/5/21
 * version:
 */
public class SelectProjectAction extends BaseAction<SelectProjectView> {
    public SelectProjectAction(RxAppCompatActivity _rxAppCompatActivity, SelectProjectView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取科室（一级）
     */
    public void getDepartVip1() {
        post(WebUrlUtil.POST_DEPARTVIP1, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), WebUrlUtil.POST_DEPARTVIP1)
        ));
    }

    /**
     * 获取科室（二级）
     *
     * @param iuid
     */
    public void getDepartVip2(String iuid) {
        post(WebUrlUtil.POST_DEPARTVIP2, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("iuid", iuid), WebUrlUtil.POST_DEPARTVIP2)
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
                    case WebUrlUtil.POST_DEPARTVIP1:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DepartidDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DepartidDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.getDepartVip1Successful(generalDto);
                                return;
                            }
                            view.onLigonError();
                            return;

                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DEPARTVIP2:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DepartidDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DepartidDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.getDepartVip2Successful(generalDto);
                                return;
                            }
                            view.onLigonError();
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
