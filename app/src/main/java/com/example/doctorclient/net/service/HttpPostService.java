package com.example.doctorclient.net.service;


import com.example.doctorclient.event.CommonLanguageListDto;
import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.event.DrugClassListDto;
import com.example.doctorclient.event.DrugDetailsDto;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.event.EvaluationListDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.IncomeDetailsListDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.InviteCodeDto;
import com.example.doctorclient.event.LoginDto;
import com.example.doctorclient.event.MessageDetailInquiryDto;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.event.MessageListDto;
import com.example.doctorclient.event.MyInquiryDto;
import com.example.doctorclient.event.MyPrescriptionDto;
import com.example.doctorclient.event.PrescriptionDetailDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.PrescriptionDrugsDto;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.event.SendMessageDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity;
import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity2;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

/**
* 
* @author lgh
* created at 2019/5/13 0013 14:38
*/
public interface HttpPostService {

//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<BaseResultEntity> getAbout();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<String> getAboutString();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Call<BaseResultEntity> getAboutByCall();
//
//    @GET(WebUrlUtil.URL_GET_MAIN)
//    Observable<BaseResultEntity> getHome();


    /**
     * 获取数据
     * @return
     */
    @POST
    Observable<BaseResultEntity2> PostData_1(@Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie")String SessionId, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie")String SessionId, @Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie")String SessionId, @Body RequestBody Body, @Url String url);

    @POST
    Observable<String> PostData_String(@Header("Cookie")String SessionId,@Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<String> PostData_String(@Header("Cookie")String SessionId,@Body RequestBody Body, @Url String url);
}
