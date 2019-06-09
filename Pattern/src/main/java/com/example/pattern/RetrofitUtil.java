package com.example.pattern;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 21:29
 * @Description：YangXinYu
 */
public class RetrofitUtil {

    private final Retrofit retrofit;
    private static RetrofitUtil retrofitUtil;
    private RetrofitUtil(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(new File(Environment.getExternalStorageDirectory()+"okHttpClient"),100))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory())
                .readTimeout(1000, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://mobile.bwstudent.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //单例模式
    public static synchronized RetrofitUtil GetInstance(){
        if (retrofitUtil == null){
            retrofitUtil = new RetrofitUtil();
        }
            return retrofitUtil;
    }

    public void doFiledPost(String url,String phone,String name,String pwd,HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.FilePost(url,phone,name,pwd);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }

    private Observer GetObserver(final HttpListener httpListener) {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {

            @Override
            public void onError(Throwable e) {
                Log.e("tag",e.getMessage());
                httpListener.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    httpListener.Succeed(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return observer;
    }

    public interface HttpListener{
        void Succeed(String s);
        void Error(String s);
    }
}
