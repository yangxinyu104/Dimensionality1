package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.RegisterBean;
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
        HashMap<String,Integer> hashMap= new HashMap<>();
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


    public interface SetLogin{
        void Succeed(LoginBean loginBean);
    }


    public interface SetRegister{
        void Succeed(RegisterBean registerBean);
    }
    public interface SetPopularMovie{
        void Succeed(PopularMovieBean popularMovieBean);
    }

}
