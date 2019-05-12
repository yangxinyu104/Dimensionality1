package com.bw.movie.contract;

import com.bw.movie.bean.LoginBean;
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
    }
    //p层
    public interface  IModel{
        void login(String phone, String pwd, final MyModel.SetLogin setLogin);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2, final MyModel.SetRegister setRegister);
    }
    public  interface  ILogin{
        void login(LoginBean loginBean);
    }
    public  interface  IRegister{
        void register(String message);
    }
}
