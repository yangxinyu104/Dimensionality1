package com.bw.movie.util;

import android.os.Environment;

import com.bw.movie.api.Api;
import com.bw.movie.url.URl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
                .build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(URl.URL_MAIN)
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

    public void doGet(String url, int userId, String sessionId, HashMap<String,String> hashMap,HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.get(url, userId, sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }
    public  void  doPost(String url, int userId, String sessionId, HashMap<String,String> hashMap,HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.post(url, userId, sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }
    public void doPut(String url, int userId, String sessionId, HashMap<String,String> hashMap,HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.put(url, userId, sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }
    public void doDelete(String url, int userId, String sessionId, HashMap<String,String> hashMap,HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.delete(url, userId, sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }
    public void doFiledPost(String url, int userId, String sessionId, HashMap<String,Object> hashMap, HttpListener httpListener){
        Api api = retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.FilePost(url, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }
    private Observer GetObserver(final HttpListener httpListener) {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {

            @Override
            public void onError(Throwable e) {
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