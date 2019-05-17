package com.bw.movie.ghb;

import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 8:46
 * Author: 高海波
 */
public interface IMainView {

    //展示推荐影院
    public void onSuccess(CinematjBean cinematjBean);
    public void onError(String errMessage);

    //展示附近影院
    public void onSuccess(CinemafjBean cinemafjBean);
    public void onErrorfj(String errMessage);

    //展示影院轮播效果
    public void onBanner(CinemaBannerBean cinemaBanner);
    public void onErrorbanner(String errMessage);

    //影院电影信息明细
    public void onCinemaxq(CinemaxqBean cinemaxqBean);
    public void onErrorxq(String errMessage);

    //影院评论
    public void onCinemapl(CinemaplBean cinemaplBean);
    public void onErrorpl(String errMessage);

}
