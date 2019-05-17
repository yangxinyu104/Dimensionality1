package com.bw.movie.api;

import com.bw.movie.bean.WechatLoginBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
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
    public Observable<ResponseBody> toWechat(@Url String url, @Field("code") String code);

    @GET
    public Observable<ResponseBody> gets(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId);

    @GET
    public Observable<ResponseBody> get(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@QueryMap HashMap<String,Object> hashMap);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> post(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @FieldMap HashMap<String, Object> hashMap);

    @DELETE
    public Observable<ResponseBody> delete(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> hashMap);

    @PUT
    public Observable<ResponseBody> put(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> FilePost(@Url String url, @FieldMap HashMap<String, Object> hashMap);

    @Multipart
    @POST
    public Observable<ResponseBody> PostUpdateHead(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part file);
}
