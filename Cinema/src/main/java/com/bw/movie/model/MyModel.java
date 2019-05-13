package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.url.URl;
import com.bw.movie.util.RetrofitUtil;
import com.google.gson.Gson;

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


}
