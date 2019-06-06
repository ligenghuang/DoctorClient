package com.example.doctorclient.actions;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.MessageListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.MessageView;
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
 * description: 消息fragment
 * autour: huang
 * date: 2019/5/22 16:55
 * update: 2019/5/22
 * version:
 */
public class MessageAction extends BaseAction<MessageView> {
    public MessageAction(RxAppCompatActivity _rxAppCompatActivity, MessageView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 获取消息列表
     */
    public void getMessageList() {
        post(WebUrlUtil.POST_MESSAGE_LIST, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext())
                        , CollectionsUtils.generateMap("H5ORDOC", "1"), WebUrlUtil.POST_MESSAGE_LIST)
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
                                return;
                            }
                            view.isLoginError();
                            return;
                        }
//                        view.isLoginError();
                        break;
                    case WebUrlUtil.POST_MESSAGE_LIST:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                MessageListDto messageListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<MessageListDto>() {
                                }.getType());
                                view.getMessageListSuccessful(messageListDto);
                            } catch (JsonSyntaxException e) {
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.GET_MESSAGE:
                        L.e("lgh_obj", action.getUserData().toString());
                        try {
                            MessageDto messageDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<MessageDto>() {
                            }.getType());
                            view.getMessage(messageDto);
                        } catch (JsonSyntaxException e) {
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
