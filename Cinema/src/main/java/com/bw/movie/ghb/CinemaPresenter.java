package com.bw.movie.ghb;

import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
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

    //展示推荐影院信息
    public void loadNetData(int page,int count){
        Observable<CinematjBean> showtj = httpUtil.api.showtj(page, count);
        showtj.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinematjBean>() {
                    @Override
                    public void accept(CinematjBean cinematjBean) throws Exception {
                        getView().onSuccess(cinematjBean);
                    }
                });
    }

    //展示附近影院信息
    public void loadNetDatafj(int page,int count){
        Observable<CinemafjBean> showfj = httpUtil.api.showfj(page, count);
        showfj.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemafjBean>() {
                    @Override
                    public void accept(CinemafjBean cinemafjBean) throws Exception {
                        getView().onSuccess(cinemafjBean);
                    }
                });
    }

    //影院轮播
    public void loadBanner(String cinemaId){
        Observable<CinemaBannerBean> showbanner = httpUtil.api.showbanner(cinemaId);
        showbanner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaBannerBean>() {
                    @Override
                    public void accept(CinemaBannerBean cinemaBannerBean) throws Exception {
                        getView().onBanner(cinemaBannerBean);
                    }
                });
    }

    //影院电影信息明细
    public void loadNexDataxq(int cinemaId){
        Observable<CinemaxqBean> showxq = httpUtil.api.showxq(cinemaId);
        showxq.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaxqBean>() {
                    @Override
                    public void accept(CinemaxqBean cinemaxqBean) throws Exception {
                       getView().onCinemaxq(cinemaxqBean);
                    }
                });
    }
}
