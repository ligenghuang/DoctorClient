package com.example.doctorclient.actions;

import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.IncomeView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
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
* 我的收入
* @author lgh
* created at 2019/5/17 0017 13:50
*/
public class IncomeAction extends BaseAction<IncomeView> {
    public IncomeAction(RxAppCompatActivity _rxAppCompatActivity,IncomeView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取我的收入
     */
    public void getDocIncome(){
        post(WebUrlUtil.POST_GETDOCINCOME,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),WebUrlUtil.POST_GETDOCINCOME)
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
                    case WebUrlUtil.POST_GETDOCINCOME:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try{
                                DoctorInfoDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DoctorInfoDto>() {
                                }.getType());
                                view.getDocIncomeSuccessful(generalDto);
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
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
