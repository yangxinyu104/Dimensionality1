package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.app.MyApplication;
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
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.SignBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.VersionBean;
import com.bw.movie.bean.WechatBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.url.URl;
import com.bw.movie.util.RetrofitUtil;
import com.google.gson.Gson;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * Project name：WeiDuMovie
 * Time: 2019/5/10 18:22
 * Author: 高海波
 */
public class MyModel implements ContractInterFace.IModel {
    SetLogin setLogin;
    SetRegister setRegister;
    SetPopularMovie setPopularMovie;
    SetShowing setShowing;
    SetBeon setBeon;
    SetAttention setAttention;
    SetNoAttention setNoAttention;
    SetDetails setDetails;
    SetParticulars setParticulars;
    SetReview setReview;
    SetGreat setGreat;
    SetFilmReview setFilmReview;
    SetFilmCinema setFilmCinema;
    SetNoFollowCinema  setNoFollowCinema;
    SetFollowCinema setFollowCinema;
    SetSchedule setSchedule;
    SetBanner setBanner;
    SetInfos setInfos;
    SetPing setping;
    SetBuy setBuy;
    SetWechat setWechat;
    SetAlipay setAlipay;
    SetWechatLogin setWechatLogin;
    SetPwd setPwd;
    SetHead setHead;
    SetUser setUser;
    SetSignin setSignin;
    SetMessage setMessage;
    SetRecommend setRecommend;
    SetNearby setNearby;
    @Override
    public void login(String phone, String pwd, final SetLogin setLogin) {
        this.setLogin = setLogin;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("pwd",pwd);
        Log.e("tag","MyModel");
        /*for (java.com.bw.movie.util.Map.Entry<String,String> kk : hashMap.entrySet()){
            Log.e("tag",kk.getKey()+"     " +kk.getValue());dsadasdasdasd
        }*/
        RetrofitUtil.GetInstance().doFiledPost(URl.URL_LOGIN, 0, null, hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                LoginBean loginBean = new Gson().fromJson(s, LoginBean.class);
                setLogin.Succeed(loginBean);
                Log.e("tag","Succeed");
                Log.e("tag",loginBean.getMessage()+"");
            }

            @Override
            public void Error(String s) {
                Log.e("tag","login  : " + s);

            }
        });

    }

    @Override
    public void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2, final SetRegister setRegister) {
        this.setRegister = setRegister;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("nickName",nickName);
        hashMap.put("sex",sex);
        hashMap.put("birthday",birthday);
        hashMap.put("phone",phone);
        hashMap.put("email",emil);
        hashMap.put("pwd",pwd2);
        hashMap.put("pwd2",pwd2);

        RetrofitUtil.GetInstance().doFiledPost(URl.URL_REGISTER, 0, null, hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                RegisterBean registerBean = new Gson().fromJson(s, RegisterBean.class);
                setRegister.Succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","register  : " + s);
            }
        });

    }

    @Override
    public void popularMovie(final SetPopularMovie setPopularMovie) {
        this.setPopularMovie =setPopularMovie;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        RetrofitUtil.GetInstance().doGet(URl.URL_POPULARMOVIE, MyApplication.UserId,hashMap ,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                PopularMovieBean popularMovieBean = new Gson().fromJson(s, PopularMovieBean.class);
                setPopularMovie.Succeed(popularMovieBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","popularMovie  : " + s);
            }
        });
    }

    @Override
    public void showing(final SetShowing setShowing) {
        this.setShowing = setShowing;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        RetrofitUtil.GetInstance().doGet(URl.URL_SHOWING, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                ShowingBean showingBean = new Gson().fromJson(s, ShowingBean.class);
                setShowing.Succeed(showingBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","showing  : " + s);
            }
        });
    }

    @Override
    public void beon(final SetBeon setBeon) {
        this.setBeon = setBeon;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        RetrofitUtil.GetInstance().doGet(URl.URL_BEON, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                BeonBean beonBean = new Gson().fromJson(s, BeonBean.class);
                setBeon.Succeed(beonBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","beon  : " + s);
            }
        });
    }

    @Override
    public void attention(int movieId,final SetAttention setAttention) {
        this.setAttention = setAttention;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        RetrofitUtil.GetInstance().doGet(URl.URL_ATTENTION, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                AttentionBean attentionBean = new Gson().fromJson(s, AttentionBean.class);
                setAttention.Succeed(attentionBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","attention  : " + s);
            }
        });

    }

    @Override
    public void noattention(int movieId, final SetNoAttention setNoAttention) {
        this.setNoAttention = setNoAttention;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        RetrofitUtil.GetInstance().doGet(URl.URL_NOATTENTION, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                AttentionBean attentionBean = new Gson().fromJson(s, AttentionBean.class);
                setNoAttention.Succeed(attentionBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","noattention  : " + s);
            }
        });
    }

    @Override
    public void details(int movieId, final SetDetails setDetails) {
        this.setDetails = setDetails;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        RetrofitUtil.GetInstance().doGet(URl.URL_DETAILS, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                DetailsBean detailsBean = new Gson().fromJson(s, DetailsBean.class);
                setDetails.Succeed(detailsBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","details  : " + s);
            }
        });
    }

    @Override
    public void particulars(int movieId, final SetParticulars setParticulars) {
        this.setParticulars = setParticulars;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        RetrofitUtil.GetInstance().doGet(URl.URL_PARTICULARS, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                ParticularsBean particularsBean = new Gson().fromJson(s, ParticularsBean.class);
                setParticulars.Succeed(particularsBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","particulars  : " + s);
            }
        });
    }

    @Override
    public void review(int movieId, int count, final SetReview setReview) {
        this.setReview = setReview;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        hashMap.put("page",1);
        hashMap.put("count",count);
        RetrofitUtil.GetInstance().doGet(URl.URL_REVIEW, MyApplication.UserId, hashMap, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                ReviewBean reviewBean = new Gson().fromJson(s, ReviewBean.class);
                setReview.Succeed(reviewBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","review  : " + s);
            }
        });

    }

    @Override
    public void great(int commentId, final SetGreat setGreat) {
        this.setGreat  =  setGreat;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("commentId",commentId);

        RetrofitUtil.GetInstance().doPost(URl.URL_GREAT, MyApplication.UserId,  MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                GreatBean greatBean = new Gson().fromJson(s, GreatBean.class);
                setGreat.Succeed(greatBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","review  : " + s);
            }
        });

    }

    @Override
    public void filmReview(int movieId, String commentContent, final SetFilmReview setFilmReview) {
        this.setFilmReview  = setFilmReview;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        hashMap.put("commentContent",commentContent);
        RetrofitUtil.GetInstance().doPost(URl.URL_FILEMREVIEW, MyApplication.UserId,  MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                GreatBean greatBean = new Gson().fromJson(s, GreatBean.class);
                setFilmReview.Succeed(greatBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","filmReview  : " + s);
            }
        });
    }

    @Override
    public void filmcinema(int movieId, final SetFilmCinema setFilmCinema) {
        this.setFilmCinema = setFilmCinema;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("movieId",movieId);
        RetrofitUtil.GetInstance().doGet(URl.URL_FILMCINEMA, MyApplication.UserId,hashMap,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                FilmCinemaBean filmCinemaBean = new Gson().fromJson(s, FilmCinemaBean.class);
                setFilmCinema.Succeed(filmCinemaBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","filmcinema  : " + s);
            }
        });


    }

    @Override
    public void noFollowCinema(int cinemaId, final SetNoFollowCinema  setNoFollowCinema) {
        this.setNoFollowCinema = setNoFollowCinema;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        RetrofitUtil.GetInstance().doGet(URl.URL_NOFOLLOWCINEMA, MyApplication.UserId,hashMap,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                GreatBean filmCinemaBean = new Gson().fromJson(s, GreatBean.class);
                setNoFollowCinema.Succeed(filmCinemaBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","noFollowCinema  : " + s);
            }
        });


    }

    @Override
    public void followCinema(int cinemaId, final SetFollowCinema setFollowCinema) {
        this.setFollowCinema = setFollowCinema;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        RetrofitUtil.GetInstance().doGet(URl.URL_FOLLOWCINEMA, MyApplication.UserId,hashMap,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                GreatBean filmCinemaBean = new Gson().fromJson(s, GreatBean.class);
                setFollowCinema.Succeed(filmCinemaBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","followCinema  : " + s);
            }
        });

    }

    @Override
    public void schedule(int cinemasId, int movieId, final SetSchedule setSchedule) {
        this.setSchedule = setSchedule;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemasId",cinemasId);
        hashMap.put("movieId",movieId);

        RetrofitUtil.GetInstance().doGet(URl.URL_SCHEDULE, MyApplication.UserId,hashMap,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                ScheduleBean scheduleBean = new Gson().fromJson(s, ScheduleBean.class);
                setSchedule.Succeed(scheduleBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","schedule  : " + s);
            }
        });
    }

    @Override
    public void message(int cinemaId, final SetMessage setMessage) {
        this.setMessage = setMessage;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        RetrofitUtil.GetInstance().doGet(URl.URL_MESSAGE, MyApplication.UserId,hashMap,MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                CinemaxqBean cinemaxqBean = new Gson().fromJson(s, CinemaxqBean.class);
                setMessage.Succeed(cinemaxqBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","message  : " + s);
            }
        });

    }

    @Override
    public void buy(int scheduleId, int amount, String sign, final SetBuy setBuy) {
        this.setBuy = setBuy;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("scheduleId",scheduleId);
        hashMap.put("amount",amount);
        hashMap.put("sign",sign);
        RetrofitUtil.GetInstance().doPost(URl.URL_BUY, MyApplication.UserId,MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                BuyBean buyBean = new Gson().fromJson(s, BuyBean.class);
                setBuy.Succeed(buyBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","buy  : " + s);
            }
        });

    }

    @Override
    public void wechat(int payType, String orderId, final SetWechat setWechat) {
        this.setWechat = setWechat;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("payType",payType);
        hashMap.put("orderId",orderId);
        RetrofitUtil.GetInstance().doPost(URl.URL_WECHAT, MyApplication.UserId,MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                WechatBean wechatBean = new Gson().fromJson(s, WechatBean.class);
                setWechat.Succeed(wechatBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","wechat  : " + s);
            }
        });

    }

    @Override
    public void alipay(int payType, String orderId, final SetAlipay setAlipay) {
        this.setAlipay = setAlipay;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("payType",payType);
        hashMap.put("orderId",orderId);
        RetrofitUtil.GetInstance().doPost(URl.URL_WECHAT, MyApplication.UserId,MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                AlipayBean  alipayBean = new Gson().fromJson(s, AlipayBean.class);
                setAlipay.Succeed(alipayBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","alipay  : " + s);
            }
        });


    }

    @Override
    public void wechatlogin(String code, final SetWechatLogin setWechatLogin) {
        this.setWechatLogin = setWechatLogin;
        RetrofitUtil.GetInstance().doWechatLoginPost(URl.URL_WECHATLOGIN, code, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                LoginBean wechatLoginBean = new Gson().fromJson(s, LoginBean.class);
                setWechatLogin.Succeed(wechatLoginBean);
            }

            @Override
            public void Error(String s) {

                Log.e("tag","   " + s );
            }
        });
    }

    @Override
    public void pwd(String oldPwd, String newPwd, String newPwd2, final SetPwd setPwd) {
        this.setPwd = setPwd;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("oldPwd",oldPwd);
        hashMap.put("newPwd",newPwd);
        hashMap.put("newPwd2",newPwd2);
        RetrofitUtil.GetInstance().doPost(URl.URL_PWD, MyApplication.UserId,MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                PwdBean pwdBean = new Gson().fromJson(s, PwdBean.class);
                setPwd.Succeed(pwdBean);
            }

            @Override
            public void Error(String s) {

                Log.e("tag","  pwd   " + s );
            }
        });


    }

    @Override
    public void head(File image, final SetHead setHead) {
        this.setHead = setHead;
        RetrofitUtil.GetInstance().doPostUpdateHead(image, URl.URL_HEAD, MyApplication.UserId, MyApplication.SessionId, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                HeadBean pwdBean = new Gson().fromJson(s, HeadBean.class);
                setHead.Succeed(pwdBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  head   " + s );
            }
        });

    }

    @Override
    public void user(String nickName, int sex, String email, final SetUser setUser) {
        this.setUser = setUser;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("nickName",nickName);
        hashMap.put("sex",sex);
        hashMap.put("email",email);

        RetrofitUtil.GetInstance().doPost(URl.URL_USER, MyApplication.UserId, MyApplication.SessionId,hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                UserBean userBean = new Gson().fromJson(s, UserBean.class);
                setUser.Succeed(userBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  head   " + s );
            }
        });


    }

    @Override
    public void signin(final SetSignin setSignin) {
        this.setSignin = setSignin;
        RetrofitUtil.GetInstance().QianGet(URl.URL_SIGNIN, MyApplication.UserId, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                SignBean signBean = new Gson().fromJson(s, SignBean.class);
                setSignin.Succeed(signBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  signin   " + s );
            }
        });

    }

    @Override
    public void recommend(int page, int count, final SetRecommend setRecommend) {
        this.setRecommend = setRecommend;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        RetrofitUtil.GetInstance().doGet(URl.URL_RECOMMEND, MyApplication.UserId,hashMap, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                CinemafjBean cinematjBean = new Gson().fromJson(s, CinemafjBean.class);
                setRecommend.Succeed(cinematjBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  recommend   " + s );
            }
        });



    }

    @Override
    public void nearby(int page, int count, final SetNearby setNearby) {
        this.setNearby = setNearby;

        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        hashMap.put("longitude",MyApplication.longitude);
        hashMap.put("latitude",MyApplication.latitude);
        RetrofitUtil.GetInstance().doGet(URl.URL_NEARBY, MyApplication.UserId,hashMap, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                CinemafjBean cinematjBean = new Gson().fromJson(s, CinemafjBean.class);
                setNearby.Succeed(cinematjBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  nearby   " + s );
            }
        });

    }

    @Override
    public void banners(int cinemaId, final SetBanner setBanner) {
        this.setBanner = setBanner;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        RetrofitUtil.GetInstance().doGet(URl.URL_BANNER, MyApplication.UserId,hashMap, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                CinemaBannerBean cinemaBannerBean = new Gson().fromJson(s, CinemaBannerBean.class);
                setBanner.Succeed(cinemaBannerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  banner   " + s );
            }
        });
    }

    @Override
    public void infos(int cinemaId, final SetInfos setInfos) {
        this.setInfos = setInfos;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        RetrofitUtil.GetInstance().doGet(URl.URL_INFO, MyApplication.UserId,hashMap, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                CinemaxqBean cinemaxqBean = new Gson().fromJson(s, CinemaxqBean.class);
                setInfos.Succeed(cinemaxqBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  infos   " + s );
            }
        });

    }

    @Override
    public void ping(int cinemaId,int page,int count,SetPing setPing) {
        this.setping = setPing;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("cinemaId",cinemaId);
        hashMap.put("page",page);
        hashMap.put("count",count);
        RetrofitUtil.GetInstance().doGet(URl.URL_PING, MyApplication.UserId,hashMap, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                CinemaplBean cinemaxqBean = new Gson().fromJson(s, CinemaplBean.class);
                setping.Succeed(cinemaxqBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  ping   " + s );
            }
        });
    }
    SetVersion setVersion;
    @Override
    public void version(String version, final SetVersion setVersion) {
        this.setVersion = setVersion;
        RetrofitUtil.GetInstance().doVersionGet(URl.URL_VERSION, MyApplication.UserId,version, MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("tag",s);
                VersionBean versionBean = new Gson().fromJson(s, VersionBean.class);
                setVersion.Succeed(versionBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","  version   " + s );
            }
        });

    }
    SetOpinion setOpinion;
    @Override
    public void opinion(String content, final SetOpinion setOpinion) {
        this.setOpinion =  setOpinion;
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("content",content);
        RetrofitUtil.GetInstance().doPost(URl.URL_OPINION, MyApplication.UserId, MyApplication.SessionId, hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                OpinionBean opinionBean = new Gson().fromJson(s, OpinionBean.class);
                setOpinion.Succeed(opinionBean);

            }

            @Override
            public void Error(String s) {
                Log.e("tag","  opinion   " + s );
            }
        });



    }
    public interface SetOpinion{
        void Succeed(OpinionBean wechatLoginBean);
    }

    public interface SetVersion{
        void Succeed(VersionBean wechatLoginBean);
    }

    public interface SetBanner{
        void Succeed(CinemaBannerBean wechatLoginBean);
    }
    public interface SetInfos{
        void Succeed(CinemaxqBean wechatLoginBean);
    }
    public interface SetPing{
        void Succeed(CinemaplBean wechatLoginBean);
    }

    public interface SetRecommend{
        void Succeed(CinemafjBean wechatLoginBean);
    }

    public interface SetNearby{
        void Succeed(CinemafjBean wechatLoginBean);
    }

    public interface SetSignin{
        void Succeed(SignBean wechatLoginBean);
    }

    public interface SetUser{
        void Succeed(UserBean wechatLoginBean);
    }

    public interface SetHead{
        void Succeed(HeadBean wechatLoginBean);
    }

    public interface SetPwd{
        void Succeed(PwdBean wechatLoginBean);
    }

    public interface SetWechatLogin{
        void Succeed(LoginBean wechatLoginBean);
    }
    public interface SetLogin{
        void Succeed(LoginBean loginBean);
    }
    public interface SetRegister{
        void Succeed(RegisterBean registerBean);
    }
    public interface SetPopularMovie{
        void Succeed(PopularMovieBean popularMovieBean);
    }
    public interface SetShowing{
        void Succeed(ShowingBean showingBean);
    }
    public interface SetBeon{
        void Succeed(BeonBean beonBean);
    }
    public interface SetAttention{
        void Succeed(AttentionBean attentionBean);
    }
    public interface SetNoAttention{
        void Succeed(AttentionBean attentionBean);
    }
    public interface SetDetails{
        void Succeed(DetailsBean detailsBean);
    }
    public interface SetParticulars{
        void Succeed(ParticularsBean particularsBean);
    }
    public interface SetReview{
        void Succeed(ReviewBean reviewBean);
    }
    public interface SetGreat{
        void Succeed(GreatBean greatBean);
    }
    public interface SetFilmReview{
        void Succeed(GreatBean greatBean);
    }
    public interface SetFilmCinema{
        void Succeed(FilmCinemaBean filmCinemaBean);
    }
    public interface SetNoFollowCinema{
        void Succeed(GreatBean greatBean);
    }
    public interface SetFollowCinema{
        void Succeed(GreatBean greatBean);
    }
    public interface SetSchedule{
        void Succeed(ScheduleBean scheduleBean);
    }
    public interface SetBuy{
        void Succeed(BuyBean buyBean);
    }
    public interface SetWechat{
        void Succeed(WechatBean wechatBean);
    }
    public interface SetAlipay {
        void Succeed(AlipayBean alipayBean);
    }
    public interface SetMessage{
        void Succeed(CinemaxqBean cinemaxqBean);
    }
}
