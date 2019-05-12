package com.bw.movie.api;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
     * @param userId  userId
     * @param sessionId  sessionId
     * @param
     * @return
     */
    @GET
    public Observable<ResponseBody> get(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@QueryMap HashMap<String,Integer> hashMap);

    @POST
    public Observable<ResponseBody> post(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> hashMap);

    @DELETE
    public Observable<ResponseBody> delete(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> hashMap);

    @PUT
    public Observable<ResponseBody> put(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> FilePost(@Url String url, @FieldMap HashMap<String, Object> hashMap);
}
