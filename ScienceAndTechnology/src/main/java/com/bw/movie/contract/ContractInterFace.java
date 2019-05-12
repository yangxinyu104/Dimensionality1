package com.bw.movie.contract;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.model.MyModel;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 22:16
 * @Description：YangXinYu
 */
public interface ContractInterFace {

    public interface IModel{
        void  login(String phone, String pwd, final MyModel.SetLogin setLogin);
        void  register(String phone, String nickName, String pwd, MyModel.SetRegister setRegister);
    }

    public interface  IPresenter{
        void  login(String phone, String pwd);
        void  register(String phone, String nickName, String pwd);
    }

    public interface LoginIView{
        void login(LoginBean loginBean);
    }
    public interface RegisterIView{
        void register(RegisterBean registerBean);
    }


}
