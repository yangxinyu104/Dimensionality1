package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FilmCinemaBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.url.URl;
import com.bw.movie.util.RetrofitUtil;
import com.google.gson.Gson;

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
    SetMessage setMessage;
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
    public interface SetMessage{
        void Succeed(CinemaxqBean cinemaxqBean);
    }

}
