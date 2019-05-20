package com.bw.movie.ghb;

import com.bw.movie.api.Api;
import com.bw.movie.app.MyApplication;
import com.facebook.common.util.ByteConstants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.jar.Manifest;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 8:20
 * Author: 高海波
 */
public class HttpUtil {

    public final Api api;

    public static HttpUtil getInstance(){
        return HttpUtilInstance.httpUtil;
    }

    //单列
    private static class HttpUtilInstance{
        private static HttpUtil httpUtil = new HttpUtil();
    }

    //日志拦截器
    private class logInstance implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request build = request.newBuilder()
                    .addHeader("userId", MyApplication.UserId+"")
                    .addHeader("sessionId", MyApplication.SessionId)
                    .build();
            return chain.proceed(request);
        }
    }

    //构造方法私有化
    private HttpUtil(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new logInstance())
                .connectTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(new File("com.bw.cache"), 100 * ByteConstants.MB))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://mobile.bwstudent.com/movieApi/")
                .client(okHttpClient)
                .build();

        api = retrofit.create(Api.class);

    }
}
