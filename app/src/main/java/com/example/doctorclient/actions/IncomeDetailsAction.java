package com.example.doctorclient.actions;

import com.example.doctorclient.event.IncomeDetailsListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.IncomeDetailsView;
import com.example.doctorclient.util.config.MyApp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
/**
 * description: 收入明细
 * autour: huang
 * date: 2019/5/30 11:33
 * update: 2019/5/30
 * version:
 */
public class IncomeDetailsAction extends BaseAction<IncomeDetailsView> {
    public IncomeDetailsAction(RxAppCompatActivity _rxAppCompatActivity,IncomeDetailsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取收入明显
     */
    public void getIncomeDetailsList(){
        post(WebUrlUtil.POST_MOENY_INCOME_BY_USER,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),WebUrlUtil.POST_MOENY_INCOME_BY_USER)
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
                    case WebUrlUtil.POST_MOENY_INCOME_BY_USER:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                IncomeDetailsListDto incomeDetailsListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<IncomeDetailsListDto>() {
                                }.getType());
                                view.getIncomeDetailsSuccessful(incomeDetailsListDto);
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
