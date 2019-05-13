package com.bw.movie.api;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
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
     * @param userId  userId
     * @param sessionId  sessionId
     * @param hashMap  参数
     * @return
     */
    @GET
    public Observable<RequestBody> get(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,String> hashMap);

    @Multipart
    @POST
    public Observable<RequestBody> post(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,String> hashMap, @Part MultipartBody.Part part);

    @DELETE
    public Observable<RequestBody> delete(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,String> hashMap);


    @PUT
    public Observable<RequestBody> put(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,String> hashMap);
}
