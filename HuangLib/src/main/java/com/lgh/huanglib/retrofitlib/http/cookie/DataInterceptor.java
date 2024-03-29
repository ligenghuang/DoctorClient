package com.lgh.huanglib.retrofitlib.http.cookie;

import com.lgh.huanglib.retrofitlib.subscribers.ProgressSubscriber;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * gson持久化截取保存数据
 * Created by WZG on 2016/10/20.
 */
public class DataInterceptor implements Interceptor {

    public DataInterceptor( ) {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);


//        L.e("lgh","Set-Cookie   =  "+response.headers("set-cookie").get(0));
        //todo 保存Cookie
        try {
            if (MySharedPreferencesUtil.getSessionId(MyApplication.getContext()) == null){
                String firstCookie = response.headers("set-cookie").get(0).toString().split(";")[0];
                MySharedPreferencesUtil.setSessionId(MyApplication.getContext(),firstCookie);
                L.e("lgh","firstCookie   = "+firstCookie);
            }else {
                String firstCookie = response.headers("set-cookie").get(0).toString().split(";")[0];
                if (!MySharedPreferencesUtil.getSessionId(MyApplication.getContext()).equals(firstCookie)){
                    MySharedPreferencesUtil.setSessionId(MyApplication.getContext(),firstCookie);
                }
            }
        }catch (IndexOutOfBoundsException e){

        }


        ResponseBody body = response.body();
        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }

        String bodyString = buffer.clone().readString(charset);
        L.e("lgh","bodyString = "+bodyString);
        ProgressSubscriber.callListener.onCall(response.code(),response.message());
        return response;
    }
}
