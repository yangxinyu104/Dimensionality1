package com.bw.movie.ghb;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 8:51
 * Author: 高海波
 */
public class CinemaPresenter extends BasePresenter<IMainView> {

    private final HttpUtil httpUtil;

    public CinemaPresenter(){
        httpUtil = HttpUtil.getInstance();
    }
}
