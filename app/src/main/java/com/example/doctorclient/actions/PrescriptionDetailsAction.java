package com.example.doctorclient.actions;

import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.PrescriptionDetailDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.PrescriptionDetailsView;
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
 * description:处方详情
 * autour: huang
 * date: 2019/5/22 10:28
 * update: 2019/5/22
 * version:
 */
public class PrescriptionDetailsAction extends BaseAction<PrescriptionDetailsView> {
    public PrescriptionDetailsAction(RxAppCompatActivity _rxAppCompatActivity,PrescriptionDetailsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 处方详情
     * @param iuid
     */
    public void getPreInfo(String iuid){
        post(WebUrlUtil.POST_PREINFOAPP,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("iuid",iuid),WebUrlUtil.POST_PREINFOAPP)
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
                    case WebUrlUtil.POST_PREINFOAPP:
                        if (aBoolean){
                            try {
                                PrescriptionDetailDto prescriptionDetailDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<PrescriptionDetailDto>() {
                                }.getType());
                                view.getPreInfoSuccessful(prescriptionDetailDto);
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
