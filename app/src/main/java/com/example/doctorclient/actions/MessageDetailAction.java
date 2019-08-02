package com.example.doctorclient.actions;

import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.example.doctorclient.event.CommonLanguageListDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.MessageDetailInquiryDto;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.SendMessageDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.MessageDetailView;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.data.MySp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jkt.tcompress.TCompress;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.lgh.huanglib.util.data.StreamUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description: 消息详情
 * autour: huang
 * date: 2019/5/23 13:37
 * update: 2019/5/23
 * version:
 */
public class MessageDetailAction extends BaseAction<MessageDetailView> {
    public MessageDetailAction(RxAppCompatActivity _rxAppCompatActivity, MessageDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }


    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 获取问诊信息
     * @param touserId
     */
    public void getAskHeadByUserId(String touserId) {
        post(WebUrlUtil.POST_MESSAGE_USERID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("touserId", touserId, "H5ORDOC", "1"),
                        WebUrlUtil.POST_MESSAGE_USERID)
        ));
    }

    /**
     * 获取聊天消息
     * @param touserId
     * @param count
     */
    public void getAskChat(String touserId, int count) {
        post(WebUrlUtil.POST_ASKCHAT, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("touserId", touserId, "count", count, "H5ORDOC", "1","offset","0"), WebUrlUtil.POST_ASKCHAT)
        ));
    }

    /**
     * 发送消息
     * @param chat_note
     * @param touserid
     * @param askId
     */
    public void sendMessage(String chat_note, String touserid, String askId) {
        post(WebUrlUtil.POST_SEND_MESSAGE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("chat_note", chat_note, "touserid", touserid, "askId", askId, "H5ORDOC", "1"),
                WebUrlUtil.POST_SEND_MESSAGE
        )));
    }

    /**
     * 上传图片
     *
     * @param avatar
     */
    public void sendPicturesa(String avatar, String touserid, String askId,int width,int height) {
//2.获取图片，创建请求体
        File file = new File(avatar);
        String name = DynamicTimeFormat.getTimestamp()+".jpg";
        TCompress tCompress = new TCompress.Builder()
                .setMaxWidth(width)
                .setMaxHeight(height)
                .setQuality(70)
                .setFormat(Bitmap.CompressFormat.JPEG)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
        File compressedFile= tCompress.compressedToFile(file);
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("type", "image/jpeg")
                .addFormDataPart("H5ORDOC", "1")
                .addFormDataPart("touserid", touserid)
                .addFormDataPart("askId", askId)
                .addFormDataPart("width",width+"")
                .addFormDataPart("heigh",height+"")
                .addFormDataPart("file", name, RequestBody.create(MediaType.parse("image/jpeg"), compressedFile))
                .build();
        post(WebUrlUtil.POST_SEND_PICTURESA, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_SEND_PICTURESA)));
    }

    /**
     * 结束会话
     * @param iuid
     */
    public void endSession(String iuid){
        post(WebUrlUtil.POST_END_SESSION, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("iuid",iuid), WebUrlUtil.POST_END_SESSION)));
    }

    /**
     * 获取常用语
     */
    public void getCommonLanguage(){
        post(WebUrlUtil.POST_COMMONLANGUGE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("H5ORDOC","1"), WebUrlUtil.POST_COMMONLANGUGE)));
    }

    /**
     * 提交数据
     * @param txt
     */
    public void sendCommonLanguage(String txt){
        post(WebUrlUtil.POST_ADD_COMMONLANGUGE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("H5ORDOC","1","textContent",txt), WebUrlUtil.POST_ADD_COMMONLANGUGE)));
    }

    /**
     * 删除常用语
     * @param iuid
     */
    public void deleteCommonLanguage(String iuid) {
        post(WebUrlUtil.POST_DELETE_COMMONLANGUAGE,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("iuid",iuid),WebUrlUtil.POST_DELETE_COMMONLANGUAGE)
        ) );
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
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_MESSAGE_USERID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                MessageDetailInquiryDto inquiryDetailDto = gson.fromJson(action.getUserData().toString(), new TypeToken<MessageDetailInquiryDto>() {
                                }.getType());
                                view.getAskHeadByUserIdSuccessful(inquiryDetailDto);
                            }catch (JsonSyntaxException e){}
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ASKCHAT:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                MessageDetailListDto messageDetailListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<MessageDetailListDto>() {
                                }.getType());
                                view.getAskChatSuccessful(messageDetailListDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_SEND_MESSAGE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                            SendMessageDto sendMessageDto = gson.fromJson(action.getUserData().toString(), new TypeToken<SendMessageDto>() {
                            }.getType());
                            view.sendMessageSuccessful(sendMessageDto);
                            }catch (JsonSyntaxException e){
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_SEND_PICTURESA:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            SendMessageDto sendMessageDto = gson.fromJson(action.getUserData().toString(), new TypeToken<SendMessageDto>() {
                            }.getType());
                            view.sendPicturesaSuccessful(sendMessageDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_END_SESSION:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.endSessionSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_COMMONLANGUGE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            CommonLanguageListDto commonLanguageListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<CommonLanguageListDto>() {
                            }.getType());
                            view.getCommonLanguageSuccessful(commonLanguageListDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADD_COMMONLANGUGE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.sendCommonLanguageSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DELETE_COMMONLANGUAGE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.deleteCommonLanguageSuccessful(generalDto);
                            return;
                        }
                        break;
                    case WebUrlUtil.GET_MESSAGE_1:
                        L.e("lgh_obj",action.getUserData().toString());
                        MessageDto messageDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<MessageDto>() {
                        }.getType());
                        view.getMessage(messageDto);
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
