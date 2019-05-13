package com.bw.movie.contract;

import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.model.MyModel;

/**
 * Project name：WeiDuMovie
 * Time: 2019/5/10 18:22
 * Author: 高海波
 */
public interface ContractInterFace {

    //p层
    public interface  IPresenter{
        void login(String phone, String pwd);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2);
        void popularMovie();
    }
    //p层
    public interface  IModel{
        void login(String phone, String pwd, final MyModel.SetLogin setLogin);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2, final MyModel.SetRegister setRegister);
        void popularMovie(MyModel.SetPopularMovie setPopularMovie);
    }
    public  interface  ILogin{
        void login(LoginBean loginBean);
    }
    public  interface  IRegister{
        void register(String message);
    }
    public interface  IFilmHome{
        void popularMovie(PopularMovieBean popularMovieBean);
    }

    public interface IRecommendCinema{
        void recommendCinema(CinematjBean cinematjBean);
    }

}
