package com.example.doctorclient.util;

import android.util.Log;

import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.net.service.HttpPostService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity;
import com.lgh.huanglib.util.L;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
* 工具类
* @author lgh
* created at 2019/5/15 0015 9:12
*/
public class Utilt {
    public static RequestBody getBody(String json){
        RequestBody body= RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        return body;
    }
}
