package com.bw.movie.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.url.URl;
import com.bw.movie.util.RetrofitUtil;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.10 8:36
 * @Description：YangXinYu
 */
public class MyModel implements ContractInterFace.IModel {
    SetLogin setLogin;
    SetRegister  setRegister;


    @Override
    public void login(String phone, String pwd, final SetLogin setLogin) {

    }

    @Override
    public void register(String phone, String nickName, String pwd, final SetRegister  setRegister) {

    }






    public interface SetLogin{
        void Succeed(LoginBean loginBean);
    }
    public interface SetRegister{
        void Succeed(RegisterBean registerBean);
    }


}
