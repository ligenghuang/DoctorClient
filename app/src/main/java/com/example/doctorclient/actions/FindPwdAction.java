package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.post.RetrievePwsPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.FindPwdView;
import com.example.doctorclient.util.Utilt;
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
* 找回密码
* @author lgh
* created at 2019/5/14 0014 11:54
*/
public class FindPwdAction extends BaseAction<FindPwdView> {

    public FindPwdAction(RxAppCompatActivity _rxAppCompatActivity, FindPwdView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取图片验证码
     */
    public void getCode(){
        post(WebUrlUtil.POST_CAPTCHAVAPP, false, service -> manager.runHttp(service.PostData_1(WebUrlUtil.POST_CAPTCHAVAPP)));
    }

    /**
     * 校验身份
     * @param username
     * @param imgCode
     */
    public void RetrievePws(String username, String imgCode){
        post(WebUrlUtil.POST_RETRIEVEPWS, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("userName",username,"imgCode",imgCode),WebUrlUtil.POST_RETRIEVEPWS)));
    }


    /**
     * 找回密码 获取验证码
     * @param userName
     */
    public void getCode(String userName,String imgCode){
        post(WebUrlUtil.POST_CHECKS, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("codeType","2","userName",userName,"imgCode",imgCode),WebUrlUtil.POST_CHECKS)));
    }

    /**
     * 找回密码
     * @param username
     * @param pwd
     * @param againPwd
     * @param smsCode
     */
    public void findPwd(String username,String pwd,String againPwd,String smsCode){
        post(WebUrlUtil.POST_CHANGE_PWD, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),CollectionsUtils.generateMap("userName",username,"pwd",pwd,"pwds",againPwd,"smsCode",smsCode),WebUrlUtil.POST_CHANGE_PWD)));
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
                    case WebUrlUtil.POST_CAPTCHAVAPP:
                        //todo 获取验证码
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.getCodeSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_RETRIEVEPWS:
                        //todo 校验身份
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.RetrievePwsSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_CHECKS:
                        //todo 找回密码 获取验证码
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.getLoginCodeSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_CHANGE_PWD:
                        //todo 修改密码
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.findPwdSuccessful(generalDto);
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
