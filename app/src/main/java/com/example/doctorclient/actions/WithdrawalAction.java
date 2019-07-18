package com.example.doctorclient.actions;

import android.view.View;

import com.example.doctorclient.event.BankDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.post.BindBankPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.WithdrawalView;
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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 提现
 *
 * @author lgh
 * created at 2019/5/17 0017 14:23
 */
public class WithdrawalAction extends BaseAction<WithdrawalView> {
    public WithdrawalAction(RxAppCompatActivity _rxAppCompatActivity,WithdrawalView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * actualnamem--姓名  bank--开户行  bank_Definite--开户支行  bank_Number--银行卡号
     * @param post
     */
    public void bindBank(BindBankPost post){
        post(WebUrlUtil.POST_BINDBANK,false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("actualnamem",post.getName(),"bank",post.getBank(),
                                "bank_Definite",post.getBankSubbranch(),"bank_Number",post.getBankNumber())
                        ,WebUrlUtil.POST_BINDBANK)));
    }

    /**
     * 判断是否绑定银行卡
     */
    public void isBindingBankCard(){
        post(WebUrlUtil.POST_IS_NINDBANKCARD,false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext())
                        ,WebUrlUtil.POST_IS_NINDBANKCARD)));
    }

    /**
     * 提现
     * @param moeny
     */
    public void addMoenyOut(String moeny){
        post(WebUrlUtil.POST_ADD_MOENYOUT,false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("moeny",moeny)
                        ,WebUrlUtil.POST_ADD_MOENYOUT)));
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
                    case WebUrlUtil.POST_BINDBANK:
                        //todo 绑定银行卡
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            BankDto bankDto = gson.fromJson(action.getUserData().toString(), new TypeToken<BankDto>() {
                            }.getType());
                            view.bundBankSuccessful(bankDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_IS_NINDBANKCARD:
                        //TODO  查询是否绑定
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try{
                                BankDto bankDto = gson.fromJson(action.getUserData().toString(), new TypeToken<BankDto>() {
                                }.getType());
                                if (bankDto.getCode()==1){
                                    view.isBindingBankCardSuccessful(bankDto);
                                }else {
                                    view.onError(bankDto.getMsg(),bankDto.getCode());
                                }
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                if (generalDto.getCode()==0){
                                    view.isBindingBankCardErron(generalDto.getMsg());
                                }else {
                                    view.onError(generalDto.getMsg(),generalDto.getCode());
                                }
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADD_MOENYOUT:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.addMoenyOutSuccessful(generalDto);
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
