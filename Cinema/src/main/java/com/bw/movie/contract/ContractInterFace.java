package com.bw.movie.contract;

import com.bw.movie.bean.AlipayBean;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.BuyBean;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FilmCinemaBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.bean.HeadBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.PwdBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.SignBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.VersionBean;
import com.bw.movie.bean.WechatBean;
import com.bw.movie.model.MyModel;

import java.io.File;

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
        void showing();
        void beon();
        void attention(int movieId);
        void noattention(int movieId);
        void details(int movieId);
        void particulars(int movieId);
        void review(int movieId, int count);
        void great(int commentId);
        void filmReview(int movieId, String commentContent);
        void filmcinema(int movieId);
        void followCinema(int cinemaId);
        void noFollowCinema(int cinemaId);
        void schedule(int cinemasId,int movieId);
        void buy(int scheduleId,int amount,String sign);
        void wechat(int payType,String orderId);
        void alipay(int payType,String orderId);
        void wechatlogin(String code);
        void pwd(String oldPwd,String newPwd,String newPwd2);
        void head(File image);
        void user(String nickName,int sex,String email);
        void signin();
        void message(int cinemaId);
        void recommend(int page,int count);
        void nearby(int page,int count);
        void banners(int cinemaId);
        void infos(int cinemaId);
        void ping(int cinemaId,int page,int count);
        void version(String ak);
        void opinion(String content);
    }
    //p层
    public interface  IModel{
        void login(String phone, String pwd, MyModel.SetLogin setLogin);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2, final MyModel.SetRegister setRegister);
        void popularMovie(MyModel.SetPopularMovie setPopularMovie);
        void showing(MyModel.SetShowing setShowing);
        void beon(MyModel.SetBeon setBeon);
        void message(int cinemaId, MyModel.SetMessage setMessage);
        void attention(int movieId,MyModel.SetAttention setAttention);
        void noattention(int movieId,MyModel.SetNoAttention setNoAttention);
        void details(int movieId,  MyModel.SetDetails setDetails);
        void particulars(int movieId,  MyModel.SetParticulars setParticulars);
        void review(int movieId, int count,  MyModel.SetReview setReview);
        void great(int commentId,MyModel.SetGreat setGreat);
        void filmReview(int movieId,String commentContent,MyModel.SetFilmReview setFilmReview);
        void filmcinema(int movieId,MyModel.SetFilmCinema setFilmCinema);
        void noFollowCinema(int cinemaId,MyModel.SetNoFollowCinema setNoFollowCinema);
        void followCinema(int cinemaId,MyModel.SetFollowCinema setFollowCinema);
        void schedule(int cinemasId,int movieId,MyModel.SetSchedule setSchedule);
        void buy(int scheduleId,int amount,String sign,MyModel.SetBuy setBuy);
        void wechat(int payType,String orderId,MyModel.SetWechat setWechat);
        void alipay(int payType,String orderId,MyModel.SetAlipay setAlipay);
        void wechatlogin(String code,MyModel.SetWechatLogin setWechatLogin);
        void pwd(String oldPwd,String newPwd,String newPwd2,MyModel.SetPwd setPwd);
        void head(File image,MyModel.SetHead setHead);
        void user(String nickName,int sex,String email,MyModel.SetUser setUser);
        void signin(MyModel.SetSignin setSignin);
        void recommend(int page,int count,MyModel.SetRecommend setRecommend);
        void nearby(int page,int count,MyModel.SetNearby setNearby);
        void banners(int cinemaId, final MyModel.SetBanner setBanner);
        void infos(int cinemaId, final MyModel.SetInfos setInfos);
        void ping(int cinemaId,int page,int count,MyModel.SetPing setPing);
        void version(String version,  MyModel.SetVersion setVersion);
        void opinion(String content,MyModel.SetOpinion setOpinion);
    }
    public interface IFilm{
        void recommend(CinemafjBean wechatLoginBean);
        void nearby(CinemafjBean wechatLoginBean);
    }
    public interface IBuyTicket{
        void banners(CinemaBannerBean wechatLoginBean);
        void infos(CinemaxqBean wechatLoginBean);
        void ping(CinemaplBean wechatLoginBean);
    }


    public interface IMineMessage{
        void pwd(PwdBean wechatLoginBean);
        void head(HeadBean wechatLoginBean);
        void user(UserBean wechatLoginBean);
    }
    public interface IOpinion{
        void opinion(OpinionBean wechatLoginBean);
    }
    public interface IMy{
        void signin(SignBean wechatLoginBean);
        void version(VersionBean wechatLoginBean);
    }
    public interface  IWechatLogin{
        void wechatlogin(LoginBean wechatLoginBean);
    }
    public  interface  ILogin{
        void login(LoginBean loginBean);
    }
    public  interface  IRegister{
        void register(String message);
    }
    public interface  IFilmHome{
        void popularMovie(PopularMovieBean popularMovieBean);
        void showing(ShowingBean showingBean);
        void beon(BeonBean beonBean);
    }
    public interface ISearchFilm{
        void attention(AttentionBean attentionBean);
        void noattention(AttentionBean attentionBean);
    }
    public interface IDetailsFilm{
        void details(DetailsBean detailsBean);
        void particulars(ParticularsBean particularsBean);
        void review(ReviewBean reviewBean);
        void great(GreatBean greatBean);
        void filmReview(GreatBean greatBean);
    }
    public interface IFilmCinema{
        void filmcinema(FilmCinemaBean filmCinemaBean);
        void followCinema(GreatBean greatBean);
        void noFollowCinema(GreatBean greatBean);
    }
    public interface  ITicket{
        void schedule(ScheduleBean scheduleBean);
    }
    public interface IRecommendCinema{
        void recommendCinema(CinematjBean cinematjBean);
    }

    public interface IBuy{
        void buy(BuyBean buyBean);
        void wechat(WechatBean wechatBean);
        void alipay(AlipayBean alipayBean);
    }

    public interface IMessage{
        void message(CinemaxqBean cinemaxqBean);
    }


}
