package com.bw.movie.api;

import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;

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
import retrofit2.http.Query;
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
    public Observable<ResponseBody> get(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, Object> hashMap);
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

    //展示推荐影院
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<CinematjBean> showtj(@Query("page") int page, @Query("count") int count);

    //展示附近影院
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<CinemafjBean> showfj(@Query("page") int page, @Query("count") int count);

    //影院轮播
    @GET("movieApi/movie/v1/findMovieListByCinemaId")
    Observable<CinemaBannerBean> showbanner(@Query("cinemaId") String cinemaId);

    //影院电影信息明细
    @GET("movieApi/cinema/v1/findCinemaInfo")
    Observable<CinemaxqBean> showxq(@Query("cinemaId") int cinemaId);

}
