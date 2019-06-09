package com.example.contract;

import com.example.bean.Login;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 8:26
 * @Description：YangXinYu
 */
public interface IContract {
    public interface LoginView{

        void onLoginSuccess(Login login);

        void onLoginFail(String errorInfo);
    }

    public interface LoginModel{
        void login(String name,String password,LoginCallBack callBack);
    }

    public interface LoginCallBack{
        void onSuccess(Login login);

        void onFail(String errorInfo);
    }

}
