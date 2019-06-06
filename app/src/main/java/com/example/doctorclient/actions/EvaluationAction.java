package com.example.doctorclient.actions;

import com.example.doctorclient.event.EvaluationListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.EvaluationView;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.data.MySp;
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
 * description:我的评价
 * autour: huang
 * date: 2019/5/30 9:41
 * update: 2019/5/30
 * version:
 */
public class EvaluationAction extends BaseAction<EvaluationView> {
    public EvaluationAction(RxAppCompatActivity _rxAppCompatActivity,EvaluationView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 获取评价列表
     */
    public void getEvaluationList(){
        post(WebUrlUtil.POST_DOCTOR_EVAL,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),WebUrlUtil.POST_DOCTOR_EVAL)
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
                    case WebUrlUtil.POST_ISLOGIN:

                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            if (generalDto.equals("1")) {
                                view.isLoginSuccessful();
                            }else {
                                view.isLoginError();
                            }
                            return;
                        }
//                        view.isLoginError();
                        break;
                    case WebUrlUtil.POST_DOCTOR_EVAL:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                EvaluationListDto evaluationListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<EvaluationListDto>() {
                                }.getType());
                                view.getEvaluationListSuccessful(evaluationListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
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
