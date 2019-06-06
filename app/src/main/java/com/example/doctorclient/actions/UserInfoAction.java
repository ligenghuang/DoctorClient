package com.example.doctorclient.actions;

import android.text.TextUtils;
import android.util.Base64;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.HospitalListDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.event.post.DoctorsInfoPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.UserInfoView;
import com.example.doctorclient.util.config.MyApp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.lgh.huanglib.util.data.StreamUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 个人信息
 *
 * @author lgh
 * created at 2019/5/16 0016 10:56
 */
public class UserInfoAction extends BaseAction<UserInfoView> {
    public UserInfoAction(RxAppCompatActivity _rxAppCompatActivity, UserInfoView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取个人信息
     */
    public void getDoctorsInfo(){
        post(WebUrlUtil.POST_DOCTOR_INFO,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),WebUrlUtil.POST_DOCTOR_INFO)));
    }

    /**
     * 获取科室
     */
    public void getFindDepartid(){
        post(WebUrlUtil.POST_FIND_DEPARTID,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),WebUrlUtil.POST_FIND_DEPARTID)));
    }

    /**
     * 保存个人信息
     * @param post
     */
    public void seveInfo(DoctorsInfoPost post){
        //构建body
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", post.getName())//todo 姓名
                .addFormDataPart("sex", post.getSex())//todo 性别
                .addFormDataPart("practicing_time",post.getPracticing_time())//todo 从业时间
                .addFormDataPart("the_level",post.getThe_level())//todo 职称
                .addFormDataPart("the_spec",post.getThe_spec())//todo 特长
                .addFormDataPart("departName",post.getDepartName())//todo 科室
                .addFormDataPart("hospital",post.getHospital())//todo 在职医院
                .addFormDataPart("isPrescribe",post.getIsPrescribe()+"") //todo 是否开处方
                .addFormDataPart("phone",post.getPhone());
        //todo 执业证书
        if (!TextUtils.isEmpty(post.getFile())){
            File file = new File(post.getFile());
            build.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        }else {
            build.addFormDataPart("file","null");
        }
        //TODO 头像
        if (!TextUtils.isEmpty(post.getPracticing_img())){
            File  Practicing = new File(post.getPracticing_img());
            build.addFormDataPart("practicing_img",Practicing.getName(), RequestBody.create(MediaType.parse("image/jpeg"), Practicing));
        }else {
            build.addFormDataPart("practicing_img","null");
        }
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_DOCTORSAUTH, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_DOCTORSAUTH)));

    }

    /**
     * 获取医院列表
     */
    public void getHospitalName(){
        post(WebUrlUtil.POST_HOSPITALNAME, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), WebUrlUtil.POST_HOSPITALNAME)));
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
                    case WebUrlUtil.POST_DOCTOR_INFO:
                        //todo 个人信息
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                           try{
                               DoctorInfoDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DoctorInfoDto>() {
                               }.getType());
                               view.getDoctorsInfoSuccessful(generalDto);
                           }catch (JsonSyntaxException e){
                               GeneralDto generalDto = gson.fromJson(action.getUserData().toString(),new TypeToken<GeneralDto>() {
                               }.getType());
                               if (generalDto.getCode() == -2){
                                   view.onLigonError();
                               }else {
                                   view.onError(generalDto.getMsg(),generalDto.getCode());
                               }
                           }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_FIND_DEPARTID:
                        //todo 科室
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DepartidDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DepartidDto>() {
                            }.getType());
                            view.getFindDepartidSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case  WebUrlUtil.POST_DOCTORSAUTH:
                        //todo 保存认证信息
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.sevaDoctorsInfoSuccessful(generalDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_HOSPITALNAME:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            HospitalListDto hospitalListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<HospitalListDto>() {
                            }.getType());
                            view.getHospitalNameSuccessful(hospitalListDto);
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
