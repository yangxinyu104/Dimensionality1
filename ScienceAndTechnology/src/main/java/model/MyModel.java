package model;

import android.support.v4.app.NavUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;

import bean.LoginBean;
import bean.RegisterBean;
import contract.ContractInterFace;
import url.URl;
import util.RetrofitUtil;

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
        this.setLogin = setLogin;
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("pwd",pwd);
        RetrofitUtil.GetInstance().doPost(URl.URL_LOGIN, 0, null, hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                LoginBean loginBean = new Gson().fromJson(s, LoginBean.class);
                setLogin.Succeed(loginBean);
            }
            @Override
            public void Error(String s) {
                Log.e("tag","Error : " + s );
            }
        },null);
    }

    @Override
    public void register(String phone, String nickName, String pwd, final SetRegister  setRegister) {
        this.setRegister = setRegister;
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("pwd",pwd);
        hashMap.put("nickName",nickName);
        RetrofitUtil.GetInstance().doPost(URl.URL_REGISTER, 0, null, hashMap, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                RegisterBean registerBean = new Gson().fromJson(s, RegisterBean.class);
                setRegister.Succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","Error : " + s );
            }
        },null);
    }






    public interface SetLogin{
        void Succeed(LoginBean loginBean);
    }
    public interface SetRegister{
        void Succeed(RegisterBean registerBean);
    }


}
