package com.example.pattern;


import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 21:53
 * @Description：YangXinYu
 */
public interface Api {

    /**
     *
     * @param url  url路径或地址
     * @param
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> FilePost(@Url String url, @Field("phone") String phone, @Field("nickName")String nickName, @Field("pwd")String pwd);



}
